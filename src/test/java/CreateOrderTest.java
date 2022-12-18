import client.OrdersClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import testdata.OrderTestData;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    Order order;
    OrdersClient ordersClient;
    private String[] color;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getColor() {
        return new Object[][]{{new String[]{}}, {new String[]{"BLACK"}}, {new String[]{"GREY"}}, {new String[]{"BLACK", "GREY"}},};
    }

    @Before
    public void setup() {
        order = OrderTestData.createNewOrder(color);
        ordersClient = new OrdersClient();
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Можно не указывать цвета, указать BLACK, указать GREY, указать оба цвета, тело ответа содержит track")
    public void createOrder() {
        ordersClient.getCorrectNewOrder(OrderTestData.createNewOrder(color)).then().statusCode(201).and().body("track", notNullValue());
    }
}