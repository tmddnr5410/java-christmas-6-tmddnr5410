package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderApplicationTest extends NsTest {
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-3,,,제로콜라-1", "티본스테이크-3,,제로콜라1"})
    void 주문_분리_테스트(String orders) {
        assertSimpleTest(() -> {
            runException("3", orders);
            assertThat(output()).contains("[ERROR] ',' 사이에 입력이 없습니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크3,제로콜라-1", "티본스테이크-3,제로콜라1", "티본스테이크-3,제로콜라--1"})
    void 메뉴_수량_분리_테스트(String orders) {
        assertSimpleTest(() -> {
            runException("3", orders);
            assertThat(output()).contains("[ERROR] '-' 사이에 입력이 없습니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴_존재_테스트() {
        assertSimpleTest(() -> {
            runException("3", "등심스테이크-3,제로콜라-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문이_최대갯수_테스트() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-30,제로콜라-15");
            assertThat(output()).contains("[ERROR] 총 주문수량은 20을 넘길수 없습니다.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 한개_메뉴_테스트() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-3");
            assertThat(output()).contains("<주문 메뉴>",
                    "티본스테이크 3개");
        });
    }

    @Test
    void 여러개_메뉴_테스트() {
        assertSimpleTest(() -> {
            runException("3", "해산물파스타-2,레드와인-1,초코케이크-1");
            assertThat(output()).contains("<주문 메뉴>",
                    "해산물파스타 2개",
                    "레드와인 1개",
                    "초코케이크 1개");
        });
    }

    @Test
    void 총_주문_금액_테스트() {
        assertSimpleTest(() -> {
            runException("3", "해산물파스타-3,티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1,타파스-4");
            assertThat(output()).contains("<할인 전 총주문 금액>",
                    "269,000원");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
