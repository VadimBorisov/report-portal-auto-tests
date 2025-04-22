package ru.home.vadim.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {
    @FindBy(xpath = "//input[@placeholder='Login']")
    private WebElement login;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @Step("Авторизоваться в системе")
    public DashboardsPage loginInSystem(String login, String password) {
        this
                .openPage()
                .setLogin(login)
                .setPassword(password)
                .clickOnLoginButton();

        return new DashboardsPage();
    }

    @Step("Открытие страницы авторизации")
    private LoginPage openPage() {
        driver.get("https://demo.reportportal.io/ui/#login");

        return this;
    }

    @Step("Ввести логин")
    private LoginPage setLogin(String login) {
        wait.until(ExpectedConditions.elementToBeClickable(this.login));

        this.login.clear();
        this.login.sendKeys(login);

        return this;
    }

    @Step("Ввести пароль")
    private LoginPage setPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);

        return this;
    }

    @Step("Нажать кнопку Login")
    private void clickOnLoginButton() {
        this.loginButton.click();
    }
}
