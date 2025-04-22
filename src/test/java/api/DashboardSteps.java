package api;

import io.qameta.allure.Step;
import ru.home.vadim.models.Dashboard;
import ru.home.vadim.models.DashboardRequest;

import java.util.Random;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardSteps extends BaseTest {
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

    @Step("GET запрос получения Dashboard по id")
    Dashboard getDashboard(Integer id) {
        return given()
                .pathParam("projectName", "default_personal")
                .pathParam("dashboardId", id)
                .when().get("/{projectName}/dashboard/{dashboardId}").then().extract().body().as(Dashboard.class);
    }

    @Step("Проверка что созданный и полученный Dashboard равны")
    void verifyDashboard(Dashboard dashboard, DashboardRequest request) {
        assertEquals(request.getName(), dashboard.getName());
        assertEquals(request.getDescription(), dashboard.getDescription());
    }

    @Step("Генерируем данные для запроса с отсутствующим обязательным параметром")
    DashboardRequest generateDashboardRequestWithError() {
        DashboardRequest request = new DashboardRequest();
        request.setDescription(generateRandomString(25));

        return request;
    }

    @Step("POST запрос с ошибочными данными")
    String createDashboardWithError(DashboardRequest request) {
        return given()
                .body(request).pathParam("projectName", "default_personal").log().all()
                .when().post("{projectName}/dashboard")
                .then().log().all().assertThat().statusCode(400)
                .extract().body().jsonPath().getString("message");
    }

    @Step("Проверка сообщения об ошибке")
    void verifyErrorMessage(String message) {
        assertEquals("Incorrect Request. [Field 'name' should not be null.] ", message);
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
