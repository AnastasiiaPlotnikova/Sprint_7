package testdata;

import org.apache.commons.lang3.RandomStringUtils;
import model.Courier;
import model.Login;

public class LoginTestData {
    private static final String LOGIN = RandomStringUtils.randomAlphanumeric(5);
    private static final String PASSWORD = RandomStringUtils.randomAlphanumeric(5);

    public static Login correctLogin(Courier courier) {
        Login login = new Login();
        login.setLogin(courier.getLogin());
        login.setPassword(courier.getPassword());
        return login;
    }

    public static Login invalidLoginPassword() {
        Login login = new Login();
        login.setLogin(LOGIN);
        login.setPassword(PASSWORD);
        return login;
    }

    public static Login requestWithoutPassword() {
        Login login = new Login();
        login.setLogin(LOGIN);
        login.setPassword("");
        return login;
    }

    public static Login requestWithoutLogin() {
        Login login = new Login();
        login.setLogin("");
        login.setPassword(PASSWORD);
        return login;
    }
}
