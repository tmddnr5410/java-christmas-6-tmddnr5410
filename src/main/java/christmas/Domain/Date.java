package christmas.Domain;

import christmas.Constant.ErrorMessage;
import java.util.Objects;

public class Date {
    private final Integer MaxDateRange = 31;
    private final Integer MinDateRange = 1;
    private final Integer A_WEEK = 7;
    private static final Date FRIDAY_PATTERN = new Date(1);
    private static final Date SATURDAY_PATTERN = new Date(2);

    private Integer date;

    public Date(int input) {
        validate(input);
        date = input;
    }

    public Integer getDate() {
        return date;
    }

    private void validate(int input) {
        exceptOutRange(input);
    }

    private void exceptOutRange(int input) {
        if (input < MinDateRange || MaxDateRange < input) {
            throw new IllegalArgumentException(ErrorMessage.OUT_DATE_RANGE);
        }
    }

    public boolean isWEEKEND() {
        return isFriday() || isSaturday();
    }

    private boolean isFriday() {
        return Objects.equals(date % A_WEEK, FRIDAY_PATTERN.getDate());
    }

    private boolean isSaturday() {
        return Objects.equals(date % A_WEEK, SATURDAY_PATTERN.getDate());
    }

}
