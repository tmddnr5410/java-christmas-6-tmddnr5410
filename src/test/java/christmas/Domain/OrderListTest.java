package christmas.Domain;

import christmas.Constant.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderListTest {
    private static final Integer MIN_ORDER_QUANTITY = 1;
    private static final String DEFAULT_MENU_NAME = "해산물파스타";

    @ParameterizedTest
    @ValueSource(strings = {"크림파스타", "화이트와인", "딸기케이크"})
    public void 메뉴판에_없는_메뉴입력에_대한_테스트(String input) {
        OrderList testOrderList = new OrderList();

        Assertions.assertThatThrownBy(() -> testOrderList.order(input, MIN_ORDER_QUANTITY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALIDATE_ORDER);
    }


    @ParameterizedTest
    @ValueSource(strings = {" ", "   "})
    public void 메뉴입력이_없는_경우에_대한_테스트(String input) {
        OrderList testOrderList = new OrderList();

        Assertions.assertThatThrownBy(() -> testOrderList.order(input, MIN_ORDER_QUANTITY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALIDATE_ORDER);
    }

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타"})
    public void 중복된_메뉴가_입력되는_경우에_대한_테스트(String input) {
        OrderList testOrderList = new OrderList();
        testOrderList.order(input, MIN_ORDER_QUANTITY);
        Assertions.assertThatThrownBy(() -> testOrderList.order(input, MIN_ORDER_QUANTITY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALIDATE_ORDER);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -99})
    public void 주문수량이_최솟값보다_작은_경우에_대한_테스트(Integer input) {
        OrderList testOrderList = new OrderList();
        Assertions.assertThatThrownBy(() -> testOrderList.order(DEFAULT_MENU_NAME, input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALIDATE_ORDER);
    }

}
