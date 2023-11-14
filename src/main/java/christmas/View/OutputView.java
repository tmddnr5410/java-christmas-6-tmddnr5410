package christmas.View;

import christmas.Constant.OutputMessage;
import java.util.Map;

public class OutputView {
    public static void printOrderList(Map<String, Integer> orderList) {
        System.out.println(OutputMessage.ORDERLIST_TITLE);
        printMenuQuantityList(orderList);
        System.out.println();
    }

    public static void printGiftList(Map<String, Integer> giftList) {
        System.out.println(OutputMessage.GIFT_TITLE);
        printEventList(giftList);
        System.out.println();
    }

    public static void printTotalPrice(Integer price) {
        System.out.println(OutputMessage.TOTAL_PRICE_TITLE);
        System.out.println(formattedPrice(price));
        System.out.println();
    }

    public static void printTotalBenefit(Map<String, Integer> allBenefit) {
        System.out.println(OutputMessage.TOTAL_BENEFIT_TITLE);
        System.out.println(formattedAllBenefit(allBenefit));
        System.out.println();
    }


    public static void printTotalDiscount(Integer totalBenefit) {
        System.out.println(OutputMessage.TOTAL_BENEFIT_PRICE_TITLE);
        System.out.printf(OutputMessage.TOTAL_PRICE_FORM, totalBenefit);
        System.out.println();
    }

    public static void printWelcome() {
        System.out.println(OutputMessage.RESERVATION_START_TITLE);
    }

    public static void printError(IllegalArgumentException error) {
        System.out.println(error.getMessage());
    }


    public static void printResultTitle() {
        System.out.println(OutputMessage.RESERVATION_RESULT_TITLE);
        System.out.println();
    }

    private static void printEventList(Map<String, Integer> events) {
        for (Integer value : events.values()) {
            if (value == 0) {
                printNoGift();
                return;
            }
        }
        printMenuQuantityList(events);
    }

    private static String formattedAllBenefit(Map<String, Integer> allBenefit) {
        StringBuilder benefitList = new StringBuilder();

        for (Map.Entry<String, Integer> benefit : allBenefit.entrySet()) {
            benefitList.append(formattedBenefit(benefit.getKey(), benefit.getValue()));
        }

        if (benefitList.isEmpty()) {
            benefitList.append(OutputMessage.TOTAL_BENEFIT_NONE);
        }
        return benefitList.toString();
    }

    private static String formattedBenefit(String name, Integer amount) {
        if (amount < 0) {
            return String.format(OutputMessage.BENEFIT_FORM, name, formattedPrice(amount));
        }
        return OutputMessage.NO_BENEFIT_FORM;
    }

    private static void printMenuQuantityList(Map<String, Integer> menuList) {
        for (Map.Entry<String, Integer> entry : menuList.entrySet()) {
            String menuName = entry.getKey();
            Integer quantity = entry.getValue();
            printMenuQuantity(menuName, quantity);
        }
    }

    private static void printMenuQuantity(String menuName, Integer quantity) {
        System.out.printf(OutputMessage.ORDERLIST_FORM, menuName, quantity);
        System.out.println();
    }

    private static String formattedPrice(Integer price) {
        return String.format(OutputMessage.TOTAL_PRICE_FORM, price);
    }

    private static void printNoGift() {
        System.out.println(OutputMessage.TOTAL_BENEFIT_NONE);
    }
}
