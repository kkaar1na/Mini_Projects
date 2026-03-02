package org.projects.PaySystem.model;

public class PaymentFactory {
    public PaymentMethod createPayment(String paymentType, String owner, String info) {
        switch (paymentType.toLowerCase()) {
            case "1":
            case "credit":
            case "creditcard":
                return new CreditCardPayment(info, owner);
            case "2":
            case "paypal":
                return new PayPalPayment(info, owner);
            case "3":
            case "crypto":
                return new CryptoPayment(info, owner);
            default:
                System.out.println("неизвестный тип платежа");
                return null;
        }
    }
}