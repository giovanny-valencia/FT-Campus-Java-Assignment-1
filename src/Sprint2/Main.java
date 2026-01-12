package Sprint2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int EXIT = 0;
        int ADD_TASK = 1;
        int UPDATE_TASK = 2;
        int DELETE_TASK = 3;
        int SEARCH_TASK = 4;
        int userInput;
        Scanner scanner = new Scanner(System.in);
        Task tasks = new Task();

        System.out.print("Hello, please enter your name: ");
        String username = scanner.nextLine();

        while(true){
            if (tasks.isEmpty()){
                System.out.println("Hi, " + username);
            } else {
                System.out.println("Here are your current tasks, " + username + ":");
                tasks.printTasks();
            }

            System.out.println("Menu Options:");
            System.out.println("0 -- Exit");
            System.out.println("1 -- Add Task");
            System.out.println("2 -- Update An Existing Task");
            System.out.println("3 -- Delete A Task");
            System.out.println("4 -- Search For A Specific Task");

            userInput = scanner.nextInt();

            System.out.println("user input: " + userInput);
            scanner.nextLine(); // consume the leftover new line

            if (userInput == ADD_TASK){
                System.out.println("Please enter new task: ");
                String task = scanner.nextLine();
                if (!task.isEmpty()){
                    tasks.addTask(task);
                }
            } else if (userInput == UPDATE_TASK) {
                System.out.println("Please state the task number you wish to update:");
                tasks.printTasks();
                int taskSelection = scanner.nextInt();
                scanner.nextLine(); // consume the leftover new line

                if (taskSelection < 1 || taskSelection > tasks.size()){
                    System.out.println("invalid task entry");
                    continue;
                }

                System.out.println("Updating with :");
                String updatedTask = scanner.nextLine();

                if (!updatedTask.isEmpty()){
                    tasks.updateTask(taskSelection, updatedTask);
                }
            } else if (userInput == DELETE_TASK) {
                System.out.println("Please state the task number you wish to delete");
                tasks.printTasks();
                int taskSelection = scanner.nextInt();
                scanner.nextLine(); // consume the leftover new line
                if (taskSelection < 1 || taskSelection > tasks.size()){
                    System.out.println("invalid task entry");
                    continue;
                }

                tasks.deleteTask(taskSelection);
                System.out.println("Successful task deletion");

            } else if (userInput == SEARCH_TASK) {
                System.out.println("Please state the task description you wish to Search");
                String description = scanner.nextLine();

                if (description.isEmpty()){
                    System.out.println("nothing entered");
                    continue;
                }

                String foundTask = tasks.searchForTask(description);

                if (foundTask == null){
                    System.out.println("No entry found for: " + description);
                    continue;
                }

                System.out.println("Task found: " + foundTask);
            } else if (userInput == EXIT) {
                System.out.println("Exit");
                break;
            }
        }
    }
}
