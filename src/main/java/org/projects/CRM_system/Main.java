package org.projects.CRM_system;

import org.projects.CRM_system.controller.CRMController;

public class Main {
    public static void main(String[] args) {
        CRMController crm = new CRMController();
        crm.start();
    }
}