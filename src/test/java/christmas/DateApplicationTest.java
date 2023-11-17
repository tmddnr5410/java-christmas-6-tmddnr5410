package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();


    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"-5", "0", "32", "99"})
    void 날짜_범위_테스트(String date) {
        assertSimpleTest(() -> {
            runException(date);
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }


    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
