package org.projects.CRM_system.model;

public class Employee {
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
}