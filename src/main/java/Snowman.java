import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Snowman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = "____________________________________________________________";
        String nextLine = "\n";

        ArrayList<String> enterList = new ArrayList<>(100);

        System.out.println("""
                ____________________________________________________________
                Hello! I'm snowman
                What can I do for you?
                ____________________________________________________________
                """);

        while (true) {
            String input = scanner.nextLine();

            if(input.equals("bye")) {
                System.out.println(line + nextLine
                        + "Bye. Hope to see you again soon!\n"
                        + line);
                break;
            }

            if(input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < enterList.size(); i++) {
                    System.out.println((i + 1) + ". " + enterList.get(i));
                }
                System.out.println(line);
            } else {
                enterList.add(input);

                System.out.print(line + nextLine
                        + "added: " + input + "\n"
                        + line + nextLine);
            }
        }
    }
}
