package api;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import ru.home.vadim.model.Dashboard;
import ru.home.vadim.model.DashboardRequest;

import java.util.Random;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateDashboardTest extends BaseTest {

    @Test
    void successfullyCreateDashboard() {
        DashboardRequest request = generateDashboardRequest();
        Integer dashboardId = createDashboard(request);
        Dashboard dashboard = getDashboard(dashboardId);
        verifyDashboard(dashboard, request);
    }

    @Step("Генерируем данные для запроса")
    DashboardRequest generateDashboardRequest() {
        DashboardRequest request = new DashboardRequest();
        request.setName(generateRandomString(20));
        request.setDescription(generateRandomString(25));

        return request;
    }

    @Step("Отправляем POST запрос на создание Dashboard")
    Integer createDashboard(DashboardRequest request) {
        String dashboardId = given()
                .body(request).pathParam("projectName", "default_personal").log().all()
                .when().post("{projectName}/dashboard")
                .then().log().all().assertThat().statusCode(201).extract().body().jsonPath().getString("id");

        return Integer.parseInt(dashboardId);
    }

    @Step("GET запрос получения dashboard по id")
    Dashboard getDashboard(Integer id) {
        return given()
                .pathParam("projectName", "default_personal")
                .pathParam("dashboardId", id)
                .when().get("/{projectName}/dashboard/{dashboardId}").then().extract().body().as(Dashboard.class);
    }

    @Step("Проверка что созданный и полученный dashboard равны")
    void verifyDashboard(Dashboard dashboard, DashboardRequest request) {
        assertEquals(request.getName(), dashboard.getName());
        assertEquals(request.getDescription(), dashboard.getDescription());
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
