package christmas.Domain;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Event {
    private static final Integer CHRISTMAS_EVENT_START_DAY = 1;
    private static final Integer CHRISTMAS_EVENT_END_DAY = 25;
    private static final Integer CHRISTMAS_DISCOUNT_DEFAULT = 1000;
    private static final Integer CHRISTMAS_DISCOUNT_BONUS = 100;

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
        if (CHRISTMAS_EVENT_START_DAY < date.getDate() && date.getDate() < CHRISTMAS_EVENT_END_DAY) {
            benefitDetail.put(EventType.CHRISTMAS_D_DAY, christmasDDayBonus(date));
        }
    }

    private Integer christmasDDayBonus(Date date) {
        return CHRISTMAS_DISCOUNT_DEFAULT + CHRISTMAS_DISCOUNT_BONUS * (date.getDate() - 1);
    }


}