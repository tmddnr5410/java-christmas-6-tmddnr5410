package christmas.Domain;

import christmas.Constant.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DateTest {


    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 32, 99})
    public void 입력날짜가_범위를_넘는_경우에_대한_예외처리(Integer date) {
        Assertions.assertThatThrownBy(() -> new Date(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OUT_DATE_RANGE);
    }
}
