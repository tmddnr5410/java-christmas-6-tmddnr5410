package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

public class EventApplicationTest extends NsTest {
    @Test
    void 전체이벤트_미적용_테스트() {
        assertSimpleTest(() -> {
            runException("25", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>",
                    "없음");
        });
    }

    @Test
    void 샴페인_증정_테스트() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-3");
            assertThat(output()).contains("<증정 메뉴>",
                    "샴페인 1개");
        });
    }

    @Test
    void 샴페인_미증정_테스트() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-1");
            assertThat(output()).contains("<증정 메뉴>",
                    "없음");
        });
    }


    @Test
    void 크리스마스_디데이_테스트() {
        assertSimpleTest(() -> {
            runException("25", "티본스테이크-3,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>",
                    "크리스마스 디데이 할인: -3,400원");
        });
    }

    @Test
    void 평일_할인_테스트() {
        assertSimpleTest(() -> {
            runException("5", "초코케이크-2,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>",
                    "평일 할인: -4,046원");
        });
    }

    @Test
    void 주말_할인_테스트() {
        assertSimpleTest(() -> {
            runException("29", "티본스테이크-2,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>",
                    "주말 할인: -4,046원");
        });
    }

    @Test
    void 특별_할인_테스트() {
        assertSimpleTest(() -> {
            runException("25", "티본스테이크-2,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>",
                    "특별 할인: -1,000원");
        });
    }

    @Test
    void 증정_할인_테스트() {
        assertSimpleTest(() -> {
            runException("25", "티본스테이크-4,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>",
                    "증정 이벤트: -25,000원");
        });
    }


    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
