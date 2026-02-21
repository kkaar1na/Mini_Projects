package org.projects.CRM_system.view;

import org.projects.CRM_system.model.Deal;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private Scanner scanner = new Scanner(System.in);

    public void showMainMenu() {
        System.out.println("\ncrm система (❁´◡`❁):");
        System.out.println("1. управление сделками");
        System.out.println("2. управление сотрудниками");
        System.out.println("3. управление клиентами");
        System.out.println("4. отчеты");
        System.out.println("0. выход");
        System.out.print("выберите опцию: ");
    }

    public void showDealMenu() {
        System.out.println("\nуправление сделками (❁´◡`❁):");
        System.out.println("1. показать все сделки");
        System.out.println("2. показать сделки по статусу");
        System.out.println("3. сортировка по статусу");
        System.out.println("4. добавить сделку");
        System.out.println("5. изменить статус сделки");
        System.out.println("0. назад");
        System.out.print("выберите опцию: ");
    }

    public void showReportMenu() {
        System.out.println("\nотчеты (❁´◡`❁):");
        System.out.println("1. комиссия по сделке");
        System.out.println("2. общая комиссия");
        System.out.println("3. комиссия сотрудника");
        System.out.println("0. назад");
        System.out.print("выберите опцию: ");
    }

    public int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    public long getLongInput() {
        try {
            return Long.parseLong(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    public double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            return 0;
        }
    }

    public String getStringInput() {
        return scanner.nextLine();
    }

    public void displayAllDeals(List<Deal> deals) {
        if (deals == null || deals.isEmpty()) {
            System.out.println("сделок не найдено");
            return;
        }
        System.out.println("\nсписок всех сделок (❁´◡`❁):");
        for (int i = 0; i < deals.size(); i++) {
            Deal deal = deals.get(i);
            System.out.println(i + ": клиент " + deal.getClient().getName());
            System.out.println("   сотрудник " + deal.getEmployee().getName());
            System.out.println("   сумма: " + deal.getSum() + " руб.");
            System.out.println("   статус: " + deal.getStatus());
        }
    }

    public void displayCommission(double commission) {
        System.out.println("комиссия: " + commission);
    }
}