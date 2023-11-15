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
                .hasMessage(ErrorMessage.INVALIDATE_DATE);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 15, 16, 29, 30})
    public void 날짜가_주말인지_확인(Integer date) {
        Date testDate = new Date(date);
        Assertions.assertThat(testDate.isWEEKEND()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 11, 19, 20, 28})
    public void 날짜가_평일인지_확인(Integer date) {
        Date testDate = new Date(date);
        Assertions.assertThat(testDate.isWEEKEND()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 17, 25, 31})
    public void 날짜가_별인지_확인(Integer date) {
        Date testDate = new Date(date);
        Assertions.assertThat(testDate.isStar()).isTrue();
    }
}
