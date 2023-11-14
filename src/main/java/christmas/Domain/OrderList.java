package christmas.Domain;

import christmas.Constant.ErrorMessage;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OrderList {
    private static Integer MIN_MENU_QUANTITY = 1;
    private static Integer MAX_ORDER_QUANTITY = 20;
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
        if (isOrderFull()) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_IS_FULL);
        }
    }

    public Integer getTotalPrice() {
        Integer totalPrice = 0;

        for (EnumMap.Entry<Menu, Integer> entry : orderList.entrySet()) {
            Menu menu = entry.getKey();
            Integer quantity = entry.getValue();

            totalPrice += menu.getPrice() * quantity;
        }
        return totalPrice;
    }

    public Map<String, Integer> getOrderQuantity() {
        Map<String, Integer> menuInfo = new HashMap<>();

        for (Map.Entry<Menu, Integer> entry : orderList.entrySet()) {
            if (entry.getValue() >= MIN_MENU_QUANTITY) {
                menuInfo.put(entry.getKey().getName(), entry.getValue());
            }
        }

        return menuInfo;
    }

    public Integer getQuantityOfMenuType(String menuType) {
        Integer totalQuantity = 0;
        
        for (Map.Entry<Menu, Integer> entry : orderList.entrySet()) {
            String eachMenuType = entry.getKey().getType();

            if (Objects.equals(eachMenuType, menuType)) {
                totalQuantity += entry.getValue();
            }
        }
        return totalQuantity;
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

    private boolean isOrderFull() {
        Integer totalQuantity = 0;

        for (Integer quantity : orderList.values()) {
            totalQuantity += quantity;
            if (totalQuantity > MAX_ORDER_QUANTITY) {
                return true;
            }
        }

        return false;
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
        if (quantity < MIN_MENU_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.INVALIDATE_ORDER);
        }
    }

    private boolean isAlreadyOrder(String menu) {
        return orderList.get(Menu.findByMenuName(menu)) != 0;
    }

}
