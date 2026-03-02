package org.projects.PaySystem.controller;

import org.projects.PaySystem.model.PaymentFactory;
import org.projects.PaySystem.model.PaymentMethod;
import org.projects.PaySystem.view.PaymentConsoleView;
import java.util.ArrayList;
import java.util.List;

public class PaymentController {
    private PaymentFactory factory;
    private PaymentConsoleView view;
    private List<PaymentObserver> observers;

    public PaymentController() {
        this.factory = new PaymentFactory();
        this.view = new PaymentConsoleView();
        this.observers = new ArrayList<>();
    }

    public void addObserver(PaymentObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message, double amount, String owner,
                                 String paymentType, boolean success) {
        for (PaymentObserver observer : observers) {
            observer.update(message, amount, owner, paymentType, success);
        }
    }

    public void run() {
        boolean running = true;

        while (running) {
            try {
                String type = view.askType();

                if (type.equalsIgnoreCase("exit") || type.equalsIgnoreCase("выход")) {
                    running = false;
                    System.out.println("программа завершена");
                    break;
                }

                String owner = view.askOwner();
                String info = view.askPaymentType(type);
                double amount = view.askAmount();

                PaymentMethod payment = factory.createPayment(type, owner, info);

                if (payment != null) {
                    boolean success = payment.processPayment(amount);
                    String message = success ? "платеж обработан" : "ошибка обработки платежа";
                    notifyObservers(message, amount, owner, payment.getName(), success);
                } else {
                    notifyObservers("не удалось создать платеж", amount, owner, "unknown", false);
                }

                if (!view.askContinue()) {
                    running = false;
                    System.out.println("программа завершена");
                }

            } catch (Exception e) {
                System.out.println("ошибка: " + e.getMessage());
                view.clearBuffer();
            }
        }
    }
}