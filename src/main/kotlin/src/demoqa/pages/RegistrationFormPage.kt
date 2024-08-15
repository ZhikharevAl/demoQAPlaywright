package src.demoqa.pages
import com.microsoft.playwright.Page
import io.qameta.allure.kotlin.Attachment
import io.qameta.allure.kotlin.Description
import io.qameta.allure.kotlin.Step
import java.nio.file.Path
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
    const val SUBJECTS = "#subjectsInput"
    const val SUBJECTS_ELEMENTS = ".subjects-auto-complete__multi-value__label"
    const val UPLOAD_PICTURE = "#uploadPicture"
    const val STATE = "Select State"
    const val STATE_INPUT = "#react-select-3-input"
    const val CITY = "Select City"
    const val CITY_INPUT = "#react-select-4-input"
}

class RegistrationFormPage(page: Page) : BasePage(page) {
    @Step("Ввод имени: {firstName}")
    @Description("Метод для ввода имени в форму регистрации")
    fun enterFirstName(firstName: String) {
        fill(RegistrationFormLocators.FIRST_NAME_INPUT, firstName)
    }

    @Step("Ввод фамилии: {lastName}")
    @Description("Метод для ввода фамилии в форму регистрации")
    fun enterLastName(lastName: String) {
        fill(RegistrationFormLocators.LAST_NAME_INPUT, lastName)
    }

    @Step("Получение значения поля 'Имя'")
    @Description("Метод для получения текущего значения поля 'Имя'")
    fun getFirstNameValue(): String {
        return inputValue(RegistrationFormLocators.FIRST_NAME_INPUT)
    }

    @Step("Получение значения поля 'Фамилия'")
    @Description("Метод для получения текущего значения поля 'Фамилия'")
    fun getLastNameValue(): String {
        return inputValue(RegistrationFormLocators.LAST_NAME_INPUT)
    }

    @Step("Ввод email: {email}")
    @Description("Метод для ввода email в форму регистрации")
    fun enterEmail(email: String) {
        fill(RegistrationFormLocators.EMAIL, email)
    }

    @Step("Получение значения поля 'Email'")
    @Description("Метод для получения текущего значения поля 'Email'")
    fun getEmailValue(): String {
        return inputValue(RegistrationFormLocators.EMAIL)
    }

    @Step("Ввод номера телефона: {phoneNumber}")
    @Description("Метод для ввода номера телефона в форму регистрации")
    fun enterPhoneNumber(phoneNumber: String) {
        fill(RegistrationFormLocators.PHONE_NUMBER, phoneNumber)
    }

    @Step("Получение значения поля 'Номер телефона'")
    @Description("Метод для получения текущего значения поля 'Номер телефона'")
    fun getPhoneNumberValue(): String {
        return inputValue(RegistrationFormLocators.PHONE_NUMBER)
    }

    @Step("Ввод текущего адреса: {currentAddress}")
    @Description("Метод для ввода текущего адреса в форму регистрации")
    fun enterCurrentAddress(currentAddress: String) {
        fill(RegistrationFormLocators.CURRENT_ADDRESS, currentAddress)
    }

    @Step("Получение значения поля 'Текущий адрес'")
    @Description("Метод для получения текущего значения поля 'Текущий адрес'")
    fun getCurrentAddressValue(): String {
        return inputValue(RegistrationFormLocators.CURRENT_ADDRESS)
    }

    @Step("Выбор пола: {gender}")
    @Description("Метод для выбора пола в форме регистрации")
    fun selectGenderOption(gender: String) {
        when (gender.lowercase(Locale.getDefault())) {
            "male" -> click(RegistrationFormLocators.GENDER_MALE)
            "female" -> click(RegistrationFormLocators.GENDER_FEMALE)
            "other" -> click(RegistrationFormLocators.GENDER_OTHER)
        }
    }

    @Step("Получение выбранного значения пола")
    @Description("Метод для получения выбранного значения пола")
    fun getGenderValue(): String {
        return when {
            isChecked(RegistrationFormLocators.GENDER_MALE) -> "Male"
            isChecked(RegistrationFormLocators.GENDER_FEMALE) -> "Female"
            isChecked(RegistrationFormLocators.GENDER_OTHER) -> "Other"
            else -> ""
        }
    }

    @Step("Выбор хобби '{hobby}'")
    @Description("Метод для выбора хобби в форме регистрации")
    fun selectHobby(hobby: String) {
        when (hobby.lowercase(Locale.getDefault())) {
            "sports" -> click(RegistrationFormLocators.SPORTS_CHECKBOX)
            "reading" -> click(RegistrationFormLocators.READING_CHECKBOX)
            "music" -> click(RegistrationFormLocators.MUSIC_CHECKBOX)
            else -> throw IllegalArgumentException("Неизвестное хобби: $hobby")
        }
    }

    @Step("Проверка, выбрано ли хобби '{hobby}'")
    @Description("Метод для проверки, выбрано ли определенное хобби")
    fun isHobbySelected(hobby: String): Boolean {
        return when (hobby.lowercase()) {
            "sports" -> isChecked(RegistrationFormLocators.SPORTS_CHECKBOX)
            "reading" -> isChecked(RegistrationFormLocators.READING_CHECKBOX)
            "music" -> isChecked(RegistrationFormLocators.MUSIC_CHECKBOX)
            else -> throw IllegalArgumentException("Неизвестное хобби: $hobby")
        }
    }

    @Step("Заполнение поля 'Предметы': {subjects}")
    @Description("Метод для заполнения поля 'Предметы' в форме регистрации")
    fun fillSubjects(subjects: List<String>) {
        subjects.forEach { subject ->
            click(RegistrationFormLocators.SUBJECTS)
            fill(RegistrationFormLocators.SUBJECTS, subject)
            Thread.sleep(500)
            pressEnter()
            Thread.sleep(500)
        }
    }

    @Step("Получение выбранных предметов")
    @Description("Метод для получения списка выбранных предметов")
    fun getSubjects(): List<String> {
        return getElementsTexts(RegistrationFormLocators.SUBJECTS_ELEMENTS)
    }

    @Step("Проверка введенных предметов")
    @Description("Метод для проверки корректности введенных предметов")
    fun verifySubjects(subjects: List<String>) {
        val actualSubjects = getSubjects()
        println("Expected: ${subjects.joinToString(", ")}")
        println("Actual: ${actualSubjects.joinToString(", ")}")
        attachSubjectsComparison(subjects, actualSubjects)
    }

    @Attachment(value = "Сравнение предметов", type = "text/plain")
    fun attachSubjectsComparison(
        expected: List<String>,
        actual: List<String>,
    ): String {
        return """
            Ожидаемые предметы: ${expected.joinToString(", ")}
            Фактические предметы: ${actual.joinToString(", ")}
            """.trimIndent()
    }

    @Step("Загрузка изображения: {filePath}")
    @Description("Метод для загрузки изображения в форму регистрации")
    fun uploadPicture(filePath: Path) {
        findElement(RegistrationFormLocators.UPLOAD_PICTURE).setInputFiles(filePath)
    }

    @Step("Проверка загрузки изображения: {filePath}")
    @Description("Метод для проверки успешной загрузки изображения")
    fun isPictureUploaded(filePath: String): Boolean {
        val uploadedFileName = findElement(RegistrationFormLocators.UPLOAD_PICTURE).inputValue()
        return uploadedFileName.split("\\").last() == filePath.split("\\").last()
    }

    @Step("Выбор штата: {state}")
    @Description("Метод для выбора штата в форме регистрации")
    fun selectState(state: String) {
        getByText(RegistrationFormLocators.STATE)
        fill(RegistrationFormLocators.STATE_INPUT, state)
        pressEnter()
    }

    @Step("Выбор населенного пункта: {city}")
    @Description("Метод для выбора населенного пункта в форме регистрации")
    fun selectCity(city: String) {
        getByText(RegistrationFormLocators.CITY)
        fill(RegistrationFormLocators.CITY_INPUT, city)
        pressEnter()
    }

    @Step("Проверка выбора штата")
    @Description("Метод для проверки выбора штата")
    fun verifyState(state: String): Boolean {
        return containsText(state)
    }

    @Step("Проверка выбора населенного пункта")
    @Description("Метод для проверки выбора населенного пункта")
    fun verifyCity(city: String): Boolean {
        return containsText(city)
    }
}
