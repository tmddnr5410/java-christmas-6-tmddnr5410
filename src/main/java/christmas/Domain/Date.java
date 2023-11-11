package christmas.Domain;

import christmas.Constant.ErrorMessage;

public class Date {
    private final Integer MaxDateRange = 31;
    private final Integer MinDateRange = 1;
    private Integer date;

    public Date(int input) {
        validate(input);
        date = input;
    }

    private void validate(int input) {
        exceptOutRange(input);
    }

    private void exceptOutRange(int input) {
        if (input < MinDateRange || MaxDateRange < input) {
            throw new IllegalArgumentException(ErrorMessage.OUT_DATE_RANGE);
        }
    }


}
