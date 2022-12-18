package model;

public class Login {
    private String login;
    private String password;

    public Login() {
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Login getLoginRequestAllRequiredField(Courier courier) {
        Login login = new Login();
        login.setLogin(courier.getLogin());
        login.setPassword(courier.getPassword());
        return login;
    }
}