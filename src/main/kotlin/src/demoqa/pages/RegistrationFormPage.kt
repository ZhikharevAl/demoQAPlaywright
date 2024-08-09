package src.demoqa.pages
import com.microsoft.playwright.Page
import io.qameta.allure.kotlin.Step
import java.util.Locale

object RegistrationFormLocators {
    const val FIRST_NAME_INPUT = "#firstName"
    const val LAST_NAME_INPUT = "#lastName"
    const val EMAIL = "#userEmail"
    const val PHONE_NUMBER = "#userNumber"
    const val CURRENT_ADDRESS = "#currentAddress"
    const val GENDER_MALE = "//label[@for='gender-radio-1']"
    const val GENDER_FEMALE = "//label[@for='gender-radio-2']"
    const val GENDER_OTHER = "//label[@for='gender-radio-3']"
    const val SPORTS_CHECKBOX = "label[for='hobbies-checkbox-1']"
    const val READING_CHECKBOX = "label[for='hobbies-checkbox-2']"
    const val MUSIC_CHECKBOX = "label[for='hobbies-checkbox-3']"
}

class RegistrationFormPage(page: Page) : BasePage(page) {
    @Step("Ввод имени: {firstName}")
    fun enterFirstName(firstName: String) {
        fill(RegistrationFormLocators.FIRST_NAME_INPUT, firstName)
    }

    @Step("Ввод фамилии: {lastName}")
    fun enterLastName(lastName: String) {
        fill(RegistrationFormLocators.LAST_NAME_INPUT, lastName)
    }

    @Step("Получение значения поля 'Имя'")
    fun getFirstNameValue(): String {
        return inputValue(RegistrationFormLocators.FIRST_NAME_INPUT)
    }

    @Step("Получение значения поля 'Фамилия'")
    fun getLastNameValue(): String {
        return inputValue(RegistrationFormLocators.LAST_NAME_INPUT)
    }

    @Step("Ввод email: {email}")
    fun enterEmail(email: String) {
        fill(RegistrationFormLocators.EMAIL, email)
    }

    @Step("Получение значения поля 'Email'")
    fun getEmailValue(): String {
        return inputValue(RegistrationFormLocators.EMAIL)
    }

    @Step("Ввод номера телефона: {phoneNumber}")
    fun enterPhoneNumber(phoneNumber: String) {
        fill(RegistrationFormLocators.PHONE_NUMBER, phoneNumber)
    }

    @Step("Получение значения поля 'Номер телефона'")
    fun getPhoneNumberValue(): String {
        return inputValue(RegistrationFormLocators.PHONE_NUMBER)
    }

    @Step("Ввод текущего адреса: {currentAddress}")
    fun enterCurrentAddress(currentAddress: String) {
        fill(RegistrationFormLocators.CURRENT_ADDRESS, currentAddress)
    }

    @Step("Получение значения поля 'Текущий адрес'")
    fun getCurrentAddressValue(): String {
        return inputValue(RegistrationFormLocators.CURRENT_ADDRESS)
    }

    @Step("Выбор пола: {gender}")
    fun selectGenderOption(gender: String) {
        when (gender.lowercase(Locale.getDefault())) {
            "male" -> click(RegistrationFormLocators.GENDER_MALE)
            "female" -> click(RegistrationFormLocators.GENDER_FEMALE)
            "other" -> click(RegistrationFormLocators.GENDER_OTHER)
        }
    }

    @Step("Получение выбранного значения пола")
    fun getGenderValue(): String {
        return when {
            isChecked(RegistrationFormLocators.GENDER_MALE) -> "Male"
            isChecked(RegistrationFormLocators.GENDER_FEMALE) -> "Female"
            isChecked(RegistrationFormLocators.GENDER_OTHER) -> "Other"
            else -> ""
        }
    }

    @Step("Выбор хобби '{hobby}'")
    fun selectHobby(hobby: String) {
        when (hobby.lowercase(Locale.getDefault())) {
            "sports" -> click(RegistrationFormLocators.SPORTS_CHECKBOX)
            "reading" -> click(RegistrationFormLocators.READING_CHECKBOX)
            "music" -> click(RegistrationFormLocators.MUSIC_CHECKBOX)
            else -> throw IllegalArgumentException("Неизвестное хобби: $hobby")
        }
    }

    @Step("Проверка, выбрано ли хобби '{hobby}'")
    fun isHobbySelected(hobby: String): Boolean {
        return when (hobby.lowercase()) {
            "sports" -> isChecked(RegistrationFormLocators.SPORTS_CHECKBOX)
            "reading" -> isChecked(RegistrationFormLocators.READING_CHECKBOX)
            "music" -> isChecked(RegistrationFormLocators.MUSIC_CHECKBOX)
            else -> throw IllegalArgumentException("Неизвестное хобби: $hobby")
        }
    }
}
