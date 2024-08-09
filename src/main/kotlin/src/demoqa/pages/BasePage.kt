package src.demoqa.pages

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import io.qameta.allure.kotlin.Step

abstract class BasePage(private val page: Page) {
    private fun findElement(locator: String): Locator {
        return page.locator(locator)
    }

    @Step("Заполнение поля с локатором '{locator}' значением '{value}'")
    protected fun fill(
        locator: String,
        value: String,
    ) {
        findElement(locator).fill(value)
    }

    @Step("Получение значения поля с локатором '{locator}'")
    protected fun inputValue(locator: String): String {
        return findElement(locator).inputValue()
    }

    @Step("Клик по элементу с локатором '{locator}'")
    protected fun click(locator: String) {
        findElement(locator).click()
    }

    @Step("Клик по элементу с текстом '{text}'")
    protected fun clickByText(text: String) {
        page.getByText(text).click()
    }

    @Step("Проверка, выбран ли элемент с локатором '{locator}'")
    protected fun isChecked(locator: String): Boolean {
        return findElement(locator).isChecked
    }
}
