package Sprint4.menu;

public class Menu {
    public static final int EXIT = 0;
    public static final int LOGIN = 1;
    public static final int REGISTER = 2;
    public static final int ADD_TASK = 1;
    public static final int UPDATE_TASK = 2;
    public static final int DELETE_TASK = 3;
    public static final int SEARCH_TASK = 4;
    public static final int LOGOUT = 5;

    public static void displayMenuOptions(){
        System.out.println("== Menu Options ==");
        System.out.println("0 -- Exit");
        System.out.println("1 -- Add Task");
        System.out.println("2 -- Update An Existing Task");
        System.out.println("3 -- Delete A Task");
        System.out.println("4 -- Search For A Specific Task");
        System.out.println("5 -- Log Out");
    }
}
