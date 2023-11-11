package christmas.View;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final String INPUT_MESSAGE_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    public int readDate() {
        System.out.println(INPUT_MESSAGE_DATE);
        String input = Console.readLine();

        return Integer.parseInt(input);
    }


}
