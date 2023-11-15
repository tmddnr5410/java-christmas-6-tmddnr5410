package christmas.View;

import christmas.Constant.OutputMessage;
import java.util.Map;

public class OutputView {
    public static void printOrderList(Map<String, Integer> orderList) {
        System.out.println(OutputMessage.ORDERLIST_TITLE);
        printMenuQuantityList(orderList);
    }

    public static void printGiftList(Map<String, Integer> giftList) {
        System.out.println(OutputMessage.GIFT_TITLE);
        if (needPrint(giftList)) {
            printMenuQuantityList(giftList);
            return;
        }
        System.out.println(OutputMessage.TOTAL_BENEFIT_NONE);
    }

    public static void printTotalBenefit(Map<String, Integer> allBenefit) {
        System.out.println(OutputMessage.TOTAL_BENEFIT_TITLE);
        if (needPrint(allBenefit)) {
            printBenefits(allBenefit);
            return;
        }
        System.out.println(OutputMessage.TOTAL_BENEFIT_NONE);
    }

    public static void printTotalPrice(Integer price) {
        System.out.println(OutputMessage.TOTAL_PRICE_TITLE);
        System.out.println(formattedPrice(price));
        System.out.println();
    }


    public static void printTotalDiscount(Integer totalBenefit) {
        System.out.println(OutputMessage.TOTAL_BENEFIT_PRICE_TITLE);
        System.out.println(formattedPrice(totalBenefit));
        System.out.println();
    }


    public static void printFinalPayment(Integer finalPayment) {
        System.out.println(OutputMessage.FINAL_PAYMENT_TITLE);
        System.out.println(formattedPrice(finalPayment));
        System.out.println();
    }

    public static void printBadgeName(String badgeName) {
        System.out.println(OutputMessage.BADGE_TITLE);
        System.out.println(badgeName);
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

    private static void printBenefits(Map<String, Integer> allBenefit) {
        for (Map.Entry<String, Integer> benefit : allBenefit.entrySet()) {
            if (benefit.getValue() < 0) {
                printBenefit(benefit.getKey(), benefit.getValue());
            }
        }
        System.out.println();
    }

    private static void printBenefit(String name, Integer amount) {
        if (amount < 0) {
            System.out.printf(OutputMessage.BENEFIT_FORM, name, formattedPrice(amount));
        }
    }

    private static void printMenuQuantityList(Map<String, Integer> menuList) {
        for (Map.Entry<String, Integer> entry : menuList.entrySet()) {
            String menuName = entry.getKey();
            Integer quantity = entry.getValue();
            printMenuQuantity(menuName, quantity);
        }
        System.out.println();
    }

    private static void printMenuQuantity(String menuName, Integer quantity) {
        System.out.printf(OutputMessage.ORDERLIST_FORM, menuName, quantity);
        System.out.println();
    }

    private static String formattedPrice(Integer price) {
        return String.format(OutputMessage.TOTAL_PRICE_FORM, price);
    }

    private static boolean needPrint(Map<String, Integer> allBenefit) {
        for (Integer value : allBenefit.values()) {
            if (value != 0) {
                return true;
            }
        }
        return false;
    }

}
