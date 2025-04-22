package ru.home.vadim.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class DashboardsPage extends AbstractPage {
    @FindBy(xpath = "//div[contains(@class, 'gridRow')]/a")
    private List<WebElement> dashboardsList;
    @FindBy(xpath = "//a[contains(@href, 'dashboard')]")
    private WebElement dashboardButton;
    @FindBy(xpath = "//button[contains(span, 'Add New Dashboard')]")
    private WebElement addNewDashboardButton;
    @FindBy(xpath = "//input[contains(@placeholder, 'Enter dashboard name')]")
    private WebElement dashboardNameField;
    @FindBy(xpath = "//button[text()='Add']")
    private WebElement addButton;

    @Step("Добавляем новый Dashboard")
    public DashboardPage createNewDashboard() {
        setDashboardNameField(generateRandomString(6));
        addButton.click();

        return new DashboardPage();
    }

    @Step("Переход на Dashboards")
    public DashboardsPage openDashboards() {
        dashboardButton.click();
        return this;
    }

    @Step("Нажать кнопку создания нового Dashboard")
    public void clickAddNewDashboardButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewDashboardButton));
        addNewDashboardButton.click();
    }

    @Step("Задать имя Dashboard")
    public void setDashboardNameField(String dashboardName) {
        dashboardNameField.sendKeys(dashboardName);
    }

    private String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rnd = new Random();
        return IntStream.generate(() -> chars.charAt(rnd.nextInt(chars.length())))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
