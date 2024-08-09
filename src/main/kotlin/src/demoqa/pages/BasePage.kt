package src.demoqa.pages

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page

abstract class BasePage(private val page: Page) {
    private fun findElement(locator: String): Locator {
        return page.locator(locator)
    }

    protected fun fill(
        locator: String,
        value: String,
    ) {
        findElement(locator).fill(value)
    }

    protected fun inputValue(locator: String): String {
        return findElement(locator).inputValue()
    }
}
