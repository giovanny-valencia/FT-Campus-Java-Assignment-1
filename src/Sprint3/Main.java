package Sprint3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // menu options
        final int EXIT = 0;
        final int ADD_TASK = 1;
        final int UPDATE_TASK = 2;
        final int DELETE_TASK = 3;
        final int SEARCH_TASK = 4;
        // accept user input
        Scanner scanner = new Scanner(System.in);
        // Data Access Object -- ArrayList of tasks and business logic functions
        TaskDAO taskDAO = new TaskDAO();

        System.out.print("Hello, please enter your name: ");
        String username = scanner.nextLine();

        boolean isRunning = true;

        while(isRunning){
            if(taskDAO.isEmpty()){
                System.out.println("Hi, " + username);
            } else {
                System.out.println("Displaying Current Tasks");
                taskDAO.printTasks();
            }

            System.out.println("== Menu Options ==");
            System.out.println("0 -- Exit");
            System.out.println("1 -- Add Task");
            System.out.println("2 -- Update An Existing Task");
            System.out.println("3 -- Delete A Task");
            System.out.println("4 -- Search For A Specific Task");

            int userInput = scanner.nextInt();
            scanner.nextLine(); // consume the leftover new line

            switch (userInput){
                case EXIT -> isRunning = false;

                case ADD_TASK -> {
                    System.out.print("Enter title for the task: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description for the task: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter assignee's name for this task: ");
                    String assignee = scanner.nextLine();

                    taskDAO.addTask(title, description, assignee);
                }

                case UPDATE_TASK -> {
                    System.out.println("Please state the task ID of the task you wish to update");
                    taskDAO.printTasks();

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

                case DELETE_TASK -> {
                    System.out.println("Please state the task number you wish to delete");
                    taskDAO.printTasks();

                    System.out.print("ID: ");
                    int taskToDelete = scanner.nextInt();
                    scanner.nextLine(); // consume empty line
                    taskDAO.deleteTask(taskToDelete);
                }

                case SEARCH_TASK -> {
                    System.out.println("Please state the task description you wish to Search");
                    String description = scanner.nextLine();
                    taskDAO.searchForTask(description);
                }

                default -> System.out.println("Invalid Entry...");
            }
        }
    }
}
