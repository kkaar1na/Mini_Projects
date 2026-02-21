package org.projects.CRM_system.model;

public interface Notifiable {
    void sendMessage(String message);
    void addObserver(Notifiable observer);
    void removeObserver(Notifiable observer);
    void notifyObservers(String message);
}