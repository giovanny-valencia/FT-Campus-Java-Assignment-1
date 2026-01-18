package Sprint5.task;

import Sprint5.auth.User;

public interface TaskDAO {
    void addTask(String title, String description, String assignee, String completionDate);

    void updateTask(int taskId, String title, String description, String assignee, String completionDate);

    void deleteTask(int taskId);

    void searchForTask(String description);

    void markTaskCompleted(int taskId, User user);

    void printTasks(User user);

    boolean isEmpty();
}
