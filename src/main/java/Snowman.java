package ip.src.main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class Snowman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = "____________________________________________________________";
        String nextLine = "\n";

        ArrayList<Task> enterList = new ArrayList<>(100);

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
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < enterList.size(); i++) {
                        System.out.println((i + 1) + ". " + enterList.get(i));
                    }
                    System.out.println(line);

                } else if (input.equals("bye")) {
                    System.out.println(line + nextLine
                            + "Bye. Hope to see you again soon!\n"
                            + line);
                    break;

                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    Task task = enterList.get(index);
                    task.markAsDone();

                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task);
                    System.out.println(line);

                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    Task task = enterList.get(index);
                    task.unmark();

                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + task);
                    System.out.println(line);

                } else if (input.startsWith("todo ")) {
                    String desc = input.substring(5);
                    if (desc.isEmpty()) {
                        throw new SnowmanException("Error, the description of a task cannot be empty.");
                    }
                    Task task = new Todo(desc);
                    enterList.add(task);
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
                    printTask(task, enterList.size());

                } else {
                    throw new SnowmanException("Wait...what are you typing? Might have missed out description/index of task.");
                }
            } catch (SnowmanException e) {
                System.out.println(line + "\n" + e.getMessage() + "\n" + line);
            }
        }
    }

    private static void printTask(Task task, int size) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(line);
    }
}
