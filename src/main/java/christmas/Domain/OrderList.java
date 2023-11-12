package christmas.Domain;

import christmas.Constant.ErrorMessage;
import java.util.EnumMap;

public class OrderList {
    private EnumMap<Menu, Integer> orderList;

    public OrderList() {
        orderList = new EnumMap<>(Menu.class);
        for (Menu menu : Menu.values()) {
            orderList.put(menu, 0);
        }
    }

    public void order(String menu, String quantity) {
        validateMenu(menu);
    }

    public void validateMenu(String menu) {
        exceptIsNull(menu);
        exceptInvalidMenu(menu);
    }

    public void exceptIsNull(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INVALIDATE_ORDER);
        }
    }

    public void exceptInvalidMenu(String value) {
        if (Menu.isNotInMenu(value)) {
            throw new IllegalArgumentException(ErrorMessage.INVALIDATE_ORDER);
        }
    }

}
