package christmas.Domain;

import christmas.Constant.ErrorMessage;
import java.util.EnumMap;

public class OrderList {
    private static Integer MIN_ORDER_QUANTITY = 1;
    private EnumMap<Menu, Integer> orderList;

    public OrderList() {
        orderList = new EnumMap<>(Menu.class);
        for (Menu menu : Menu.values()) {
            orderList.put(menu, 0);
        }
    }

    public void order(String menu, Integer quantity) {
        validateMenu(menu);
        validateQuantity(quantity);
        orderList.put(Menu.findByMenuName(menu), quantity);
    }

    private void validateQuantity(Integer quantity) {
        exceptNotPositive(quantity);
    }

    private void validateMenu(String menu) {
        exceptIsNull(menu);
        exceptInvalidMenu(menu);
        exceptDuplicatedOrder(menu);
    }


    private void exceptIsNull(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INVALIDATE_ORDER);
        }
    }

    private void exceptInvalidMenu(String value) {
        if (Menu.isNotInMenu(value)) {
            throw new IllegalArgumentException(ErrorMessage.INVALIDATE_ORDER);
        }
    }


    private void exceptDuplicatedOrder(String menu) {
        if (isAlreadyOrder(menu)) {
            throw new IllegalArgumentException(ErrorMessage.INVALIDATE_ORDER);
        }
    }

    private void exceptNotPositive(Integer quantity) {
        if (quantity < MIN_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.INVALIDATE_ORDER);
        }
    }

    private boolean isAlreadyOrder(String menu) {
        return orderList.get(Menu.findByMenuName(menu)) != 0;
    }

}
