package christmas.Domain;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Event {
    private static final Integer CHRISTMAS_START_DAY = 1;
    private static final Integer CHRISTMAS_END_DAY = 25;
    private static final Integer CHRISTMAS_BENEFIT_DEFAULT = 1000;
    private static final Integer CHRISTMAS_BENEFIT = 100;
    private static final Integer SPECIAL_BENEFIT = 1000;
    private static final Integer WEEK_BENEFIT = 2023;
    private static final Integer GIFT_MIN_PRICE = 120000;
    private static final Integer GIFT_DEFAULT_AMOUNT = 1;
    private static final String WEEKEND_BENEFIT_MENU_TYPE = MenuType.MAIN;
    private static final String WEEKDAY_BENEFIT_MENU_TYPE = MenuType.DESERT;
    private static final Menu GIFT = Menu.CHAMPAGNE;

    private EnumMap<EventType, Integer> benefitDetail;
    private EnumMap<Menu, Integer> gifts;
    private Badge badge;

    public Event() {
        benefitDetail = new EnumMap<>(EventType.class);
        for (EventType eventType : EventType.values()) {
            benefitDetail.put(eventType, 0);
        }

        gifts = new EnumMap<>(Menu.class);
        gifts.put(GIFT, 0);

        badge = Badge.NONE;
    }

    public Map<String, Integer> getTotalBenefit() {
        Map<String, Integer> allBenefit = new LinkedHashMap<>();
        for (Map.Entry<EventType, Integer> entry : benefitDetail.entrySet()) {
            EventType eventType = entry.getKey();
            int amount = entry.getValue();

            allBenefit.put(eventType.getEventName(), amount);
        }
        return allBenefit;
    }

    public Integer getBenefitPrice() {
        Integer totalBenefit = 0;
        for (Integer benefit : benefitDetail.values()) {
            totalBenefit += benefit;
        }
        return totalBenefit;
    }

    public void calculateEventBadge() {
        badge = Badge.getBadgeByBenefit(Math.abs(getBenefitPrice()));
    }

    public Integer getTotalDiscount() {
        Integer totalDiscount = 0;
        for (Map.Entry<EventType, Integer> entry : benefitDetail.entrySet()) {
            EventType eventType = entry.getKey();
            int amount = entry.getValue();

            if (eventType != EventType.GIFT) {
                totalDiscount += amount;
            }
        }
        return totalDiscount;
    }

    public void calculateGift(Integer totalPrice) {
        if (totalPrice >= GIFT_MIN_PRICE) {
            gifts.put(GIFT, GIFT_DEFAULT_AMOUNT);
        }
    }

    public void calculateChristmasDDayBonus(Date date) {
        if (CHRISTMAS_START_DAY < date.getDate() && date.getDate() <= CHRISTMAS_END_DAY) {
            benefitDetail.put(EventType.CHRISTMAS_D_DAY, christmasDDayBonus(date) * -1);
        }
    }

    private Integer christmasDDayBonus(Date date) {
        return CHRISTMAS_BENEFIT_DEFAULT + CHRISTMAS_BENEFIT * (date.getDate() - 1);
    }

    public String getWeekEventMenuType(Date date) {
        if (date.isWEEKEND()) {
            return WEEKEND_BENEFIT_MENU_TYPE;
        }
        return WEEKDAY_BENEFIT_MENU_TYPE;
    }

    public Map<String, Integer> getGifts() {
        Map<String, Integer> allGifts = new HashMap<>();

        for (Map.Entry<Menu, Integer> entry : gifts.entrySet()) {
            Menu gift = entry.getKey();
            Integer quantity = entry.getValue();
            allGifts.put(gift.getName(), quantity);
        }

        return allGifts;
    }

    public String getBadgeName() {
        return badge.getName();
    }

    public void calculateWeekBonus(Date date, Integer amount) {
        if (date.isWEEKEND()) {
            benefitDetail.put(EventType.WEEKEND, amount * WEEK_BENEFIT * -1);
            return;
        }
        benefitDetail.put(EventType.WEEKDAY, amount * WEEK_BENEFIT * -1);
    }

    public void calculateSpecialBonus(Date date) {
        if (date.isStar()) {
            benefitDetail.put(EventType.SPECIAL, SPECIAL_BENEFIT * -1);
        }
    }

    public void calculateGiftBonus() {
        Integer totalPrice = 0;
        for (Map.Entry<Menu, Integer> entry : gifts.entrySet()) {
            Menu menu = entry.getKey();
            int amount = entry.getValue();

            totalPrice += menu.getPrice() * amount;
        }
        benefitDetail.put(EventType.GIFT, totalPrice * -1);
    }

}