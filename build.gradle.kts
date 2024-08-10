plugins {
    kotlin("jvm") version "1.9.23"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    id("io.qameta.allure") version "2.11.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val allureVersion = "2.25.0"
val aspectJVersion = "1.9.21"

val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.3")
    implementation("com.microsoft.playwright:playwright:1.45.1")
    implementation("com.github.javafaker:javafaker:1.0.2")
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-junit5")
    implementation("io.qameta.allure:allure-kotlin-commons:2.4.0")
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
