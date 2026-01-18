package Sprint5.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private static int nextId = 1; // track sequential, unique, next available id for the taskId;
    private int taskId;
    private String taskTitle;
    private String description;
    private String assignedTo;
    private LocalDate completionDate;
    private boolean completed;


    public Task(String taskTitle, String description, String assignedTo, LocalDate completionDate) {
        this.setTaskId(nextId++);
        this.setTaskTitle(taskTitle);
        this.setDescription(description);
        this.setAssignedTo(assignedTo);
        this.setCompletionDate(completionDate);
        this.setCompleted(false);
    }

    private void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public boolean getIsCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        String status = getIsCompleted() ? "completed" : "uncompleted";

        return "ID: " + getTaskId() +
                ", Title: " + getTaskTitle() +
                ", Description: " + getDescription() +
                ", Assigned to: " + getAssignedTo() +
                ", due on: " + getCompletionDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) +
                ", status: " + status;
    }
}
