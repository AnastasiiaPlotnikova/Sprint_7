import client.OrdersClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersTest {
    OrdersClient ordersClient;

    @Before
    public void setUp() {
        ordersClient = new OrdersClient();
    }

    @Test
    @DisplayName("Получение списка заказов")
    @Description("В тело ответа возвращается список заказов")
    public void getListOrder() {
        ordersClient.getAllOrders()
                .then().statusCode(200)
                .and()
                .assertThat().body("orders", notNullValue());
    }
}