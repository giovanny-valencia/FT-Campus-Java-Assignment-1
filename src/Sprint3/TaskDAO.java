package Sprint3;

import java.util.ArrayList;

public class TaskDAO {
    private final ArrayList<Task> tasks;

    public TaskDAO(){
        tasks = new ArrayList<>();
    }

    public void addTask(String title, String description, String assignee){
        Task task = new Task(title, description, assignee);
        tasks.add(task);
    }

    // full task or just description?
    public void updateTask(int taskId, String title, String description, String assignee){
        for (Task task : tasks){
            if (task.getTaskId() == taskId){
                if (!title.isEmpty()){
                    task.setTaskTitle(title);
                }

                if (!description.isEmpty()){
                    task.setDescription(description);
                }

                if (!assignee.isEmpty()){
                    task.setAssignedTo(assignee);
                }
                return;
            }
        }
        System.out.println("TaskId " + taskId + " not found.");
    }

    public void deleteTask(int taskId){
        boolean wasDeleted = tasks.removeIf(task -> task.getTaskId() == taskId);

        if (!wasDeleted){
            System.out.println("TaskId " + taskId + " not found.");
        }
    }

    public void searchForTask(String description){
        if (description.isEmpty()){
            System.out.println("Description was empty..");
        }

        Task foundTask = tasks.stream()
                .filter(task -> task.getDescription().equalsIgnoreCase(description))
                .findFirst()
                .orElse(null);

        if (foundTask == null){
            System.out.println("Task with description: " + description + " was not found");
        } else {
            System.out.println("Found: " + foundTask);
        }
    }

    public boolean isEmpty(){
        return tasks.isEmpty();
    }

    public void printTasks(){
        int i = 1;
        for (Task task : tasks){
            System.out.println("Task#" + (i++) + " | " + task.toString());
        }
    }
}
