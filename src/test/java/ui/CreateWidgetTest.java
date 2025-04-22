package ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.home.vadim.pages.DashboardPage;
import ru.home.vadim.pages.DashboardsPage;
import ru.home.vadim.pages.LoginPage;
import ru.home.vadim.webdriver.WebDriverSingleton;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateWidgetTest {
    @AfterEach
    void closeBrowser() {
        WebDriverSingleton.getInstance().close();
    }

    @Epic("Работа с Dashboards")
    @Feature("Добавление Dashboard и Widget через UI")
    @Story("Успешное добавление Widget")
    @Test
    void successfullyCreateWidget() {
        LoginPage loginPage = new LoginPage();
        DashboardsPage dashboardsPage = loginPage.loginInSystem("default", "1q2w3e").openDashboards();
        dashboardsPage.clickAddNewDashboardButton();
        DashboardPage dashboardPage = dashboardsPage.createNewDashboard();
        String widgetName = dashboardPage.addWidgetToDashboard();

        assertTrue(dashboardPage.widgetIsPresent(widgetName));
    }

}
