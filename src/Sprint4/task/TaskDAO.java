package Sprint4.task;

import Sprint4.auth.User;

public interface TaskDAO {
    void addTask(String title, String description, String assignee);
    void updateTask(int taskId, String title, String description, String assignee);
    void deleteTask(int taskId, User currentUser);
    void searchForTask(String description);
    void printTasks(User user);
    boolean isEmpty();
}
