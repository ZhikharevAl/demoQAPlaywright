package src.demoqa.pages

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import io.qameta.allure.kotlin.Step

abstract class BasePage(private val page: Page) {
    protected fun findElement(locator: String): Locator {
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

    @Step("Проверка, выбран ли элемент с локатором '{locator}'")
    protected fun isChecked(locator: String): Boolean {
        return findElement(locator).isChecked
    }

    @Step("Нажатие на клавишу ENTER")
    protected fun pressEnter() {
        page.keyboard().press("Enter")
    }

    protected fun getElementsTexts(locator: String): List<String> {
        return page.locator(locator).allInnerTexts()
    }
}
