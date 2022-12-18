package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Order;

import static config.Config.ORDERS_LIST;
import static config.Config.getBaseUrl;
import static io.restassured.RestAssured.given;

public class OrdersClient {

    @Step("Создание нового заказа")
    public Response getCorrectNewOrder(Order order) {
        Response response = given().header("Content-type", "application/json").baseUri(getBaseUrl()).body(order).post(ORDERS_LIST);
        response.then();
        return response;
    }

    @Step("Получение списка заказов")
    public Response getAllOrders() {
        Response response = given().baseUri(getBaseUrl()).header("Content-type", "application/json").get(ORDERS_LIST);
        response.then();
        return response;
    }
}
