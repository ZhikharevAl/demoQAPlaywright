import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import src.demoqa.pages.RegistrationFormPage
import src.demoqa.utils.FakerUtil

class RegistrationFormTest : BaseTest() {
    @Test
    fun testFirstNameAndLastName() {
        val firstName = FakerUtil.generateName()
        val lastName = FakerUtil.generateLastName()
        val registrationFormPage = RegistrationFormPage(page)

        registrationFormPage.enterFirstName(firstName)
        registrationFormPage.enterLastName(lastName)

        assertEquals(firstName, registrationFormPage.getFirstNameValue())
        assertEquals(lastName, registrationFormPage.getLastNameValue())
    }

    @Test
    fun testEmail() {
        val email = FakerUtil.generateEmail()
        val registrationFormPage = RegistrationFormPage(page)

        registrationFormPage.enterEmail(email)

        assertEquals(email, registrationFormPage.getEmailValue())
    }

    @Test
    fun testPhoneNumber() {
        val phoneNumber = FakerUtil.generatePhoneNumber()
        val registrationFormPage = RegistrationFormPage(page)

        registrationFormPage.enterPhoneNumber(phoneNumber)

        assertEquals(phoneNumber, registrationFormPage.getPhoneNumberValue())
    }

    @Test
    fun testCurrentAddress() {
        val currentAddress = FakerUtil.generateCurrentAddress()
        val registrationFormPage = RegistrationFormPage(page)

        registrationFormPage.enterCurrentAddress(currentAddress)

        assertEquals(currentAddress, registrationFormPage.getCurrentAddressValue())
    }
}
