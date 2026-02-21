package org.projects.CRM_system.model;

public class Employee implements Notifiable {

    private String name;
    private double commissionRate;
    private String job;

    public Employee(String name, double commissionRate, String job) {
        this.name = name;
        this.commissionRate = commissionRate;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public String getJob() {
        return job;
    }

    @Override
    public void notify(String message) {
        System.out.println("уведомление сотруднику " + name + ": " + message);
    }
}