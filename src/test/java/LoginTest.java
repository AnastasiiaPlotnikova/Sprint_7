import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.Test;
import model.Courier;
import model.Login;
import testdata.CourierTestData;
import testdata.LoginTestData;

import static config.Config.getBaseUrl;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static testdata.CourierTestData.getCourierRequestAllRequiredField;
import static testdata.LoginTestData.*;

public class LoginTest {
    private static final String COURIER_LOGIN = "courier/login";
    private static final String COURIER = "courier";

    @AfterClass
    public static void setId() {
        Login login = LoginTestData.correctLogin(CourierTestData.getCourierRequestAllRequiredField());

        int id = given().header("Content-type", "application/json").baseUri(getBaseUrl()).body(login).post("courier/login").then().assertThat().statusCode(200).and().body("id", notNullValue()).extract().path("id");

        given().header("Content-type", "application/json").baseUri(getBaseUrl()).delete("courier/" + id);
    }

    @Test
    @DisplayName("Авторизация курьера")
    @Description("Курьер может авторизоваться с валидными логином и паролем, успешный запрос возвращает id")
    public void courierAuthWithCorrectPasswordLogin() {
        Courier courier = getCourierRequestAllRequiredField();

        given().header("Content-type", "application/json").baseUri(getBaseUrl()).body(courier).post(COURIER);

        Login login = LoginTestData.correctLogin(CourierTestData.getCourierRequestAllRequiredField());

        Response response = given().header("Content-type", "application/json").baseUri(getBaseUrl()).body(login).post(COURIER_LOGIN);
        response.then().statusCode(200).and().assertThat().body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация c неверным паролем")
    @Description("Система вернёт ошибку, если неправильно указать логин или пароль;")
    public void courierAuthWithInorrectPassword() {
        Courier courier = getCourierRequestAllRequiredField();

        given().header("Content-type", "application/json").baseUri(getBaseUrl()).body(courier).post(COURIER);

        Login login = LoginTestData.correctLogin(CourierTestData.getCourierRequestAllRequiredField());
        login.setPassword("0");

        Response response = given().header("Content-type", "application/json").baseUri(getBaseUrl()).body(login).post(COURIER_LOGIN);
        response.then().statusCode(404).and().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Неуспешная авторизация с неверным логином")
    @Description("Если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;")
    public void courierAuthWithNotExistedLogin() {
        Response response = given().header("Content-type", "application/json").baseUri(getBaseUrl()).body(invalidLoginPassword()).post(COURIER_LOGIN);
        response.then().statusCode(404).and().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера без пароля")
    @Description("Если какого-то поля нет, запрос возвращает ошибку")
    public void courierAuthWithoutPassword() {
        Response response = given().header("Content-type", "application/json").baseUri(getBaseUrl()).body(requestWithoutPassword()).post(COURIER_LOGIN);
        response.then().statusCode(400).and().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера без логина")
    @Description("Если какого-то поля нет, запрос возвращает ошибку")
    public void courierAuthWithoutLogin() {
        Response response = given().header("Content-type", "application/json").baseUri(getBaseUrl()).body(requestWithoutLogin()).post(COURIER_LOGIN);
        response.then().statusCode(400).and().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
}