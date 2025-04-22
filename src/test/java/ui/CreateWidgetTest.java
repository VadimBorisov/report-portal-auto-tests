package ui;

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

    @Test
    void successfullyCreateWidget() {
        LoginPage loginPage = new LoginPage();
        DashboardsPage dashboardsPage = loginPage.loginInSystem("default", "1q2w3e");
        DashboardPage dashboardPage = dashboardsPage.openDashboards().selectDashboard();
        String widgetName = dashboardPage.addWidgetToDashboard();

        assertTrue(dashboardPage.widgetIsPresent(widgetName));
    }
}
