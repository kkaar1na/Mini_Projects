package org.projects.PaySystem.controller;

public interface PaymentObserver {
    void update(String message, double amount, String owner, String paymentType, boolean success);
}