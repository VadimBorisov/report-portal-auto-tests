package ru.home.vadim.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @Step("Авторизоваться в системе")
    public DashboardsPage loginInSystem(String login, String password) {
        openPage();
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        clickOnLoginButton();

        return new DashboardsPage();
    }

    @Step("Открытие страницы авторизации")
    private LoginPage openPage() {
        driver.get("https://demo.reportportal.io/ui/#login");

        return this;
    }

    @Step("Нажать кнопку Login")
    private void clickOnLoginButton() {
        this.loginButton.click();
    }
}
