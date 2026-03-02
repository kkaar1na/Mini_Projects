package org.projects.PaySystem.view;

import java.util.Scanner;

public class PaymentConsoleView {
    private Scanner input = new Scanner(System.in);

    public String askType() {
        System.out.println("\nсистема оплаты");
        System.out.println("выберите тип платежа: ");
        System.out.println("1. оплата кредитной картой");
        System.out.println("2. система PayPal");
        System.out.println("3. оплата криптовалютой");
        System.out.println("или напишите 'exit' для выхода");
        return input.nextLine().trim();
    }

    public String askOwner() {
        System.out.println("напишите ваше имя: ");
        return input.nextLine().trim();
    }

    public double askAmount() {
        System.out.println("введите сумму платежа: ");
        while (!input.hasNextDouble()) {
            System.out.println("пожалуйста, введите число");
            input.next();
        }
        double amount = input.nextDouble();
        input.nextLine();
        return amount;
    }

    public String askPaymentType(String type) {
        System.out.println("введите данные для оплаты:");
        switch (type) {
            case "1":
                System.out.println("номер карты (16 цифр): ");
                break;
            case "2":
                System.out.println("email: ");
                break;
            case "3":
                System.out.println("адрес кошелька: ");
                break;
            default:
                System.out.println("дополнительная информация: ");
        }
        return input.nextLine().trim();
    }

    public boolean askContinue() {
        System.out.println("хотите выполнить еще один платеж? (да/нет): ");
        String answer = input.nextLine().trim().toLowerCase();
        return answer.equals("да") || answer.equals("yes") || answer.equals("y");
    }

    public void clearBuffer() {
        if (input.hasNextLine()) {
            input.nextLine();
        }
    }
}