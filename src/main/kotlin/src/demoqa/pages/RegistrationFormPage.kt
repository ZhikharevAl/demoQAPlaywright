package src.demoqa.pages

import com.microsoft.playwright.Page
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
}

class RegistrationFormPage(page: Page) : BasePage(page) {
    fun enterFirstName(firstName: String) {
        fill(RegistrationFormLocators.FIRST_NAME_INPUT, firstName)
    }

    fun enterLastName(lastName: String) {
        fill(RegistrationFormLocators.LAST_NAME_INPUT, lastName)
    }

    fun getFirstNameValue(): String {
        return inputValue(RegistrationFormLocators.FIRST_NAME_INPUT)
    }

    fun getLastNameValue(): String {
        return inputValue(RegistrationFormLocators.LAST_NAME_INPUT)
    }

    fun enterEmail(email: String) {
        fill(RegistrationFormLocators.EMAIL, email)
    }

    fun getEmailValue(): String {
        return inputValue(RegistrationFormLocators.EMAIL)
    }

    fun enterPhoneNumber(phoneNumber: String) {
        fill(RegistrationFormLocators.PHONE_NUMBER, phoneNumber)
    }

    fun getPhoneNumberValue(): String {
        return inputValue(RegistrationFormLocators.PHONE_NUMBER)
    }

    fun enterCurrentAddress(currentAddress: String) {
        fill(RegistrationFormLocators.CURRENT_ADDRESS, currentAddress)
    }

    fun getCurrentAddressValue(): String {
        return inputValue(RegistrationFormLocators.CURRENT_ADDRESS)
    }

    fun selectGenderOption(gender: String) {
        when (gender.lowercase(Locale.getDefault())) {
            "male" -> click(RegistrationFormLocators.GENDER_MALE)
            "female" -> click(RegistrationFormLocators.GENDER_FEMALE)
            "other" -> click(RegistrationFormLocators.GENDER_OTHER)
        }
    }

    fun getGenderValue(): String {
        return when {
            isChecked(RegistrationFormLocators.GENDER_MALE) -> "Male"
            isChecked(RegistrationFormLocators.GENDER_FEMALE) -> "Female"
            isChecked(RegistrationFormLocators.GENDER_OTHER) -> "Other"
            else -> ""
        }
    }
}
