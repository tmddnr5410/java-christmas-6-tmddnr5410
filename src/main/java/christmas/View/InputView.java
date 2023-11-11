package christmas.View;

import camp.nextstep.edu.missionutils.Console;
import christmas.Constant.ErrorMessage;

public class InputView {
    private static final String INPUT_MESSAGE_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public static int readDate() {
        System.out.println(INPUT_MESSAGE_DATE);
        String input = Console.readLine();

        validate(input);

        return Integer.parseInt(input);
    }

    private static void validate(String input) {
        exceptInputNone(input);
        exceptInteger(input);
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
