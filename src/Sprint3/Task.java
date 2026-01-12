package Sprint3;

public class Task {
    private static int nextId = 1; // track sequential, unique, next available id for the taskId;
    private int taskId;
    private String taskTitle;
    private String description;
    private String assignedTo;

    public Task(String taskTitle, String description, String assignedTo){
        this.setTaskId(nextId++);
        this.setTaskTitle(taskTitle);
        this.setDescription(description);
        this.setAssignedTo(assignedTo);
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

    @Override
    public String toString(){
        return "ID: " + getTaskId() +
                ", Title: " + getTaskTitle() +
                ", Description: " + getDescription() +
                ", Assigned to: " + getAssignedTo();
    }
}
