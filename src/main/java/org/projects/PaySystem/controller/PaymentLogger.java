package org.projects.PaySystem.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentLogger implements PaymentObserver {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void update(String message, double amount, String owner, String paymentType, boolean success) {
        String timestamp = LocalDateTime.now().format(formatter);
        String status = success ? "успешно" : "ошибка";
        System.out.println("[" + timestamp + "] " + status + ": " + owner + " - " +
                paymentType + " - " + amount + " - " + message);
    }
}