package christmas.Controller;

import christmas.Constant.OutputMessage;
import christmas.Model.PromotionModel;
import christmas.View.InputView;
import christmas.View.OutputView;
import java.util.Map;

public class PromotionController {
    private PromotionModel promotionModel = new PromotionModel();

    public void startPromotion() {
        startReservation();

        showReservationResult();

        showEventResult();

        showPaymentResult();
    }

    private void startReservation() {
        OutputView.printWelcome();
        saveDate();
        saveOrder();
    }

    private void saveDate() {
        try {
            int input = InputView.readDate();
            promotionModel.initDate(input);
        } catch (IllegalArgumentException error) {
            OutputView.printError(error);
            saveDate();
        }
    }

    private void saveOrder() {
        try {
            String input = InputView.readMenu();
            promotionModel.processOrder(input);
        } catch (IllegalArgumentException error) {
            OutputView.printError(error);
            saveOrder();
        }
    }

    private void showReservationResult() {
        OutputView.printResultTitle();

        Map<String, Integer> customerOrder = promotionModel.transferOrder();
        OutputView.printMenuSheet(OutputMessage.ORDER_TITLE, customerOrder);

        Integer totalPrice = promotionModel.transferTotalPrice();
        OutputView.printPriceSheet(OutputMessage.TOTAL_PRICE_TITLE, totalPrice);
    }

    private void showEventResult() {
        promotionModel.processEvent();

        Map<String, Integer> allGifts = promotionModel.transferTotalGifts();
        OutputView.printMenuSheet(OutputMessage.GIFT_TITLE, allGifts);

        Map<String, Integer> totalBenefit = promotionModel.transferTotalBenefit();
        OutputView.printBenefitSheet(totalBenefit);
    }

    private void showPaymentResult() {
        Integer benefitPrice = promotionModel.transferBenefitPrice();
        OutputView.printPriceSheet(OutputMessage.BENEFIT_PRICE_TITLE, benefitPrice);

        Integer finalPayment = promotionModel.getFinalPayment();
        OutputView.printPriceSheet(OutputMessage.FINAL_PAYMENT_TITLE, finalPayment);

        String badgeName = promotionModel.getEventBadgeName();
        OutputView.printBadgeNameSheet(badgeName);
    }

}
