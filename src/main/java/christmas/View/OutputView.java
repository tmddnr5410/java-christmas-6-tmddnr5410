package christmas.View;

import christmas.Constant.OutputMessage;
import java.util.Map;

public class OutputView {
    public static void printOrderList(Map<String, Integer> orderList) {
        System.out.println(OutputMessage.ORDERLIST_TITLE);

        for (Map.Entry<String, Integer> entry : orderList.entrySet()) {
            String menuName = entry.getKey();
            Integer quantity = entry.getValue();
            printOrder(menuName, quantity);
        }
        
        System.out.println();
    }

    public static void printResultTitle() {
        System.out.println(OutputMessage.RESERVATION_RESULT_TITLE);
    }

    public static void printWelcome() {
        System.out.println(OutputMessage.RESERVATION_START_TITLE);
    }

    public static void printError(IllegalArgumentException error) {
        System.out.println(error.getMessage());
    }

    private static void printOrder(String menuName, Integer quantity) {
        System.out.printf(OutputMessage.ORDERLIST_FORM, menuName, quantity);
    }
}
