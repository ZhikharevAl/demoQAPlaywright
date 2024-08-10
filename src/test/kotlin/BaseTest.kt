import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseTest {
    protected lateinit var page: Page
    private lateinit var browser: Browser
    private lateinit var playwright: Playwright

    @BeforeEach
    fun setup() {
        playwright = Playwright.create()
        browser =
            playwright.chromium().launch(
                BrowserType.LaunchOptions().apply {
                    headless = true
                },
            )
        page = browser.newPage()
        page.navigate("https://demoqa.com/automation-practice-form")
    }

    @AfterEach
    fun teardown() {
        page.close()
        browser.close()
        playwright.close()
    }
}
