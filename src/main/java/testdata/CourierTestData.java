package testdata;

import org.apache.commons.lang3.RandomStringUtils;
import model.Courier;

public class CourierTestData {
    private static final String LOGIN = RandomStringUtils.randomAlphanumeric(5);
    private static final String PASSWORD = RandomStringUtils.randomAlphanumeric(5);
    private static final String FIRST_NAME = "Obi-Wan Kenobi";

    public static Courier getCourierRequestAllRequiredField() {
        Courier courier = new Courier();
        courier.setLogin(LOGIN);
        courier.setPassword(PASSWORD);
        courier.setFirstName(FIRST_NAME);
        return courier;
    }

    public static Courier getCourierRequestWithoutLogin() {
        Courier courier = new Courier();
        courier.setPassword(PASSWORD);
        courier.setFirstName(FIRST_NAME);
        return courier;
    }

    public static Courier getCourierRequestWithoutPassword() {
        Courier courier = new Courier();
        courier.setPassword(LOGIN);
        courier.setFirstName(FIRST_NAME);
        return courier;
    }
}