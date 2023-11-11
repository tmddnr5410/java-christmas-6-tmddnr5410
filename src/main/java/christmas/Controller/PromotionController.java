package christmas.Controller;

import christmas.Model.PromotionModel;
import christmas.View.InputView;
import christmas.View.OutputView;

public class PromotionController {
    private PromotionModel promotionModel = new PromotionModel();

    public void startReserve() {
        boolean errorChecker = true;

        while (errorChecker) {
            try {
                saveDate();
                errorChecker = false;
            } catch (IllegalArgumentException error) {
                OutputView.printError(error);
            }
        }

    }

    private void saveDate() {

        int input = InputView.readDate();
        promotionModel.initDate(input);

    }


}
