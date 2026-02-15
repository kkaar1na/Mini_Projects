package org.example.TODOLIST.model;

import java.time.LocalDate;

public class Task {
    private String description;
    private TaskStatus status;
    private LocalDate startTime;
    private LocalDate deadline;

    public Task(String description, TaskStatus status, LocalDate startTime, LocalDate deadline) {
        this.description = description;
        this.status = status;
        this.startTime = startTime;
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void changeStatus(TaskStatus newStatus) {
        this.status = newStatus;
    }
}

