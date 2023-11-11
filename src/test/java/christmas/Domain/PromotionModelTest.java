package christmas.Domain;

import christmas.Constant.ErrorMessage;
import christmas.Model.PromotionModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PromotionModelTest {


    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1, ,", "티본스테이크-1,바비큐립-1,,제로콜라-1"})
    public void 메뉴가_입력되지_않는경우에_대한_예외처리(String input) {
        PromotionModel promotionModel = new PromotionModel();
        Assertions.assertThatThrownBy(() -> promotionModel.initOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALIDATE_ORDER);
    }
}
