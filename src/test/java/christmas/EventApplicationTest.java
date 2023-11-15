package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

public class EventApplicationTest extends NsTest {
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

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
