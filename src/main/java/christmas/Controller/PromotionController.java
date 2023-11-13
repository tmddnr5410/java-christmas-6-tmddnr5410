package christmas.Controller;

import christmas.Model.PromotionModel;
import christmas.View.InputView;
import christmas.View.OutputView;

public class PromotionController {
    private PromotionModel promotionModel = new PromotionModel();

    public void startReserve() {
        OutputView.printWelcome();
        saveDate();
        saveOrder();
    }

    private void saveDate() {
        boolean errorChecker = true;

        while (errorChecker) {
            try {
                int input = InputView.readDate();
                promotionModel.initDate(input);
                errorChecker = false;
            } catch (IllegalArgumentException error) {
                OutputView.printError(error);
            }
        }
    }

    private void saveOrder() {
        boolean errorChecker = true;

        while (errorChecker) {
            try {
                String input = InputView.readMenu();
                promotionModel.processOrder(input);
                errorChecker = false;
            } catch (IllegalArgumentException error) {
                OutputView.printError(error);
            }
        }
    }

    private void calculateReservationResult() {
        OutputView.printResultTitle();
    }

}
