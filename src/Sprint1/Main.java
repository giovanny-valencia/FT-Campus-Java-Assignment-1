package Sprint1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String task1 = "";
        String task2 = "";
        String task3 = "";
        String task4 = "";
        String task5 = "";

        System.out.print("Hello, please enter your name: ");
        String username = scanner.nextLine();

        System.out.println("\nPlease enter up to 5 tasks:");
        int i = 1;

        while(true){
            System.out.print("task #" + i + ": ");
            String task = scanner.nextLine();
            i++;

            if (task.isEmpty()){
                break;
            }

            if (task1.isEmpty()){
                task1 = task;
            } else if (task2.isEmpty()) {
                task2 = task;
            } else if (task3.isEmpty()) {
                task3 = task;
            } else if (task4.isEmpty()) {
                task4 = task;
            } else if (task5.isEmpty()) {
                task5 = task;
                break;
            }

        }

        System.out.println("Task list for " + username + ":");
        if (task1.isEmpty()){
            System.out.println("No tasks entered");
        } else {
            System.out.println("== Increasing order ==");
            System.out.println("Task #1:" + task1);
            if (!task2.isEmpty()){
                System.out.println("Task #2:" + task2);
            }
            if (!task3.isEmpty()){
                System.out.println("Task #3:" + task3);
            }
            if (!task4.isEmpty()){
                System.out.println("Task #4:" + task4);
            }
            if (!task5.isEmpty()){
                System.out.println("Task #5:" + task5);
            }

            System.out.println("== Decreasing order ==");
            if (!task5.isEmpty()){
                System.out.println("Task #5:" + task5);
            }
            if (!task4.isEmpty()){
                System.out.println("Task #4:" + task4);
            }
            if (!task3.isEmpty()){
                System.out.println("Task #3:" + task3);
            }
            if (!task2.isEmpty()){
                System.out.println("Task #2:" + task2);
            }
            System.out.println("Task #1:" + task1);
        }
    }
}
