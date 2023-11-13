package christmas.Domain;

import christmas.Constant.ErrorMessage;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6000),
    TAPAS(MenuType.APPETIZER, "타파스", 5500),
    CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8000),
    T_BONE_STAKE(MenuType.MAIN, "티본스테이크", 55000),
    BARBECUE_RIB(MenuType.MAIN, "바비큐립", 6000),
    CAESAR_PASTA(MenuType.MAIN, "해산물파스타", 6000),
    CHOCO_CAKE(MenuType.DESERT, "초코케이크", 6000),
    ICE_CREAM(MenuType.DESERT, "아이스크림", 6000),
    ZERO_COKE(MenuType.DRINK, "제로콜라", 6000),
    RED_WINE(MenuType.DRINK, "레드와인", 6000),
    CHAMPAGNE(MenuType.DRINK, "샴페인", 6000);


    private final String type;
    private final String name;
    private final int price;

    Menu(String type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public static boolean isNotInMenu(String orderMenu) {
        return Arrays.stream(Menu.values())
                .noneMatch(menu -> menu.name.equalsIgnoreCase(orderMenu));
    }

    public static Menu findByMenuName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALIDATE_ORDER));
    }
}
