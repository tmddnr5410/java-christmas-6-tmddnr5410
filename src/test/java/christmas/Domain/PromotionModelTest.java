package christmas.Domain;

import christmas.Constant.ErrorMessage;
import christmas.Model.PromotionModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PromotionModelTest {


    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1, ,", "티본스테이크-1,바비큐립-1,,제로콜라-1"})
    public void 딜리미터사이에_주문이_입력되지_않는경우에_대한_예외처리(String input) {
        PromotionModel promotionModel = new PromotionModel();
        Assertions.assertThatThrownBy(() -> promotionModel.initOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALIDATE_ORDER_SPLIT);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-2,레드와인1,초코케이크-1", "해산물파스타-해-2,레드와인-1,초코케이크-1", "해산물파스타-2,레드와인-1,초코케이크--"})
    public void 메뉴와_수량의_딜리미터가_유효하지_않은경우에_대한_예외처리(String input) {
        PromotionModel promotionModel = new PromotionModel();
        Assertions.assertThatThrownBy(() -> promotionModel.initOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALIDATE_MENU_SPLIT);
    }


    @ParameterizedTest
    @ValueSource(strings = {"크림파스타-2,레드와인-1,초코케이크-1", "해산물파스타-2,화이트와인-1,초코케이크-1"})
    public void 없는메뉴가_주문되는_경우에_대한_예외처리(String input) {
        PromotionModel promotionModel = new PromotionModel();
        Assertions.assertThatThrownBy(() -> promotionModel.initOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALIDATE_ORDER);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타-x,레드와인-1,초코케이크-1", "해산물파스타-2,레드와인-tt,초코케이크-1"})
    public void 주문수량이_문자인_경우에_대한_예외처리(String input) {
        PromotionModel promotionModel = new PromotionModel();
        Assertions.assertThatThrownBy(() -> promotionModel.initOrder(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.MENU_NOT_INT);
    }

}
