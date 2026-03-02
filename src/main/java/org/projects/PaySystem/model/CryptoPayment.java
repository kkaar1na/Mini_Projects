package org.projects.PaySystem.model;

public class CryptoPayment extends PaymentMethod {
    private String walletAddress;

    public CryptoPayment(String walletAddress, String owner) {
        super("Crypto", owner);
        this.walletAddress = walletAddress;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    @Override
    protected boolean validate() {
        return walletAddress != null && walletAddress.length() >= 26 && walletAddress.length() <= 35;
    }

    @Override
    public boolean processPayment(double amount) {
        if (!validate()) {
            System.out.println("ошибка: неверный адрес кошелька");
            return false;
        }
        if (amount <= 0) {
            System.out.println("ошибка: сумма должна быть положительной");
            return false;
        }
        System.out.println("оплата криптовалютой на сумму " + amount + " выполнена");
        return true;
    }
}