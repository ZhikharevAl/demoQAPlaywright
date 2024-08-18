plugins {
    id("org.jetbrains.kotlin.jvm") version "2.0.20-RC2"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    id("io.qameta.allure") version "2.11.2"
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val allureKotlinCommonsVersion = "2.4.0"
val allureVersion = "2.25.0"
val aspectJVersion = "1.9.21"
val javaFakerVersion = "1.0.2"
val junitVersion = "5.10.3"
val log4jKotlinVersion = "1.5.0"
val log4jVersion = "2.23.1"
val playwrightVersion = "1.46.0"

val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.apache.logging.log4j:log4j-slf4j-impl:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:$log4jKotlinVersion")
    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

    implementation("com.microsoft.playwright:playwright:$playwrightVersion")

    implementation("com.github.javafaker:javafaker:$javaFakerVersion")

    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-junit5")
    implementation("io.qameta.allure:allure-kotlin-commons:$allureKotlinCommonsVersion")
    agent("org.aspectj:aspectjweaver:$aspectJVersion")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs =
        listOf(
            "-javaagent:${agent.singleFile}",
        )
}

kotlin {
    jvmToolchain(21)
}

allure {
    version.set(allureVersion)
    adapter {
        aspectjWeaver.set(true)
        frameworks {
            junit5 {
                adapterVersion.set(allureVersion)
            }
        }
    }
}

// Задача для установки Playwright
tasks.register("playwrightInstall") {
    doLast {
        exec {
            commandLine("npx", "playwright", "install", "--with-deps")
        }
    }
}

// Задача для запуска PlaywrightCodegen
tasks.register<JavaExec>("runPlaywright") {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("com.microsoft.playwright.CLI")
    args("codegen", "demo.playwright.dev/todomvc")
}
