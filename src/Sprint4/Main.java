package Sprint4;

import java.util.Scanner;

import Sprint4.auth.User;
import Sprint4.auth.UserDAO;
import Sprint4.auth.UserDAOImpl;
import Sprint4.auth.Visitor;
import Sprint4.menu.*;
import Sprint4.task.TaskDAOImpl;

public class Main {
    public static void main(String[] args) {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        UserDAOImpl userDAO = new UserDAOImpl();
        TaskDAOImpl taskDAO = new TaskDAOImpl();
        User currentUser = null; // hold session of current authorized user

        while(isRunning){
            if (currentUser == null){
                System.out.println("0 -- Exit");
                System.out.println("1 -- Login");
                System.out.println("2 -- Register");

                int userSelection = scanner.nextInt();
                scanner.nextLine(); // consume new line

                switch (userSelection){
                    case Menu.EXIT -> isRunning = false;

                    case Menu.LOGIN -> {
                        System.out.println("== LogIn ==");
                        System.out.print("Username: ");
                        String username = scanner.nextLine();
                        System.out.print("Password: ");
                        String password = scanner.nextLine();

                        // null or authorized client / visitor
                        currentUser = userDAO.login(username, password);

                        if (currentUser == null){
                            System.out.println("Incorrect Credentials");
                        }
                    }
                    case Menu.REGISTER -> {
                        System.out.println("== Registering Account ==");
                        System.out.print("Username: ");
                        String username = scanner.nextLine();
                        System.out.print("Password: ");
                        String password = scanner.nextLine();
                        System.out.println("Select Account Type: \n1--Client \n2--Visitor");
                        int accountTypeSelection = scanner.nextInt();
                        scanner.nextLine();

                        boolean isRegistered = userDAO.register(username, password, accountTypeSelection);
                        if (!isRegistered){
                            System.out.println("Registration failed (Invalid entry or user exists)");
                        } else {
                            System.out.println("Registration successful!");
                        }
                    }
                }

            } else { // user is logged in
                System.out.println("Hi, " + currentUser.getUsername());

                if (!taskDAO.isEmpty()){
                    taskDAO.printTasks(currentUser);
                }

                Menu.displayMenuOptions();
                int userInput = scanner.nextInt();
                scanner.nextLine(); // consume the leftover new line

                switch (userInput){
                    case Menu.EXIT -> isRunning = false;

                    case Menu.ADD_TASK -> {
                        if(!currentUser.hasClientRole(currentUser)){
                            System.out.println("Visitors cannot add tasks");
                            break;
                        }

                        System.out.print("Enter title for the task: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter description for the task: ");
                        String description = scanner.nextLine();
                        System.out.print("Enter assignee's name for this task: ");
                        String assignee = scanner.nextLine();

                        taskDAO.addTask(title, description, assignee);
                    }

                    case Menu.UPDATE_TASK -> {
                        if(!currentUser.hasClientRole(currentUser)){
                            System.out.println("Visitors cannot update tasks");
                            break;
                        }

                        System.out.println("Please state the task ID of the task you wish to update");
                        taskDAO.printTasks(currentUser);

                        System.out.print("ID: ");
                        int taskToChange = scanner.nextInt();
                        scanner.nextLine(); // consume empty line

                        System.out.print("Enter new title for the task (No change? Click Enter): ");
                        String title = scanner.nextLine();

                        System.out.print("Enter description for the task (No change? Click Enter): ");
                        String description = scanner.nextLine();

                        System.out.print("Enter assignee's name for this task (No change? Click Enter): ");
                        String assignee = scanner.nextLine();

                        taskDAO.updateTask(taskToChange, title, description, assignee);
                    }

                    case Menu.DELETE_TASK -> {
                        if(!currentUser.hasClientRole(currentUser)){
                            System.out.println("Visitors cannot delete tasks");
                            break;
                        }

                        System.out.println("Please state the task number you wish to delete");
                        taskDAO.printTasks(currentUser);

                        System.out.print("ID: ");
                        int taskToDelete = scanner.nextInt();
                        scanner.nextLine(); // consume empty line
                        taskDAO.deleteTask(taskToDelete, currentUser);
                    }

                    case Menu.SEARCH_TASK -> {
                        if(!currentUser.hasClientRole(currentUser)){
                            System.out.println("Visitors cannot search tasks");
                            break;
                        }

                        System.out.println("Please state the task description you wish to Search");
                        String description = scanner.nextLine();
                        taskDAO.searchForTask(description);
                    }

                    case Menu.LOGOUT -> {
                        currentUser = null;
                    }
                }
            }
        }
    }
}
