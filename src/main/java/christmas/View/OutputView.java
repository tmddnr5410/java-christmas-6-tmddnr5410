package christmas.View;

import christmas.Constant.OutputMessage;

public class OutputView {
    public static void printResultTitle() {
        System.out.println(OutputMessage.RESERVATION_RESULT_TITLE);
    }

    public static void printWelcome() {
        System.out.println(OutputMessage.RESERVATION_START_TITLE);
    }

    public static void printError(IllegalArgumentException error) {
        System.out.println(error.getMessage());
    }
}
