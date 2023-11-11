package christmas.View;

import camp.nextstep.edu.missionutils.Console;
import christmas.Constant.ErrorMessage;

public class InputView {
    private static final String INPUT_MESSAGE_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_MESSAGE_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public static int readDate() {
        System.out.println(INPUT_MESSAGE_DATE);
        String input = Console.readLine();

        validateDate(input);

        return Integer.parseInt(input);
    }

    public static String readMenu() {
        System.out.println(INPUT_MESSAGE_MENU);
        String input = Console.readLine();

        validateMenu(input);

        return input;
    }

    private static void validateDate(String input) {
        exceptInputNone(input);
        exceptInteger(input);
    }

    private static void validateMenu(String input) {
        exceptInputNone(input);
    }

    private static void exceptInputNone(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NO_INPUT);
        }
    }

    private static void exceptInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_INT);
        }
    }

}
