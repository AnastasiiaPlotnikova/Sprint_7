package config;

public class Config {
    public static final String BASE_URL = "http://qa-scooter.praktikum-services.ru/api/v1/";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static final String COURIER = "courier";
    public static final String COURIER_LOGIN = COURIER + "/login";
    public static final String ORDERS_LIST = "orders";
}
