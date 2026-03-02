package org.projects.PaySystem.model;

public abstract class PaymentMethod {
    protected String name;
    protected String owner;

    public PaymentMethod(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public abstract boolean processPayment(double amount);
    protected abstract boolean validate();
}