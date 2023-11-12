package christmas.Domain;

import java.util.EnumMap;
import java.util.List;

public class OrderList {
    private EnumMap<Menu, Integer> orderList;

    public OrderList() {
        orderList = new EnumMap<>(Menu.class);
        for (Menu menu : Menu.values()) {
            orderList.put(menu, 0);
        }
    }

    public void orderMenu(List<String> orders) {
    }


}
