package org.projects.PaySystem;

import org.projects.PaySystem.controller.PaymentController;
import org.projects.PaySystem.controller.PaymentLogger;

public class Main {
    public static void main(String[] args) {
        PaymentController controller = new PaymentController();

        controller.addObserver(new PaymentLogger());

        System.out.println("запуск платежной системы...");
        controller.run();
    }
}