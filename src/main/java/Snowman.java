package ip.src.main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Snowman {
    private static final String LINE = "____________________________________________________________";
    private static final String FILE_PATH = "data/storage.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage(FILE_PATH);
        ArrayList<Task> enterList = storage.load();

        System.out.println("""
                ____________________________________________________________
                Hello! I'm snowman
                What can I do for you?
                ____________________________________________________________
                """);

        while (true) {
            String input = scanner.nextLine();

            try {
                if (input.equals("list")) {
                    System.out.println(LINE);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < enterList.size(); i++) {
                        System.out.println((i + 1) + ". " + enterList.get(i));
                    }
                    System.out.println(LINE);

                } else if (input.equals("bye")) {
                    System.out.println(LINE + "\n"
                            + "Bye. Hope to see you again soon!\n"
                            + LINE);
                    break;

                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    Task task = enterList.get(index);
                    task.markAsDone();
                    storage.save(enterList);

                    System.out.println(LINE);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task);
                    System.out.println(LINE);

                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    Task task = enterList.get(index);
                    task.unmark();
                    storage.save(enterList);

                    System.out.println(LINE);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + task);
                    System.out.println(LINE);

                } else if (input.startsWith("todo ")) {
                    String desc = input.substring(5);
                    if (desc.isEmpty()) {
                        throw new SnowmanException("Error, the description of a task cannot be empty.");
                    }
                    Task task = new Todo(desc);
                    enterList.add(task);
                    storage.save(enterList);
                    printTask(task, enterList.size());

                } else if (input.startsWith("deadline ")) {
                    String[] inputSegment = input.substring(9).split(" /by ", 2);
                    if (inputSegment.length < 2
                            || inputSegment[0].trim().isEmpty()
                            || inputSegment[1].trim().isEmpty()) {
                        throw new SnowmanException("Error, the description of a task cannot be empty.");
                    }
                    Task task = new Deadline(inputSegment[0], inputSegment[1]);
                    enterList.add(task);
                    storage.save(enterList);
                    printTask(task, enterList.size());

                } else if (input.startsWith("event ")) {
                    String[] inputSegment = input.substring(6).split(" /from | /to ", 3);
                    if (inputSegment.length < 3
                            || inputSegment[0].trim().isEmpty()
                            || inputSegment[1].trim().isEmpty()
                            || inputSegment[2].trim().isEmpty()) {
                        throw new SnowmanException("Error, the description of a task cannot be empty.");
                    }
                    Task task = new Event(inputSegment[0], inputSegment[1], inputSegment[2]);
                    enterList.add(task);
                    storage.save(enterList);
                    printTask(task, enterList.size());

                } else if (input.startsWith("delete ")) {
                    String arg = input.substring(7).trim(); // everything after "delete "
                    if (arg.isEmpty()) {
                        throw new SnowmanException("Error, the task number to delete cannot be empty.");
                    }
                    try {
                        int index = Integer.parseInt(arg) - 1; // convert to 0-based index
                        Task task = enterList.remove(index);
                        storage.save(enterList);

                        System.out.println(LINE);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + task);
                        System.out.println("Now you have " + enterList.size() + " tasks in the list.");
                        System.out.println(LINE);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        throw new SnowmanException("Might have missed out description/index of task.");
                    }
                } else {
                    throw new SnowmanException("Wait...what are you typing? Might have missed out description/index of task.");
                }
            } catch (SnowmanException e) {
                System.out.println(LINE + "\n" + e.getMessage() + "\n" + LINE);
            }
        }
    }

    private static void printTask(Task task, int size) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE);
    }
}
