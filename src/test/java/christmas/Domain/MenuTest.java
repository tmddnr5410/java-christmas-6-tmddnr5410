package christmas.Domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MenuTest {

    @ParameterizedTest
    @ValueSource(strings = {"해산물파스타", "레드와인", "초코케이크"})
    public void 존재하는_메뉴인지_확인(String input) {
        Assertions.assertThat(Menu.isNotInMenu(input)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"크림파스타", "화이트와인", "딸기케이크"})
    public void 존재하지_않는_메뉴인지_확인(String input) {
        Assertions.assertThat(Menu.isNotInMenu(input)).isTrue();
    }

}
