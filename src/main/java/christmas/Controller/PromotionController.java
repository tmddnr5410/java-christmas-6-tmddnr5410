package christmas.Controller;

import christmas.View.InputView;

public class PromotionController {
    private final InputView inputView = new InputView();


    public void startReserve() {
        saveDate();
    }

    private void saveDate() {
        int date = inputView.readDate();
    }


}
