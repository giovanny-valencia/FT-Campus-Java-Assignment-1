package Sprint5.task;

import Sprint5.auth.Client;
import Sprint5.auth.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Comparator;

public class TaskDAOImpl implements TaskDAO {
    private final ArrayList<Task> tasks;

    public TaskDAOImpl() {
        tasks = new ArrayList<>();
    }

    @Override
    public void addTask(String title, String description, String assignee, String completionDate) {
        if (!isDataValid(title, description, assignee, completionDate)) {
            System.out.println("Invalid Entry");
            return;
        }
        Task task = new Task(title, description, assignee, LocalDate.parse(completionDate, DateTimeFormatter.ofPattern("M/d/uuuu")));
        tasks.add(task);
    }

    @Override
    public void updateTask(int taskId, String title, String description, String assignee, String completionDate) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                if (!title.isEmpty()) {
                    task.setTaskTitle(title);
                }

                if (!description.isEmpty()) {
                    task.setDescription(description);
                }

                if (!assignee.isEmpty()) {
                    task.setAssignedTo(assignee);
                }

                if (!completionDate.isEmpty() && isDateValid(completionDate)) {
                    LocalDate updatedDate = LocalDate.parse(completionDate);
                    task.setCompletionDate(updatedDate);
                }

                return;
            }
        }
        System.out.println("TaskId " + taskId + " not found.");
    }

    @Override
    public void deleteTask(int taskId) {
        boolean wasDeleted = tasks.removeIf(task -> task.getTaskId() == taskId);

        if (!wasDeleted) {
            System.out.println("TaskId " + taskId + " not found.");
        }
    }

    @Override
    public void searchForTask(String description) {
        if (description.isEmpty()) {
            System.out.println("Description was empty..");
        }

        Task foundTask = tasks.stream().filter(task -> task.getDescription().equalsIgnoreCase(description)).findFirst().orElse(null);

        if (foundTask == null) {
            System.out.println("Task with description: " + description + " was not found");
        } else {
            System.out.println("Found: " + foundTask);
        }
    }

    @Override
    public void markTaskCompleted(int taskId, User user) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId && task.getAssignedTo().equals(user.getUsername())) {
                task.setCompleted(true);
                return;
            }
        }
    }

    @Override
    public void printTasks(User user) {
        int i = 1;
        for (Task task : tasks) {
            if (user instanceof Client || task.getAssignedTo().equalsIgnoreCase(user.getUsername())) {
                System.out.println("Task#" + (i++) + " | " + task.toString());
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void reSortTasks(boolean increasing) {
        if (increasing) {
            tasks.sort(Comparator.comparing(Task::getCompletionDate));
        } else {
            tasks.sort(Comparator.comparing(Task::getCompletionDate).reversed());
        }
    }

    private boolean isDataValid(String title, String description, String assignee, String completionDate) {
        return !title.isEmpty() && !description.isEmpty() && !assignee.isEmpty() && isDateValid(completionDate);
    }

    // MM/DD/YYYY format
    private boolean isDateValid(String dateInput) {
        String[] dateToken = dateInput.split("/");
        if (dateToken.length != 3) {
            System.out.println("Invalid date format. Expected: MM/DD/YYYY");
            return false;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/uuuu").withResolverStyle(ResolverStyle.STRICT);

            LocalDate providedDate = LocalDate.parse(dateInput, formatter); // checks for strict real existing dates

            LocalDate today = LocalDate.now();

            if (providedDate.isBefore(today)) {
                System.out.println("Date is in the past");
                return false;
            }

            return true;
        } catch (DateTimeParseException _) {
            System.out.println("Invalid date format. Expected: MM/DD/YYYY");
            return false;
        }
    }
}
