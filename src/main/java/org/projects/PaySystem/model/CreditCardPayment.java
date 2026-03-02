package org.projects.PaySystem.model;

public class CreditCardPayment extends PaymentMethod {
    private String cardNumber;

    public CreditCardPayment(String cardNumber, String owner) {
        super("CreditCard", owner);
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    protected boolean validate() {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }

    @Override
    public boolean processPayment(double amount) {
        if (!validate()) {
            System.out.println("ошибка: неверный номер карты");
            return false;
        }
        if (amount <= 0) {
            System.out.println("ошибка: сумма должна быть положительной");
            return false;
        }
        System.out.println("оплата кредитной картой на сумму " + amount + " выполнена");
        return true;
    }
}