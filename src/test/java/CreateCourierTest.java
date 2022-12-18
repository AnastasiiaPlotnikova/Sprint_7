import client.CourierClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.Login;
import model.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testdata.CourierTestData;

import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest {
    Courier courier;
    Integer id;
    CourierClient courierClient;


    @Before
    public void setup() {
        courier = Courier.getCourierRequestAllRequiredField();
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        if (id != null) {
            courierClient.deleteCourier(Login.getLoginRequestAllRequiredField(CourierTestData.getCourierRequestAllRequiredField()))
                    .then().statusCode(200);
        }
    }

    @Test
    @DisplayName("Создание нового курьера")
    @Description("Курьера можно создать")
    public void createNewCorrectCourier() {
        courierClient.getCourierForLogin(CourierTestData.getCourierRequestAllRequiredField())
                .then().statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание курьера с уже существующим логином")
    @Description("Если создать пользователя с логином, который уже есть, возвращается ошибка. Нельзя создать двух одинаковых курьеров")
    public void createDuplicateCourier() {
        courierClient.getCourierForLogin(CourierTestData.getCourierRequestAllRequiredField());
        courierClient.getCourierForLogin(CourierTestData.getCourierRequestAllRequiredField())
                .then().statusCode(409)
                .and()
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Создание курьера с пустым логином")
    @Description("Чтобы создать курьера, нужно передать в ручку все обязательные поля. Если одного из полей нет, запрос возвращает ошибку")
    public void createCourierWithoutLogin() {
        courierClient.getCourierForLogin(CourierTestData.getCourierRequestWithoutLogin())
                .then().statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера с пустым паролем")
    @Description("Чтобы создать курьера, нужно передать в ручку все обязательные поля. Если одного из полей нет, запрос возвращает ошибку")
    public void createCourierWithoutPassword() {
        courierClient.getCourierForLogin(CourierTestData.getCourierRequestWithoutPassword())
                .then().statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }


}