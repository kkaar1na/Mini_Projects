package org.projects.CRM_system.model;

import java.time.LocalDate;

public class Deal {
    private Client client;
    private Employee employee;
    private long sum;
    private DealStatus status;

    public Deal(Client client, Employee employee, long sum, LocalDate startDate, LocalDate endDate, DealStatus status) {
        this.client = client;
        this.employee = employee;
        this.sum = sum;
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public long getSum() {
        return sum;
    }

    public DealStatus getStatus() {
        return status;
    }

    public void setStatus(DealStatus status) {
        this.status = status;
    }
}