package org.projects.PaySystem.model;

public class PayPalPayment extends PaymentMethod {
    private String email;

    public PayPalPayment(String email, String owner) {
        super("PayPal", owner);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    protected boolean validate() {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    @Override
    public boolean processPayment(double amount) {
        if (!validate()) {
            System.out.println("ошибка: неверный формат email");
            return false;
        }
        if (amount <= 0) {
            System.out.println("ошибка: сумма должна быть положительной");
            return false;
        }
        System.out.println("оплата через PayPal на сумму " + amount + " выполнена");
        return true;
    }
}