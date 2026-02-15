package org.example.TODOLIST.controller;

import org.example.TODOLIST.model.Task;
import org.example.TODOLIST.model.TaskStatus;
import org.example.TODOLIST.view.ToDoView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ToDoController {
    private List<Task> tasks;
    private ToDoView view;
    private boolean isRunning;

    public ToDoController() {
        tasks = new ArrayList<>();
        view = new ToDoView();
        isRunning = true;
    }

    public void start() {
        while (isRunning) {
            view.showMainMenu();
            String choice = view.getUserInput();
            handleUserChoice(choice);
        }
    }

    private void handleUserChoice(String choice) {
        switch (choice) {
            case "1":
                addNewTask();
                break;
            case "2":
                view.showTasks(tasks);
                view.pressAnyKey();
                break;
            case "3":
                changeTaskStatus();
                break;
            case "4":
                deleteTask();
                break;
            case "5":
                isRunning = false;
                view.showMassage("До свидания!!!");
                break;
            default:
                view.showError("Неверный выбор ответа :(((");
                view.pressAnyKey();
        }
    }

    public void addNewTask() {
        ToDoView.TaskInput input = view.readTaskDetails();

        if (input.getDescription().trim().isEmpty()) {
            view.showError("Описание не может быть пустым :(");
            view.pressAnyKey();
            return;
        }

        LocalDate deadline;
        try {
            String[] parts = input.getDeadline().split("\\.");
            if (parts.length == 3) {
                deadline = LocalDate.parse(parts[2] + "-" + parts[1] + "-" + parts[0]);
            } else {
                throw new DateTimeParseException("Неверный формат", input.getDeadline(), 0);
            }
        } catch (DateTimeParseException e) {
            view.showError("Неверный формат даты. Используйте дд.мм.гггг");
            view.pressAnyKey();
            return;
        }

        if (!isDeadlineValid(deadline)){
            view.showError("дедлайн не может быть таким");
            view.pressAnyKey();
            return;
        }

        Task task = new Task(
                input.getDescription(),
                TaskStatus.NEW,
                LocalDate.now(),
                deadline
        );

        tasks.add(task);
        view.showMassage("Задача добавлена :)");
        view.pressAnyKey();
    }

    private void changeTaskStatus() {
        if (tasks.isEmpty()) {
            view.showError("Нет задач для изменений");
            view.pressAnyKey();
            return;
        }
        int taskIndex = view.selectTask(tasks, "изменение статуса");

        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            view.showError("Неверный номер задачи");
            view.pressAnyKey();
            return;
        }

        TaskStatus newStatus = view.selectTaskStatus();
        if (newStatus == null) {
            view.showError("Неверный выбор статуса");
            view.pressAnyKey();
            return;
        }

        Task task = tasks.get(taskIndex);
        TaskStatus oldStatus = task.getStatus();
        task.changeStatus(newStatus);

        view.showMassage("Статус задачи изменен: " + oldStatus + " -> " + newStatus);

        view.pressAnyKey();
    }

    private boolean isDeadlineValid(LocalDate deadline) {
        return !deadline.isBefore(LocalDate.now());
    }

    private void deleteTask() {
        if (tasks.isEmpty()) {
            view.showError("Нет задач для удаления");
            view.pressAnyKey();
            return;
        }

        int taskIndex = view.selectTask(tasks, "удаления");
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            view.showError("Неверный номер задачи");
            view.pressAnyKey();
            return;
        }

        Task task = tasks.get(taskIndex);

        if (view.confirmDeleteTask(task.getDescription())) {
            tasks.remove(taskIndex);
            view.showMassage("Задача удалена");
        } else {
            view.showMassage("Удаление отменено");
        }
        view.pressAnyKey();
    }

    public void validateDate(LocalDate endDate, LocalDate startDate) {
        if  (endDate.isBefore(startDate)) {
            System.out.println("Невозможно добавить данный дедлайн");
        }
    }
}
