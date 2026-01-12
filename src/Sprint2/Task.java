package Sprint2;

import java.util.ArrayList;

public class Task {
    private ArrayList<String> tasks;

    public Task() {
        tasks = new ArrayList<>();
    }

    public void addTask(String description) {
        this.tasks.add(description);
    }

    public ArrayList<String> getTasks() {
        return this.tasks;
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int size() {
        return this.tasks.size();
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Task #" + (i + 1) + " " + tasks.get(i));
        }
    }

    public void updateTask(int index, String updatedDescription) {
        tasks.set(index - 1, updatedDescription);
    }

    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    public String searchForTask(String description) {
        return tasks.stream()
                .filter(t -> t.equalsIgnoreCase(description)) // decided to use exact matching instead of contains for simplicity
                .findFirst()
                .orElse(null);
    }

}
