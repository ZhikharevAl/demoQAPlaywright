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
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import src.demoqa.pages.RegistrationFormPage
import src.demoqa.utils.FakerUtil
import java.nio.file.Paths
import kotlin.test.assertTrue

@Epic("Тестирование формы регистрации")
@Feature("Заполнение полей формы")
@Owner("QA Team")
@Issue("REGFORM-123")
@TmsLink("TC-456")
class RegistrationFormTest : BaseTest(), Logging {
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
    @Severity(SeverityLevel.BLOCKER)
    @Link("https://demoqa.com/automation-practice-form", name = "Registration Form")
    fun `overviewTest`() {
        // Этот тест может быть пустым или содержать общую логику
        // Его основная цель - предоставить общее описание для отчета Allure
    }

    @Test
    @Story("Пользователь может заполнить имя и фамилию")
    @DisplayName("Тест заполнения полей 'Имя' и 'Фамилия'")
    @Description("Этот тест проверяет корректность заполнения полей 'Имя' и 'Фамилия' в форме регистрации.")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("REGFORM-124")
    @TmsLink("TC-457")
    fun `testFirstNameAndLastName`() {
        logger.info { "Начало теста заполнения полей 'Имя' и 'Фамилия'" }

        val firstName = FakerUtil.generateName()
        val lastName = FakerUtil.generateLastName()
        val registrationFormPage = RegistrationFormPage(page)

        logger.debug { "Сгенерированное имя: $firstName, фамилия: $lastName" }

        step("Заполнение поля 'Имя' значением $firstName")
        registrationFormPage.enterFirstName(firstName)
        logger.info { "Поле 'Имя' заполнено значением: $firstName" }

        step("Заполнение поля 'Фамилия' значением $lastName")
        registrationFormPage.enterLastName(lastName)
        logger.info { "Поле 'Фамилия' заполнено значением: $lastName" }

        step("Проверка корректности заполнения поля 'Имя'")
        assertEquals(
            firstName,
            registrationFormPage.getFirstNameValue(),
            "Имя $firstName не было корректно заполнено",
        )
        logger.info { "Проверка поля 'Имя' прошла успешно" }

        step("Проверка корректности заполнения поля 'Фамилия'")
        assertEquals(
            lastName,
            registrationFormPage.getLastNameValue(),
            "Фамилия $lastName не была корректно заполнена",
        )
        logger.info { "Проверка поля 'Фамилия' прошла успешно" }

        logger.info { "Тест заполнения полей 'Имя' и 'Фамилия' завершен успешно" }
    }

    @Test
    @Story("Пользователь может заполнить email")
    @DisplayName("Тест заполнения поля 'Email'")
    @Description("Этот тест проверяет корректность заполнения поля 'Email' в форме регистрации.")
    @Severity(SeverityLevel.NORMAL)
    @Issue("REGFORM-125")
    @TmsLink("TC-458")
    fun `testEmail`() {
        logger.info { "Начало теста заполнения поля 'Email'" }

        val email = FakerUtil.generateEmail()
        val registrationFormPage = RegistrationFormPage(page)

        logger.debug { "Сгенерированный email: $email" }

        step("Заполнение поля 'Email' значением $email")
        registrationFormPage.enterEmail(email)
        logger.info { "Поле 'Email' заполнено значением: $email" }

        step("Проверка корректности заполнения поля 'Email'")
        assertEquals(
            email,
            registrationFormPage.getEmailValue(),
            "Email $email не был корректно заполнен",
        )
        logger.info { "Проверка поля 'Email' прошла успешно" }

        logger.info { "Тест заполнения поля 'Email' завершен успешно" }
    }

    @Test
    @Story("Пользователь может заполнить номер телефона")
    @DisplayName("Тест заполнения поля 'Номер телефона'")
    @Description("Этот тест проверяет корректность заполнения поля 'Номер телефона' в форме регистрации.")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("REGFORM-126")
    @TmsLink("TC-459")
    fun `testPhoneNumber`() {
        logger.info { "Начало теста заполнения поля 'Номер телефона'" }

        val phoneNumber = FakerUtil.generatePhoneNumber()
        val registrationFormPage = RegistrationFormPage(page)

        logger.debug { "Сгенерированный номер телефона: $phoneNumber" }

        step("Заполнение поля 'Номер телефона' значением $phoneNumber")
        registrationFormPage.enterPhoneNumber(phoneNumber)
        logger.info { "Поле 'Номер телефона' заполнено значением: $phoneNumber" }

        step("Проверка корректности заполнения поля 'Номер телефона'")
        assertEquals(
            phoneNumber,
            registrationFormPage.getPhoneNumberValue(),
            "Номер телефона $phoneNumber не был корректно заполнен",
        )
        logger.info { "Проверка поля 'Номер телефона' прошла успешно" }

        logger.info { "Тест заполнения поля 'Номер телефона' завершен успешно" }
    }

    @Test
    @Story("Пользователь может заполнить текущий адрес")
    @DisplayName("Тест заполнения поля 'Текущий адрес'")
    @Description("Этот тест проверяет корректность заполнения поля 'Текущий адрес' в форме регистрации.")
    @Severity(SeverityLevel.NORMAL)
    @Issue("REGFORM-127")
    @TmsLink("TC-460")
    fun `testCurrentAddress`() {
        logger.info { "Начало теста заполнения поля 'Текущий адрес'" }

        val currentAddress = FakerUtil.generateCurrentAddress()
        val registrationFormPage = RegistrationFormPage(page)

        logger.debug { "Сгенерированный текущий адрес: $currentAddress" }

        step("Заполнение поля 'Текущий адрес' значением $currentAddress")
        registrationFormPage.enterCurrentAddress(currentAddress)
        logger.info { "Поле 'Текущий адрес' заполнено значением: $currentAddress" }

        step("Проверка корректности заполнения поля 'Текущий адрес'")
        assertEquals(
            currentAddress,
            registrationFormPage.getCurrentAddressValue(),
            "Текущий адрес $currentAddress не был корректно заполнен",
        )
        logger.info { "Проверка поля 'Текущий адрес' прошла успешно" }

        logger.info { "Тест заполнения поля 'Текущий адрес' завершен успешно" }
    }

    @ParameterizedTest
    @ValueSource(strings = ["Male", "Female", "Other"])
    @Story("Пользователь может выбрать пол")
    @DisplayName("Тест выбора пола")
    @Description("Этот тест проверяет возможность выбора различных опций пола")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("REGFORM-128")
    @TmsLink("TC-461")
    fun `testGenderOption`(gender: String) {
        logger.info { "Начало теста выбора пола: $gender" }

        val registrationFormPage = RegistrationFormPage(page)

        step("Выбор пола '$gender'")
        registrationFormPage.selectGenderOption(gender)
        logger.info { "Выбран пол: $gender" }

        step("Проверка выбора пола '$gender'")
        assertEquals(
            gender,
            registrationFormPage.getGenderValue(),
            "Пол '$gender' не был выбран",
        )
        logger.info { "Проверка выбора пола '$gender' прошла успешно" }

        logger.info { "Тест выбора пола '$gender' завершен успешно" }
    }

    @ParameterizedTest
    @ValueSource(strings = ["Sports", "Reading", "Music"])
    @DisplayName("Выбор хобби")
    @Description("Этот тест проверяет возможность выбора различных хобби")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Пользователь может выбрать различные хобби")
    @Issue("REGFORM-129")
    @TmsLink("TC-462")
    fun `testSelectHobby`(hobby: String) {
        logger.info { "Начало теста выбора хобби: $hobby" }

        val registrationFormPage = RegistrationFormPage(page)

        step("Выбор хобби '$hobby'")
        registrationFormPage.selectHobby(hobby)
        logger.info { "Выбрано хобби: $hobby" }

        step("Проверка выбора хобби '$hobby'")
        assertTrue(
            registrationFormPage.isHobbySelected(hobby),
            "Хобби '$hobby' не было выбрано",
        )
        logger.info { "Проверка выбора хобби '$hobby' прошла успешно" }

        logger.info { "Тест выбора хобби '$hobby' завершен успешно" }
    }

    @Test
    @DisplayName("Выбор всех хобби")
    @Description("Этот тест проверяет возможность выбора всех хобби одновременно")
    @Severity(SeverityLevel.NORMAL)
    @Story("Пользователь может выбрать все хобби")
    @Issue("REGFORM-130")
    @TmsLink("TC-463")
    fun `testSelectAllHobbies`() {
        logger.info { "Начало теста выбора всех хобби" }

        val registrationFormPage = RegistrationFormPage(page)
        val hobbies = listOf("Sports", "Reading", "Music")

        step("Выбор всех хобби")
        hobbies.forEach { hobby ->
            registrationFormPage.selectHobby(hobby)
            logger.info { "Выбрано хобби: $hobby" }
        }

        step("Проверка выбора всех хобби")
        hobbies.forEach { hobby ->
            assertTrue(
                registrationFormPage.isHobbySelected(hobby),
                "Хобби '$hobby' не было выбрано",
            )
            logger.info { "Проверка выбора хобби '$hobby' прошла успешно" }
        }

        logger.info { "Тест выбора всех хобби завершен успешно" }
    }

    @Test
    @DisplayName("Заполнение поля Subjects")
    @Description("Этот тест проверяет корректность заполнения поля 'Subjects'")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Пользователь может заполнить поле 'Subjects'")
    @Issue("REGFORM-131")
    @TmsLink("TC-464")
    fun `testSubjects`() {
        logger.info { "Начало теста заполнения поля 'Subjects'" }

        val registrationFormPage = RegistrationFormPage(page)
        val subjects = listOf("Maths", "English", "Hindi")

        step("Заполнение поля 'Subjects'")
        registrationFormPage.fillSubjects(subjects)
        logger.info { "Заполнено поле 'Subjects' значениями: $subjects" }

        step("Проверка количества введенных предметов")
        val enteredSubjects = registrationFormPage.getSubjects()
        assertEquals(
            subjects.size,
            enteredSubjects.size,
            "Количество введенных '$subjects' не совпадает",
        )
        logger.info { "Проверка количества введенных предметов прошла успешно" }

        step("Проверка корректности введенных предметов")
        registrationFormPage.verifySubjects(subjects)
        subjects.forEach { expectedSubject ->
            val actualSubjects = registrationFormPage.getSubjects()
            assertTrue(
                actualSubjects.contains(expectedSubject),
                "Ожидаемый предмет '$expectedSubject' не найден среди актуальных предметов",
            )
            logger.info { "Проверка наличия предмета '$expectedSubject' прошла успешно" }
        }

        logger.info { "Тест заполнения поля 'Subjects' завершен успешно" }
    }

    @Test
    @DisplayName("Загрузка изображения")
    @Description("Этот тест проверяет возможность загрузки изображения")
    @Severity(SeverityLevel.NORMAL)
    @Story("Пользователь может загрузить изображение")
    @Issue("REGFORM-133")
    @TmsLink("TC-466")
    fun `testUploadPicture`() {
        logger.info { "Начало теста загрузки изображения" }

        val registrationFormPage = RegistrationFormPage(page)
        val imagePath = Paths.get("src/test/resources/Battle_Star.jpg")

        step("Загрузка изображения")
        registrationFormPage.uploadPicture(imagePath)
        logger.info { "Загружено изображение: $imagePath" }

        step("Проверка загрузки изображения")
        logger.info { "Проверка загрузки изображения" }
        registrationFormPage.isPictureUploaded("src/test/resources/Battle_Star.jpg")

        logger.info { "Тест загрузки изображения завершен успешно" }
    }

    @ParameterizedTest
    @MethodSource("data.TestDataProvider#stateAndCityProvider")
    @DisplayName("Проверка выбора штата и города")
    @Description("Этот тест проверяет, что после выбора значения для штата и города они сохраняются корректно")
    @Severity(SeverityLevel.NORMAL)
    @Story("Пользователь может выбрать штат и город")
    @Issue("REGFORM-134")
    @TmsLink("TC-467")
    fun `testStateAndCitySelection`(
        state: String,
        city: String,
    ) {
        logger.info { "Начало теста выбора штата и города" }
        val registrationFormPage = RegistrationFormPage(page)

        step("Выбор штата и города")
        logger.info { "Выбор штата '$state' и города '$city'" }
        registrationFormPage.selectState(state)
        registrationFormPage.selectCity(city)

        step("Проверка выбранного штата")
        logger.info { "Проверка выбора штата '$state'" }
        assertTrue(
            registrationFormPage.verifyState(state),
            "Штат '$state' не был корректно выбран",
        )

        step("Проверка выбранного города")
        logger.info { "Проверка выбора города '$city'" }
        assertTrue(
            registrationFormPage.verifyCity(city),
            "Город '$city' не был корректно выбран",
        )
        logger.info { "Тест выбора штата и города завершен успешно" }
    }

    @Test
    @DisplayName("Выбор даты рождения")
    @Description("Этот тест проверяет корректность выбора даты рождения")
    @Severity(SeverityLevel.NORMAL)
    @Story("Пользователь может выбрать дату рождения")
    @Issue("REGFORM-135")
    @TmsLink("TC-468")
    fun `testDateOfBirth`() {
        logger.info { "Начало теста выбора даты рождения" }
        val registrationFormPage = RegistrationFormPage(page)
        val date = "03 March 1998"
        val expectedDate = "03 Mar 1998"

        step("Выбор даты рождения")
        logger.info { "Выбор даты рождения '$date'" }
        registrationFormPage.selectDateOfBirth(date)

        step("Проверка выбранной даты рождения")
        logger.info { "Проверка выбора даты рождения '$expectedDate'" }
        assertTrue(
            registrationFormPage.verifyDateOfBirth(expectedDate),
            "Дата рождения '$expectedDate' не была корректно выбрана",
        )
        logger.info { "Тест выбора даты рождения завершен успешно" }
    }
}
