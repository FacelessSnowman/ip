package snowman.ui;

import snowman.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /** Show welcome message */
    public void showWelcome() {
        System.out.println("""
                ____________________________________________________________
                Hello! I'm snowman
                What can I do for you?
                ____________________________________________________________
                """);
    }

    /** Show a divider line */
    public void showLine() {
        System.out.println(LINE);
    }

    /** Read the next line of user input */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Show the message of the command that added a task */
    public void showAddedTask(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /** Show the message of the command that deleted a task */
    public void showDeletedTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /** Show an error message */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /** Show a generic message */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /** Show the list stored*/
    public void showList(String message) {
        System.out.println(message);
    }
}
