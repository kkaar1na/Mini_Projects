package org.projects.CRM_system.controller;

import org.projects.CRM_system.model.*;
import org.projects.CRM_system.view.ConsoleView;

import java.util.ArrayList;
import java.util.List;

public class CRMController {
    private List<Deal> deals = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private ConsoleView view = new ConsoleView();

    public void start() {
        while (true) {
            view.showMainMenu();
            int choice = view.getIntInput();

            if (choice == 0) {
                System.out.println("выход");
                break;
            }

            switch (choice) {
                case 1:
                    manageDeals();
                    break;
                case 2:
                    manageEmployees();
                    break;
                case 3:
                    manageClients();
                    break;
                case 4:
                    manageReports();
                    break;
                default:
                    System.out.println("неверный выбор");
            }
        }
    }

    private void manageDeals() {
        while (true) {
            view.showDealMenu();
            int choice = view.getIntInput();

            if (choice == 0) {
                return;
            }

            switch (choice) {
                case 1:
                    view.displayAllDeals(deals);
                    break;
                case 2:
                    System.out.println("введите статус (NEW, IN_PROGRESS, COMPLETED):");
                    String statusInput = view.getStringInput().toUpperCase();
                    try {
                        DealStatus status = DealStatus.valueOf(statusInput);
                        List<Deal> filteredDeals = getDealsByStatus(status);
                        view.displayAllDeals(filteredDeals);
                    } catch (IllegalArgumentException e) {
                        System.out.println("неверный статус");
                    }
                    break;
                case 3:
                    List<Deal> sortedDeals = sortDealsByStatus();
                    view.displayAllDeals(sortedDeals);
                    break;
                case 4:
                    addDeal();
                    break;
                case 5:
                    updateDealStatus();
                    break;
                default:
                    System.out.println("неверный выбор");
            }
        }
    }

    private void addDeal() {
        if (clients.isEmpty()) {
            System.out.println("сначала добавьте клиентов");
            return;
        }
        if (employees.isEmpty()) {
            System.out.println("сначала добавьте сотрудников");
            return;
        }

        System.out.println("введите сумму сделки:");
        long sum = view.getLongInput();

        System.out.println("выберите клиента по индексу:");
        for (int i = 0; i < clients.size(); i++) {
            System.out.println(i + ": " + clients.get(i).getName());
        }
        int clientIndex = view.getIntInput();
        if (clientIndex < 0 || clientIndex >= clients.size()) {
            System.out.println("неверный индекс клиента");
            return;
        }
        Client client = clients.get(clientIndex);

        System.out.println("выберите сотрудника по индексу:");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(i + ": " + employees.get(i).getName());
        }
        int empIndex = view.getIntInput();
        if (empIndex < 0 || empIndex >= employees.size()) {
            System.out.println("неверный индекс сотрудника");
            return;
        }
        Employee employee = employees.get(empIndex);

        Deal deal = new Deal(client, employee, sum, java.time.LocalDate.now(),
                java.time.LocalDate.now().plusDays(7), DealStatus.NEW);

        deals.add(deal);
        System.out.println("сделка добавлена");
    }

    private void updateDealStatus() {
        if (deals.isEmpty()) {
            System.out.println("сделок нет");
            return;
        }

        System.out.println("выберите сделку по индексу:");
        for (int i = 0; i < deals.size(); i++) {
            System.out.println(i + ": клиент " + deals.get(i).getClient().getName() +
                    ", сотрудник " + deals.get(i).getEmployee().getName() +
                    ", статус " + deals.get(i).getStatus());
        }
        int dealIndex = view.getIntInput();
        if (dealIndex < 0 || dealIndex >= deals.size()) {
            System.out.println("неверный индекс сделки");
            return;
        }

        Deal deal = deals.get(dealIndex);

        System.out.println("введите новый статус (NEW, IN_PROGRESS, COMPLETED):");
        String newStatus = view.getStringInput().toUpperCase();

        try {
            DealStatus status = DealStatus.valueOf(newStatus);
            deal.setStatus(status);
            System.out.println("статус обновлен");
        } catch (IllegalArgumentException e) {
            System.out.println("неверный статус");
        }
    }

    private void manageEmployees() {
        System.out.println("1. добавить сотрудника");
        int choice = view.getIntInput();

        if (choice == 1) {
            System.out.println("введите имя:");
            String name = view.getStringInput();

            System.out.println("введите комиссию (например 0.05):");
            double rate = view.getDoubleInput();

            System.out.println("введите должность:");
            String job = view.getStringInput();

            employees.add(new Employee(name, rate, job));
            System.out.println("сотрудник добавлен");
        }
    }

    private void manageClients() {
        System.out.println("1. добавить клиента");
        int choice = view.getIntInput();

        if (choice == 1) {
            System.out.println("введите имя:");
            String name = view.getStringInput();

            System.out.println("введите email:");
            String email = view.getStringInput();

            System.out.println("введите телефон:");
            String phone = view.getStringInput();

            clients.add(new Client(name, email, phone));
            System.out.println("клиент добавлен");
        }
    }

    private void manageReports() {
        while (true) {
            view.showReportMenu();
            int choice = view.getIntInput();

            if (choice == 0) {
                return;
            }

            switch (choice) {
                case 1:
                    if (deals.isEmpty()) {
                        System.out.println("сделок нет");
                        break;
                    }
                    System.out.println("выберите сделку по индексу:");
                    for (int i = 0; i < deals.size(); i++) {
                        System.out.println(i + ": клиент " + deals.get(i).getClient().getName() +
                                ", сотрудник " + deals.get(i).getEmployee().getName() +
                                ", статус " + deals.get(i).getStatus());
                    }
                    int dealIndex = view.getIntInput();
                    if (dealIndex < 0 || dealIndex >= deals.size()) {
                        System.out.println("неверный индекс сделки");
                        break;
                    }
                    Deal deal = deals.get(dealIndex);
                    double commission = deal.getSum() * deal.getEmployee().getCommissionRate();
                    view.displayCommission(commission);
                    break;

                case 2:
                    double total = 0;
                    for (Deal d : deals) {
                        total += d.getSum() * d.getEmployee().getCommissionRate();
                    }
                    System.out.println("общая комиссия: " + total);
                    break;

                case 3:
                    if (employees.isEmpty()) {
                        System.out.println("сотрудников нет");
                        break;
                    }
                    System.out.println("выберите сотрудника по индексу:");
                    for (int i = 0; i < employees.size(); i++) {
                        System.out.println(i + ": " + employees.get(i).getName());
                    }
                    int empIndex = view.getIntInput();
                    if (empIndex < 0 || empIndex >= employees.size()) {
                        System.out.println("неверный индекс сотрудника");
                        break;
                    }
                    Employee emp = employees.get(empIndex);
                    double empTotal = 0;
                    for (Deal d : deals) {
                        if (d.getEmployee() == emp) {
                            empTotal += d.getSum() * emp.getCommissionRate();
                        }
                    }
                    System.out.println("комиссия сотрудника: " + empTotal);
                    break;

                default:
                    System.out.println("неверный выбор");
            }
        }
    }

    private List<Deal> getDealsByStatus(DealStatus status) {
        List<Deal> result = new ArrayList<>();
        for (Deal deal : deals) {
            if (deal.getStatus() == status) {
                result.add(deal);
            }
        }
        return result;
    }

    private List<Deal> sortDealsByStatus() {
        List<Deal> sorted = new ArrayList<>(deals);
        sorted.sort((d1, d2) -> d1.getStatus().compareTo(d2.getStatus()));
        return sorted;
    }
}