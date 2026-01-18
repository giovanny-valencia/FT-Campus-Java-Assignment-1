package Sprint5;

import Sprint5.auth.Client;
import Sprint5.auth.User;
import Sprint5.auth.UserDAOImpl;
import Sprint5.exception.InvalidMenuEntry;
import Sprint5.exception.InvalidUserRole;
import Sprint5.menu.MenuHandler;
import Sprint5.task.TaskDAOImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Main maintains local states
        boolean isRunning = true;
        Integer userMenuInput = null;
        User currentUser = null;
        boolean increasingOrder = true;

        Scanner scanner = new Scanner(System.in);
        MenuHandler menuHandler = new MenuHandler();
        UserDAOImpl userDAO = new UserDAOImpl();
        TaskDAOImpl taskDAO = new TaskDAOImpl();

        while (isRunning) {

            while (userMenuInput == null && currentUser == null) {
                menuHandler.displayUserLoginOptions();
                try {
                    System.out.print("choice: ");
                    userMenuInput = Integer.parseInt(scanner.nextLine());
                    if (userMenuInput < 0) {
                        throw new InvalidMenuEntry("Invalid Menu Selection");
                    }
                } catch (InvalidMenuEntry i) {
                    System.out.println(i.getMessage());
                } catch (Exception _) {
                    System.out.println("Something unexpected occurred...");
                }
            }

            if (currentUser == null) {
                switch (userMenuInput) {
                    case MenuHandler.EXIT -> isRunning = false;
                    case MenuHandler.LOGIN -> {
                        String[] userCredentials = menuHandler.displayAndGetUserLogin();
                        currentUser = userDAO.login(userCredentials[0], userCredentials[1]);
                        System.out.println("current user: " + currentUser);
                    }
                    case MenuHandler.REGISTER -> {
                        String[] userRegistrationRequest = menuHandler.displayAndGetUserRegister();
                        int role;

                        try {
                            role = Integer.parseInt(userRegistrationRequest[2]);
                            if (role < 1 || role > 2) {
                                throw new InvalidUserRole("Invalid role entry"); //TODO: handle exception
                            }

                            boolean isRegistered = userDAO.register(userRegistrationRequest[0], userRegistrationRequest[1], role);
                            System.out.println("is Registered?: " + isRegistered);
                        } catch (NumberFormatException | InvalidUserRole e) {
                            System.out.println("invalid role entry");
                        }
                    }
                }
                userMenuInput = null; // stops infinite loop. Not the prettiest of fixes but it works.
                //TODO: condense this onto one handler for client and visitor
            } else if (currentUser instanceof Client) {
                taskDAO.printTasks(currentUser);
                menuHandler.displayMenuOptions(currentUser);
                try {
                    System.out.print("choice: ");
                    userMenuInput = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException | NullPointerException _) {
                    //throw new RuntimeException("Invalid input");
                    userMenuInput = -1;
                }

                switch (userMenuInput) {
                    case MenuHandler.EXIT -> isRunning = false;
                    case MenuHandler.ADD_TASK -> {
                        System.out.println("add Task");
                        String[] taskData = menuHandler.handleAddTask();
                        taskDAO.addTask(taskData[0], taskData[1], taskData[2], taskData[3]);
                    }
                    case MenuHandler.UPDATE_TASK -> {
                        String[] updatedTaskData = menuHandler.handleUpdateTask(taskDAO, currentUser);
                        try {
                            int idToChange = Integer.parseInt(updatedTaskData[0]);
                            taskDAO.updateTask(idToChange, updatedTaskData[1], updatedTaskData[2], updatedTaskData[3], updatedTaskData[4]);
                        } catch (NumberFormatException _) {
                            System.out.println("Invalid ID entry");
                        }
                    }
                    case MenuHandler.DELETE_TASK -> {
                        try {
                            int idToDelete = menuHandler.handleTaskDelection(taskDAO, currentUser);
                            taskDAO.deleteTask(idToDelete);
                        } catch (InvalidMenuEntry e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case MenuHandler.SEARCH_TASK -> {
                        taskDAO.searchForTask(menuHandler.handleTaskSearch());
                    }
                    case MenuHandler.LOGOUT -> {
                        System.out.println("logout");
                        currentUser = null;
                    }

                    case MenuHandler.RESORT -> taskDAO.reSortTasks(increasingOrder = !increasingOrder);

                    default -> System.out.println("Invalid input");

                }
            } else { // visitor
                taskDAO.printTasks(currentUser);
                menuHandler.displayMenuOptions(currentUser);
                System.out.println("current value going into visitor: " + userMenuInput);
                try {
                    System.out.print("choice: ");
                    userMenuInput = Integer.parseInt(scanner.nextLine());
                    if (!menuHandler.isValidVisitorEntry(userMenuInput)) {
                        throw new InvalidMenuEntry("Invalid Menu Entry");
                    }
                } catch (NumberFormatException | NullPointerException | InvalidMenuEntry _) { // keep program running
                    userMenuInput = -1;
                }

                switch (userMenuInput) {
                    case MenuHandler.EXIT -> isRunning = false;
                    case MenuHandler.COMPLETED_TASK -> {
                        try {
                            int completedTaskID = menuHandler.handleTaskCompletion(taskDAO, currentUser);
                            taskDAO.markTaskCompleted(completedTaskID, currentUser);
                        } catch (InvalidMenuEntry e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case MenuHandler.LOGOUT -> currentUser = null;
                    case MenuHandler.RESORT -> taskDAO.reSortTasks(increasingOrder = !increasingOrder);
                    default -> System.out.println("Invalid input");

                }

            }

        }
    }
}
