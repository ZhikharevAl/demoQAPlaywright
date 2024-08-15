import io.qameta.allure.Allure.step
import io.qameta.allure.Description
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Issue
import io.qameta.allure.Link
import io.qameta.allure.Owner
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import io.qameta.allure.Story
import io.qameta.allure.TmsLink
import org.apache.logging.log4j.kotlin.Logging
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import src.demoqa.pages.RegistrationFormPage
import src.demoqa.utils.FakerUtil
import java.nio.file.Paths
import kotlin.test.assertTrue

@Epic("Тестирование формы регистрации")
@Feature("Заполнение полей формы")
@Owner("QA Team")
@Issue("REGFORM-123")
@TmsLink("TC-456")
class RegistrationFillingFormTest : BaseTest(), Logging {
    @Test
    @Story("Пользователь может полностью заполнить форму регистрации")
    @DisplayName("Полное заполнение формы регистрации")
    @Description("Этот тест проверяет корректное заполнение всех полей формы регистрации.")
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://demoqa.com/automation-practice-form", name = "Registration Form")
    fun `testCompleteRegistrationForm`() {
        logger.info { "Начало теста полного заполнения формы регистрации" }

        val firstName = FakerUtil.generateName()
        val lastName = FakerUtil.generateLastName()
        val email = FakerUtil.generateEmail()
        val phoneNumber = FakerUtil.generatePhoneNumber()
        val currentAddress = FakerUtil.generateCurrentAddress()
        val dateOfBirth = "03 March 1998"
        val expectedDate = "03 Mar 1998"
        val gender = "Female"
        val hobbies = listOf("Sports", "Reading", "Music")
        val subjects = listOf("Maths", "English", "Hindi")
        val state = "NCR"
        val city = "Delhi"
        val imagePath = Paths.get("src/test/resources/Battle_Star.jpg")
        val img = "Battle_Star"
        val title = "Thanks for submitting the form"
        val registrationFormPage = RegistrationFormPage(page)

        step("Заполнение поля 'Имя' значением $firstName")
        logger.info { "Заполнение поля 'Имя' значением $firstName" }
        registrationFormPage.enterFirstName(firstName)

        step("Заполнение поля 'Фамилия' значением $lastName")
        logger.info { "Заполнение поля 'Фамилия' значением $lastName" }
        registrationFormPage.enterLastName(lastName)

        step("Заполнение поля 'Email' значением $email")
        logger.info { "Заполнение поля 'Email' значением $email" }
        registrationFormPage.enterEmail(email)

        step("Выбор пола $gender")
        logger.info { "Выбор пола '$gender'" }
        registrationFormPage.selectGenderOption(gender)

        step("Заполнение поля 'Номер телефона' значением $phoneNumber")
        logger.info { "Заполнение поля 'Номер телефона' значением $phoneNumber" }
        registrationFormPage.enterPhoneNumber(phoneNumber)

        step("Выбор даты рождения $dateOfBirth")
        logger.info { "Выбор даты рождения '$dateOfBirth'" }
        registrationFormPage.selectDateOfBirth(dateOfBirth)

        step("Заполнение поля 'Subjects' значением $subjects")
        logger.info { "Заполнение поля 'Subjects' значениями: $subjects" }
        registrationFormPage.fillSubjects(subjects)

        step("Выбор хобби $hobbies")
        hobbies.forEach { hobby ->
            logger.info { "Выбор хобби '$hobby'" }
            registrationFormPage.selectHobby(hobby)
        }

        step("Загрузка изображения $imagePath")
        logger.info { "Загрузка изображения '$imagePath'" }
        registrationFormPage.uploadPicture(imagePath)

        step("Заполнение поля 'Текущий адрес' значением $currentAddress")
        logger.info { "Заполнение поля 'Текущий адрес' значением $currentAddress" }
        registrationFormPage.enterCurrentAddress(currentAddress)

        step("Выбор штата и города $state и $city")
        logger.info { "Выбор штата '$state' и города '$city'" }
        registrationFormPage.selectState(state)
        registrationFormPage.selectCity(city)

        step("Нажатие кнопки 'Заполнить форму регистрации'")
        logger.info { "Нажатие кнопки 'Заполнить форму регистрации'" }
        registrationFormPage.clickSubmit()
        Thread.sleep(3000)
        step("Проверка отсутствия ошибок при заполнении формы")
        logger.info { "Проверка отсутствия ошибок при заполнении формы" }
        assertFalse(
            registrationFormPage.hasErrors(),
            "На странице обнаружены ошибки при заполнении формы",
        )

        step("Проверка title страницы")
        logger.info { "Проверка title страницы" }
        assertTrue(
            registrationFormPage.verifyRegistrationForm(title),
            "Title страницы не совпадает с $title",
        )

        step("Проверка всех заполненных полей")
        logger.info { "Проверка всех заполненных полей" }
        assertTrue(
            registrationFormPage.verifyRegistrationFormAll(
                name = firstName,
                lastName = lastName,
                email = email,
                phoneNumber = phoneNumber,
                currentAddress = currentAddress, birthDate = expectedDate,
                gender = gender,
                hobby = hobbies,
                subjects = subjects,
                state = state,
                city = city,
                img = img,
            ),
            "Проверка заполненных полей формы регистрации не прошла успешно",
        )

        logger.info { "Тест полного заполнения формы регистрации завершен успешно" }
    }
}
