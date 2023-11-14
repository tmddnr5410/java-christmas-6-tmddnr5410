package christmas.Domain;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Event {
    private static final Integer CHRISTMAS_START_DAY = 1;
    private static final Integer CHRISTMAS_END_DAY = 25;
    private static final Integer CHRISTMAS_BENEFIT_DEFAULT = 1000;
    private static final Integer CHRISTMAS_BENEFIT = 100;
    private static final Integer SPECIAL_BENEFIT = 1000;
    private static final Integer WEEK_BENEFIT = 2023;
    private static final String WEEKEND_BENEFIT_MENU_TYPE = MenuType.MAIN;
    private static final String WEEKDAY_BENEFIT_MENU_TYPE = MenuType.DESERT;


    private EnumMap<EventType, Integer> benefitDetail;

    public Event() {
        benefitDetail = new EnumMap<>(EventType.class);
        for (EventType eventType : EventType.values()) {
            benefitDetail.put(eventType, 0);
        }
    }

    public Map<String, Integer> getAllBenefit() {
        Map<String, Integer> allBenefit = new HashMap<>();

        for (Map.Entry<EventType, Integer> entry : benefitDetail.entrySet()) {
            EventType eventType = entry.getKey();
            int amount = entry.getValue();

            allBenefit.put(eventType.getEventName(), amount);
        }
        return allBenefit;
    }

    public void calculateChristmasDDayBonus(Date date) {
        if (CHRISTMAS_START_DAY < date.getDate() && date.getDate() < CHRISTMAS_END_DAY) {
            benefitDetail.put(EventType.CHRISTMAS_D_DAY, christmasDDayBonus(date));
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

    public void calculateWeekBonus(Date date, Integer amount) {
        if (date.isWEEKEND()) {
            benefitDetail.put(EventType.WEEKEND, amount * WEEK_BENEFIT);
            return;
        }
        benefitDetail.put(EventType.WEEKDAY, amount * WEEK_BENEFIT);
    }

    public void calculateSpecialBonus(Date date) {
        if (date.isStar()) {
            benefitDetail.put(EventType.SPECIAL, SPECIAL_BENEFIT);
        }
    }


}