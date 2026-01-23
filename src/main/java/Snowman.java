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

            if(input.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < enterList.size(); i++) {
                    System.out.println((i + 1) + ". " + enterList.get(i));
                }
                System.out.println(line);

            } else if(input.equals("bye")) {
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

            } else {
                Task task = new Task(input);
                enterList.add(task);

                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }
    }
}
