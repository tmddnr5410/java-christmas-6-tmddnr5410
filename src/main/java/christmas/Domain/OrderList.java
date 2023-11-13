package christmas.Domain;

import christmas.Constant.ErrorMessage;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

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


    public void validateSelf() {
        if (isOnlyBeverage()) {
            throw new IllegalArgumentException(ErrorMessage.INVALIDATE_ORDER);
        }
    }

    public Map<String, Integer> getMenuInfo() {
        Map<String, Integer> menuInfo = new HashMap<>();

        for (Map.Entry<Menu, Integer> entry : orderList.entrySet()) {
            if (entry.getValue() > 0) {
                menuInfo.put(entry.getKey().getName(), entry.getValue());
            }
        }

        return menuInfo;
    }

    private boolean isOnlyBeverage() {
        for (EnumMap.Entry<Menu, Integer> entry : orderList.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();

            if (quantity > 0 && !MenuType.DRINK.equals(menu.getType())) {
                return false;
            }
        }
        return true;
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
