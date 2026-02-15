package org.example.TODOLIST.view;

import org.example.TODOLIST.model.Task;
import org.example.TODOLIST.model.TaskStatus;

import java.util.List;
import java.util.Scanner;

public class ToDoView {
    private Scanner in = new Scanner(System.in);

    public void showMainMenu(){
        System.out.println("Менеджер задач (✿◡‿◡)");
        System.out.println("\n1. Добавить задачу");
        System.out.println("2. Показать все задачи");
        System.out.println("3. Изменить статус задачи");
        System.out.println("4. Удалить задачу");
        System.out.println("5. Выйти");
        System.out.print("Выберите действие: ");
    }
    public String getUserInput() {
        return in.nextLine();
    }

    public TaskInput readTaskDetails(){
        System.out.println("НОВАЯ ЗАДАЧА (✿◡‿◡)");
        System.out.print("\nВведите описание задачи: ");
        String description = in.nextLine();
        System.out.println("Введите дату дедлайна в формате дд.мм.гггг");
        String deadline = in.nextLine();
        return new TaskInput(description, deadline);
    }

    public int selectTask(List<Task> tasks, String action){
        showTasks(tasks);
        if (tasks.isEmpty()){
            return -1;
        }
        System.out.println("\nВведите номер задачи для " + action + ": ");
        try {
            return Integer.parseInt(in.nextLine()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public TaskStatus selectTaskStatus() {
        System.out.println("Выберите статус задачи: ");
        System.out.println("1 - Новая");
        System.out.println("2 - В процессе выполнения");
        System.out.println("3 - Выполнено");
        System.out.print("Ваш выбор: ");

        String status = in.nextLine();
        switch (status) {
            case "1":
                return TaskStatus.NEW;
            case "2":
                return TaskStatus.IN_PROGRESS;
            case "3":
                return TaskStatus.COMPLETED;
            default:
                return null;
        }
    }

    public void showTasks(List<Task> taskList){
        System.out.println("\nСписок задач (✿◡‿◡)");
        if (taskList.isEmpty()){
            System.out.println("Задач пока нет");
            return;
        }

        for (int i = 0; i<taskList.size(); i++){
            Task task = taskList.get(i);
            System.out.printf("%d. [%s] %s (Дедлайн: %s, Создана: %s)%n",
                    i + 1,
                    getStatusEmoji(task.getStatus()),
                    task.getDescription(),
                    task.getDeadline(),
                    task.getStartTime());
        }
    }

    private String getStatusEmoji(TaskStatus status){
        switch (status){
            case NEW:
                return "Новая";
            case IN_PROGRESS:
                return "В процессе";
            case COMPLETED:
                return "Завершена";
            default:
                return status.toString();
        }
    }

    public boolean confirmDeleteTask(String taskDescription){
        System.out.print("Удалить задачу '" + taskDescription + "'? (д/н): ");
        String answer = in.nextLine().toLowerCase();
        return answer.equals("д") || answer.equals("да");
    }

    public void showMassage(String message){
        System.out.println(message);
    }

    public void showError(String message){
        System.out.println("Ошибка: " + message);
    }

    public void pressAnyKey(){
        System.out.println("\nНажмите Enter для продолжения...");
        in.nextLine();
    }

    public static class TaskInput{
        private String description;
        private String deadline;

        public  TaskInput(String description, String deadline){
            this.description = description;
            this.deadline = deadline;
        }

        public String getDescription(){
            return description;
        }

        public String getDeadline(){
            return deadline;
        }
    }
}
