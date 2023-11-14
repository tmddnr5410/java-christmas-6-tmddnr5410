package christmas.Model;

import christmas.Constant.ErrorMessage;
import christmas.Domain.Date;
import christmas.Domain.Event;
import christmas.Domain.OrderList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PromotionModel {

    private final Integer ORDER_QUANTITY_INDEX = 1;
    private final Integer ORDER_MENU_INDEX = 0;
    private final Integer MENU_DIVIDE_SIZE = 2;
    private final String ORDER_DIVIDE_POINT = ",";
    private final String MENU_DIVIDE_POINT = "-";
    private Date date;
    private OrderList orderList;

    private Event event;


    public PromotionModel() {
        orderList = new OrderList();
        event = new Event();
    }

    public void initDate(int input) {
        date = new Date(input);
    }

    public void processOrder(String input) {
        orderList = new OrderList();
        event = new Event();

        List<String> orders = Arrays.asList(input.split(ORDER_DIVIDE_POINT));
        validateOrderSplit(orders);

        for (String order : orders) {
            addToOrderList(order);
        }

        orderList.validateSelf();
    }

    public void processEvent() {
        event.calculateChristmasDDayBonus(date);
        processWeekEvent();

    }

    public Map<String, Integer> transferOrderList() {
        return orderList.getOrderQuantity();
    }

    public Integer transferTotalPrice() {
        return orderList.getTotalPrice();
    }

    public Map<String, Integer> transferAllBenefit() {
        return event.getAllBenefit();
    }

    private void processWeekEvent() {
        String weekBonusMenuType = event.getWeekEventMenuType(date);
        Integer weekBonusQuantity = orderList.getQuantityOfMenuType(weekBonusMenuType);
        event.calculateWeekBonus(date, weekBonusQuantity);

    }

    private void addToOrderList(String order) {
        List<String> orderComponents = Arrays.asList(order.split(MENU_DIVIDE_POINT));

        validateorderComponents(orderComponents);

        String menu = orderComponents.get(ORDER_MENU_INDEX);
        String quantity = orderComponents.get(ORDER_QUANTITY_INDEX);

        orderList.order(menu, Integer.parseInt(quantity));
    }

    private void validateOrderSplit(List<String> orders) {
        exceptOrderSplit(orders);
    }

    private void validateorderComponents(List<String> order) {
        exceptInvalidDivideSize(order);
        exceptNotInteger(order.get(ORDER_QUANTITY_INDEX));
    }

    private void exceptNotInteger(String quantity) {
        try {
            Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.MENU_NOT_INT);
        }
    }

    private void exceptInvalidDivideSize(List<String> order) {
        if (order.size() != MENU_DIVIDE_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALIDATE_MENU_SPLIT);
        }
    }

    private void exceptOrderSplit(List<String> orders) {
        if (isSplitEmpty(orders)) {
            throw new IllegalArgumentException(ErrorMessage.INVALIDATE_ORDER_SPLIT);
        }
    }


    private boolean isSplitEmpty(List<String> orders) {
        return orders.stream().anyMatch(String::isBlank);
    }

}
