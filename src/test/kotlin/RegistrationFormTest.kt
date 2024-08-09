
import io.qameta.allure.Allure.step
import io.qameta.allure.Description
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.Story
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import src.demoqa.pages.RegistrationFormPage
import src.demoqa.utils.FakerUtil
import kotlin.test.assertTrue

@Epic("Тестирование формы регистрации")
@Feature("Заполнение полей формы")
class RegistrationFormTest : BaseTest() {
    @Test
    @Story("Общее описание тестов формы регистрации")
    @Description(
        """
    <h2>Тестирование формы регистрации на сайте</h2>
    <p>Этот набор тестов проверяет функциональность формы регистрации, включая:</p>
    <ul>
        <li>Заполнение всех полей формы</li>
        <li>Выбор Имени и Фамилии</li>
        <li>Выбор различных опций (пол, хобби, предметы)</li>
        <li>Загрузку изображения</li>
        <li>Выбор даты рождения</li>
        <li>Выбор штата и города</li>
    </ul>
    """,
    )
    fun overviewTest() {
        // Этот тест может быть пустым или содержать общую логику
        // Его основная цель - предоставить общее описание для отчета Allure
    }

    @Test
    @Story("Пользователь может заполнить имя и фамилию")
    @DisplayName("Тест заполнения полей 'Имя' и 'Фамилия'")
    @Description(
        "Этот тест проверяет корректность заполнения " +
            "полей 'Имя' и 'Фамилия' в форме регистрации.",
    )
    @Severity(SeverityLevel.CRITICAL)
    fun testFirstNameAndLastName() {
        val firstName = FakerUtil.generateName()
        val lastName = FakerUtil.generateLastName()
        val registrationFormPage = RegistrationFormPage(page)

        step("Заполнение поля 'Имя' значением $firstName")
        registrationFormPage.enterFirstName(firstName)

        step("Заполнение поля 'Фамилия' значением $lastName")
        registrationFormPage.enterLastName(lastName)

        step("Проверка корректности заполнения поля 'Имя'")
        assertEquals(
            firstName,
            registrationFormPage.getFirstNameValue(),
            "Имя $firstName не было корректно заполнено",
        )

        step("Проверка корректности заполнения поля 'Фамилия'")
        assertEquals(
            lastName,
            registrationFormPage.getLastNameValue(),
            "Фамилия $lastName не была корректно заполнена",
        )
    }

    @Test
    @Story("Пользователь может заполнить email")
    @DisplayName("Тест заполнения поля 'Email'")
    @Description(
        "Этот тест проверяет корректность заполнения " +
            "поля 'Email' в форме регистрации.",
    )
    @Severity(SeverityLevel.NORMAL)
    fun testEmail() {
        val email = FakerUtil.generateEmail()
        val registrationFormPage = RegistrationFormPage(page)

        step("Заполнение поля 'Email' значением $email")
        registrationFormPage.enterEmail(email)

        step("Проверка корректности заполнения поля 'Email'")
        assertEquals(
            email,
            registrationFormPage.getEmailValue(),
            "Email $email не был корректно заполнен",
        )
    }

    @Test
    @Story("Пользователь может заполнить номер телефона")
    @DisplayName("Тест заполнения поля 'Номер телефона'")
    @Description(
        "Этот тест проверяет корректность заполнения " +
            "поля 'Номер телефона' в форме регистрации.",
    )
    @Severity(SeverityLevel.NORMAL)
    fun testPhoneNumber() {
        val phoneNumber = FakerUtil.generatePhoneNumber()
        val registrationFormPage = RegistrationFormPage(page)

        step("Заполнение поля 'Номер телефона' значением $phoneNumber")
        registrationFormPage.enterPhoneNumber(phoneNumber)

        step("Проверка корректности заполнения поля 'Номер телефона'")
        assertEquals(
            phoneNumber,
            registrationFormPage.getPhoneNumberValue(),
            "Номер телефона $phoneNumber не был корректно заполнен",
        )
    }

    @Test
    @Story("Пользователь может заполнить текущий адрес")
    @DisplayName("Тест заполнения поля 'Текущий адрес'")
    @Description("Этот тест проверяет корректность заполнения поля 'Текущий адрес' в форме регистрации.")
    @Severity(SeverityLevel.NORMAL)
    fun testCurrentAddress() {
        val currentAddress = FakerUtil.generateCurrentAddress()
        val registrationFormPage = RegistrationFormPage(page)

        step("Заполнение поля 'Текущий адрес' значением $currentAddress")
        registrationFormPage.enterCurrentAddress(currentAddress)

        step("Проверка корректности заполнения поля 'Текущий адрес'")
        assertEquals(
            currentAddress,
            registrationFormPage.getCurrentAddressValue(),
            "Текущий адрес $currentAddress не был корректно заполнен",
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["Male", "Female", "Other"])
    fun testGenderOption(gender: String) {
        val registrationFormPage = RegistrationFormPage(page)
        step("Выбор пола '$gender'")
        registrationFormPage.selectGenderOption(gender)

        step("Проверка выбора пола '$gender'")
        assertEquals(
            gender,
            registrationFormPage.getGenderValue(),
            "Пол '$gender' не был выбран",
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["Sports", "Reading", "Music"])
    @DisplayName("Выбор хобби")
    @Description("Этот тест проверяет возможность выбора различных хобби")
    @Severity(SeverityLevel.NORMAL)
    @Story("Пользователь может выбрать различные хобби")
    fun testSelectHobby(hobby: String) {
        val registrationFormPage = RegistrationFormPage(page)
        registrationFormPage.selectHobby(hobby)
        assertTrue(registrationFormPage.isHobbySelected(hobby), "Хобби '$hobby' не было выбрано")
    }

    @Test
    @DisplayName("Выбор всех хобби")
    @Description("Этот тест проверяет возможность выбора всех хобби одновременно")
    @Severity(SeverityLevel.NORMAL)
    @Story("Пользователь может выбрать все хобби")
    fun testSelectAllHobbies() {
        val registrationFormPage = RegistrationFormPage(page)
        val hobbies = listOf("Sports", "Reading", "Music")

        hobbies.forEach { hobby ->
            registrationFormPage.selectHobby(hobby)
        }

        hobbies.forEach { hobby ->
            assertTrue(registrationFormPage.isHobbySelected(hobby), "Хобби '$hobby' не было выбрано")
        }
    }
}
