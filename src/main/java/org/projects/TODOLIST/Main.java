package org.projects.TODOLIST;

import org.projects.TODOLIST.controller.ToDoController;

public class Main {
    public static void main(String[] args) {
        ToDoController toDoController = new ToDoController();
        toDoController.start();
    }
}