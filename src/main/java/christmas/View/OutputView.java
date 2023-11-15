package christmas.View;

import christmas.Constant.OutputMessage;
import java.util.Map;

public class OutputView {

    public static void printMenuSheet(String Title, Map<String, Integer> MenuList) {
        System.out.println(Title);
        if (needPrint(MenuList)) {
            printMenuQuantityList(MenuList);
            return;
        }
        System.out.println(OutputMessage.TOTAL_NONE);
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
        System.out.printf(OutputMessage.ORDER_FORM, menuName, quantity);
        System.out.println();
    }


    public static void printBenefitSheet(Map<String, Integer> allBenefit) {
        System.out.println(OutputMessage.TOTAL_BENEFIT_TITLE);
        if (needPrint(allBenefit)) {
            printBenefits(allBenefit);
            return;
        }
        System.out.println(OutputMessage.TOTAL_NONE);
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


    public static void printPriceSheet(String title, Integer price) {
        System.out.println(title);
        System.out.println(formattedPrice(price));
        System.out.println();
    }

    private static String formattedPrice(Integer price) {
        return String.format(OutputMessage.TOTAL_PRICE_FORM, price);
    }


    public static void printBadgeNameSheet(String badgeName) {
        System.out.println(OutputMessage.BADGE_TITLE);
        System.out.println(badgeName);
        System.out.println();
    }


    public static void printError(IllegalArgumentException error) {
        System.out.println(error.getMessage());
    }

    public static void printWelcome() {
        System.out.println(OutputMessage.RESERVATION_START_TITLE);
    }

    public static void printResultTitle() {
        System.out.println(OutputMessage.RESERVATION_RESULT_TITLE);
        System.out.println();
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
