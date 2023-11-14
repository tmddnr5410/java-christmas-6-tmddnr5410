package christmas.Controller;

import christmas.Model.PromotionModel;
import christmas.View.InputView;
import christmas.View.OutputView;
import java.util.Map;

public class PromotionController {
    private PromotionModel promotionModel = new PromotionModel();

    public void startReservation() {
        makeReservation();
        printReservationResult();
        showEventResult();

    }

    private void makeReservation() {
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

    private void printReservationResult() {
        OutputView.printResultTitle();
        Map<String, Integer> customerOrderList = promotionModel.transferOrderList();
        OutputView.printOrderList(customerOrderList);
    }

    private void showEventResult() {
        Integer totalPrice = promotionModel.transferTotalPrice();
        OutputView.printTotalPrice(totalPrice);

        promotionModel.processEvent();

        Map<String, Integer> allGifts = promotionModel.transferTotalGifts();
        OutputView.printGiftList(allGifts);

        Map<String, Integer> allBenefit = promotionModel.transferAllBenefit();
        OutputView.printTotalBenefit(allBenefit);

        Integer totalBenefitPrice = promotionModel.transferTotalBenefitPrice();
    }

}
