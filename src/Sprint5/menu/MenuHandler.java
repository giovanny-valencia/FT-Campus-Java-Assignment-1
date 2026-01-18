package Sprint5.menu;

import Sprint5.auth.Client;
import Sprint5.auth.User;
import Sprint5.exception.InvalidMenuEntry;
import Sprint5.task.TaskDAOImpl;

import java.util.Arrays;
import java.util.Scanner;

public class MenuHandler {
    public static final int EXIT = 0;
    public static final int LOGIN = 1;
    public static final int COMPLETED_TASK = 1;
    public static final int REGISTER = 2;
    public static final int ADD_TASK = 1;
    public static final int UPDATE_TASK = 2;
    public static final int DELETE_TASK = 3;
    public static final int SEARCH_TASK = 4;
    public static final int LOGOUT = 5;
    public static final int RESORT = 6;

    public void displayUserLoginOptions() {
        System.out.println("0 -- Exit");
        System.out.println("1 -- Login");
        System.out.println("2 -- Register");
    }

    public void displayMenuOptions(User user) {
        if (user instanceof Client) {
            System.out.println("== Menu Options ==");
            System.out.println("0 -- Exit");
            System.out.println("1 -- Add Task");
            System.out.println("2 -- Update An Existing Task");
            System.out.println("3 -- Delete A Task");
            System.out.println("4 -- Search For A Specific Task");
            System.out.println("5 -- Log Out");
            System.out.println("6 -- Resort Tasks Based On Date (increasing or descending)");
        } else {
            System.out.println("== Menu Options ==");
            System.out.println("1 -- Mark A Task For Completion");
            System.out.println("0 -- Exit");
            System.out.println("5 -- Log Out");
            System.out.println("6 -- Resort Tasks Based On Date (increasing or descending)");
        }
    }

    public boolean isValidVisitorEntry(int input) {
        if (input < 0 || input > 6) {
            return false;
        }

        return input == EXIT || input == COMPLETED_TASK || input == LOGOUT || input == RESORT;
    }

    public String[] displayAndGetUserLogin() {
        Scanner scanner = new Scanner(System.in);
        String[] userCredentials = new String[2];

        System.out.println("== LogIn ==");
        System.out.print("username: ");
        userCredentials[0] = scanner.nextLine();
        System.out.print("password: ");
        userCredentials[1] = scanner.nextLine();

        return userCredentials;
    }

    public String[] displayAndGetUserRegister() {
        Scanner scanner = new Scanner(System.in);
        String[] userRegistrationRequest = new String[3];

        System.out.println("== Registering Account ==");
        System.out.print("Username: ");
        userRegistrationRequest[0] = scanner.nextLine();
        System.out.print("Password: ");
        userRegistrationRequest[1] = scanner.nextLine();
        System.out.println("Select Account Type: \n1--Client \n2--Visitor");
        userRegistrationRequest[2] = scanner.nextLine();

        return userRegistrationRequest;
    }

    public String[] handleAddTask() {
        Scanner scanner = new Scanner(System.in);
        String[] taskData = new String[4];

        System.out.println("== Task Creation ==");
        System.out.print("Enter title for the task: ");
        taskData[0] = scanner.nextLine();
        System.out.print("Enter description for the task: ");
        taskData[1] = scanner.nextLine();
        System.out.print("Enter assignee's name for this task: ");
        taskData[2] = scanner.nextLine();
        System.out.print("Enter completion date for this task: ");
        taskData[3] = scanner.nextLine();

        return taskData;
    }

    public String[] handleUpdateTask(TaskDAOImpl taskDAO, User currentUser) {
        Scanner scanner = new Scanner(System.in);
        String[] updatedTaskData = new String[5];
        Arrays.fill(updatedTaskData, ""); // resolves null values

        System.out.println("== Updating Task ==");
        System.out.println("Please state the task ID of the task you wish to update");
        taskDAO.printTasks(currentUser);

        System.out.print("ID: ");
        updatedTaskData[0] = scanner.nextLine();
        System.out.print("Enter new title for the task (No change? Click Enter): ");
        updatedTaskData[1] = scanner.nextLine();
        System.out.print("Enter description for the task (No change? Click Enter): ");
        updatedTaskData[2] = scanner.nextLine();
        System.out.print("Enter assignee's name for this task (No change? Click Enter): ");
        updatedTaskData[3] = scanner.nextLine();
        System.out.print("Enter completion date for this task (No change? Click Enter): ");
        updatedTaskData[4] = scanner.nextLine();

        return updatedTaskData;
    }

    public int handleTaskDelection(TaskDAOImpl taskDAO, User currentUser) throws InvalidMenuEntry {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Please state the task number you wish to delete");
        taskDAO.printTasks(currentUser);

        System.out.print("ID: ");
        input = scanner.nextLine();

        try {
            int idToDelete = Integer.parseInt(input);
            if (idToDelete < 1) {
                throw new InvalidMenuEntry("ID must be a positive number.");
            }
            return idToDelete;
        } catch (NumberFormatException _) {
            throw new InvalidMenuEntry("Invalid non-numeric entry.");
        }
    }

    public String handleTaskSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please state the task description you wish to Search");
        return scanner.nextLine();
    }

    public int handleTaskCompletion(TaskDAOImpl taskDAO, User currentUser) throws InvalidMenuEntry {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please state the task ID you completed");
        taskDAO.printTasks(currentUser);

        System.out.print("ID: ");
        String input = scanner.nextLine();

        try {
            int id = Integer.parseInt(input);
            if (id < 0) {
                throw new InvalidMenuEntry("ID must be a positive number.");
            }
            return id;
        } catch (NumberFormatException | NullPointerException | InvalidMenuEntry _) {
            throw new InvalidMenuEntry("Invalid ID entry");
        }
    }
}
