package org.projects.CRM_system.model;

public class Client implements Notifiable {

    private String name;
    private String email;
    private String phone;

    public Client(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    @Override
    public void notify(String message) {
        System.out.println("уведомление клиенту " + name + ": " + message);
    }
}