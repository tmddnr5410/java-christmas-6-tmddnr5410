package christmas.Model;

import christmas.Constant.ErrorMessage;
import christmas.Domain.Date;
import christmas.Domain.OrderList;
import java.util.Arrays;
import java.util.List;

public class PromotionModel {

    private final Integer ORDER_QUANTITY_INDEX = 1;
    private final Integer ORDER_MENU_INDEX = 0;
    private final Integer MENU_DIVIDE_SIZE = 2;
    private final String ORDER_DIVIDE_POINT = ",";
    private final String MENU_DIVIDE_POINT = "-";
    private Date date;
    private OrderList orderList;

    public PromotionModel() {
        orderList = new OrderList();
    }

    public void initDate(int input) {
        date = new Date(input);
    }

    public void initOrder(String input) {
        List<String> orders = Arrays.asList(input.split(ORDER_DIVIDE_POINT));
        validateOrderSplit(orders);

        for (String order : orders) {
            List<String> orderComponents = Arrays.asList(order.split(MENU_DIVIDE_POINT));

            validateMenuSplit(orderComponents);

            String menu = orderComponents.get(ORDER_MENU_INDEX);
            String quantity = orderComponents.get(ORDER_QUANTITY_INDEX);

            orderList.order(menu, quantity);
        }

    }

    private void validateMenuSplit(List<String> order) {
        exceptInvalidDivideSize(order);

    }

    private void exceptInvalidDivideSize(List<String> order) {
        if (order.size() != MENU_DIVIDE_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALIDATE_ORDER);
        }
    }

    private void validateOrderSplit(List<String> orders) {
        exceptOrderSplit(orders);
    }

    private void exceptOrderSplit(List<String> orders) {
        if (isSplitEmpty(orders)) {
            throw new IllegalArgumentException(ErrorMessage.INVALIDATE_ORDER);
        }
    }


    private boolean isSplitEmpty(List<String> orders) {
        return orders.stream().anyMatch(String::isBlank);
    }

}
