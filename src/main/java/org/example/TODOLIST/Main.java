package org.example.TODOLIST;

import org.example.TODOLIST.controller.ToDoController;

public class Main {
    public static void main(String[] args) {
        ToDoController toDoController = new ToDoController();
        toDoController.start();
    }
}