package Sprint4.task;

import Sprint3.Task;
import Sprint4.auth.Client;
import Sprint4.auth.User;

import java.util.ArrayList;

public class TaskDAOImpl implements TaskDAO{
    private final ArrayList<Task> tasks;

    public TaskDAOImpl(){
        tasks = new ArrayList<>();
    }

    @Override
    public void addTask(String title, String description, String assignee) {
        if (!isDataValid(title, description, assignee)){
            System.out.println("Invalid Entry");
            return;
        }
        Task task = new Task(title, description, assignee);
        tasks.add(task);
    }

    @Override
    public void updateTask(int taskId, String title, String description, String assignee) {
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

    @Override
    public void deleteTask(int taskId, User currentUser) {
        // probably not needed as Delete caller checks the role already
        if (!currentUser.hasClientRole(currentUser)){
            System.out.println("Visitors cannot delete tasks");
            return;
        }

        boolean wasDeleted = tasks.removeIf(task -> task.getTaskId() == taskId);

        if (!wasDeleted){
            System.out.println("TaskId " + taskId + " not found.");
        }
    }

    @Override
    public void searchForTask(String description) {
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

    @Override
    public void printTasks(User user) {
        int i = 1;
        for (Task task : tasks){
            /** (assumption)
             * Client should be able to see ALL the tasks
             * Visitor can only see tasks where their username matches tasks assignedTo
             */
            if (user instanceof Client || task.getAssignedTo().equalsIgnoreCase(user.getUsername())){
                System.out.println("Task#" + (i++) + " | " + task.toString());
            }
        }
    }

    @Override
    public boolean isEmpty(){
        return tasks.isEmpty();
    }

    private boolean isDataValid(String title, String description, String assignee){
        return  !title.isEmpty()
                && !description.isEmpty()
                && !assignee.isEmpty();
    }
}
