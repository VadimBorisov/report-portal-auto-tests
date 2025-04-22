package ru.home.vadim.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardsPage extends AbstractPage {
    @FindBy(xpath = "//div[contains(@class, 'gridRow')]/a")
    private List<WebElement> dashboardsList;
    @FindBy(xpath = "//a[contains(@href, 'dashboard')]")
    private WebElement dashboardButton;

    @Step("Переход на Dashboards")
    public DashboardsPage openDashboards() {
        dashboardButton.click();
        return this;
    }

    @Step("Выбрать любой существующий Dashboard")
    public DashboardPage selectDashboard() {
        dashboardsList.getFirst().click();

        return new DashboardPage();
    }
}
