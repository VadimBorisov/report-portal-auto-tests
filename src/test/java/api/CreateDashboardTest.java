package api;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import ru.home.vadim.models.Dashboard;
import ru.home.vadim.models.DashboardRequest;

public class CreateDashboardTest extends BaseTest {
    @Epic("Работа с Dashboards")
    @Feature("Создание Dashboards")
    @Story("Проверка успешного создания нового Dashboard")
    @Test
    void successfullyCreateDashboard() {
        DashboardSteps steps = new DashboardSteps();

        DashboardRequest request = steps.generateDashboardRequest();
        Integer dashboardId = steps.createDashboard(request);
        Dashboard dashboard = steps.getDashboard(dashboardId);
        steps.verifyDashboard(dashboard, request);
    }

    @Epic("Работа с Dashboards")
    @Feature("Создание Dashboards")
    @Story("Проверка невозможности создания нового Dashboard с недостаточными параметрами")
    @Test
    void negativeCreateDashboard() {
        DashboardSteps steps = new DashboardSteps();
        DashboardRequest request = steps.generateDashboardRequestWithError();
        String errorMessage = steps.createDashboardWithError(request);
        steps.verifyErrorMessage(errorMessage);
    }
}
