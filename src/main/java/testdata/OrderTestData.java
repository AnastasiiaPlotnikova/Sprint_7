package testdata;

import model.Order;

public class OrderTestData {
    public static final String FIRST_NAME = "Анджелина";
    public static final String LAST_NAME = "Джоли";
    public static final String ADDRESS = "Марксистская дом 1";
    public static final String METRO_STATION = "145";
    public static final String PHONE = "+79876543210";
    public static final int RENT_TIME = 1;
    public static final String DELIVERY_DATE = "2022-12-16";
    public static final String COMMENT = "Домофон не работает, позвонить на мобильный";

    public static Order createNewOrder(String[] color) {
        Order order = new Order();
        order.setFirstName(FIRST_NAME);
        order.setLastName(LAST_NAME);
        order.setAddress(ADDRESS);
        order.setMetroStation(METRO_STATION);
        order.setPhone(PHONE);
        order.setRentTime(RENT_TIME);
        order.setDeliveryDate(DELIVERY_DATE);
        order.setComment(COMMENT);
        order.setColor(color);
        return order;
    }
}