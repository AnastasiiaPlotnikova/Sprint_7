package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Login;
import model.Courier;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static config.Config.*;

public class CourierClient {
    @Step("Регистрация нового курьера")
    public Response getCourierForLogin(Courier courier) {
        return given().header("Content-type", "application/json").baseUri(getBaseUrl()).and().body(courier).when().post(COURIER);
    }

    @Step("Удаление курьера")
    public Response deleteCourier(Login login) {
        Integer id = given().header("Content-type", "application/json").baseUri(getBaseUrl()).body(login).post(COURIER_LOGIN).then().assertThat().statusCode(200).and().body("id", notNullValue()).extract().path("id");

        return given().header("Content-type", "application/json").baseUri(getBaseUrl()).delete(COURIER + '/' + id);
    }
}