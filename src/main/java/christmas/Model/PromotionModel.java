package christmas.Model;

import christmas.Constant.ErrorMessage;
import christmas.Domain.Date;
import christmas.Domain.OrderList;
import java.util.Arrays;
import java.util.List;

public class PromotionModel {
    private final String DIVIDE_POINT = ",";
    private Date date;
    private OrderList orderList;


    public void initDate(int input) {
        date = new Date(input);
    }

    public void initOrder(String input) {
        List<String> orders = Arrays.asList(input.split(","));
        validateOrders(orders);
    }

    private void validateOrders(List<String> orders) {
        exceptSplit(orders);
    }

    private void exceptSplit(List<String> orders) {
        if (isSplitEmpty(orders)) {
            throw new IllegalArgumentException(ErrorMessage.INVALIDATE_ORDER);
        }
    }

    private boolean isSplitEmpty(List<String> orders) {
        return orders.stream().anyMatch(String::isBlank);
    }

}
