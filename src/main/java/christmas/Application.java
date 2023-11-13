package christmas;

import christmas.Controller.PromotionController;

public class Application {
    public static void main(String[] args) {
        PromotionController promotionController = new PromotionController();
        promotionController.startReservation();
    }
}