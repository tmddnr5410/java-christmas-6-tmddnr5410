package christmas.Controller;

import christmas.Model.PromotionModel;
import christmas.View.InputView;

public class PromotionController {
    private PromotionModel promotionModel = new PromotionModel();

    public void startReserve() {
        saveDate();
    }

    private void saveDate() {

        int input = InputView.readDate();
        promotionModel.initDate(input);

    }


}
