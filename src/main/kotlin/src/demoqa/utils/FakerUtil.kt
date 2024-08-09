package src.demoqa.utils

import com.github.javafaker.Faker

object FakerUtil {
    private var faker = Faker()

    fun generateName(): String = faker.name().name()

    fun generateLastName(): String = faker.name().lastName()

    fun generateCurrentAddress(): String = faker.address().fullAddress()

    fun generatePhoneNumber(): String = faker.number().digits(10)

    fun generateEmail(): String = faker.internet().emailAddress()
}
