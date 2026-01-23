import java.util.Scanner;

public class Snowman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                ____________________________________________________________
                Hello! I'm snowman
                What can I do for you?
                ____________________________________________________________
                """);

        while (true) {
            String input = scanner.nextLine();

            if(input.equals("bye")) {
                System.out.println("""
                        ____________________________________________________________
                        Bye. Hope to see you again soon!
                        ____________________________________________________________
                        """);
                break;
            }

            System.out.print("____________________________________________________________\n"
            + input + "\n____________________________________________________________\n");

        }
    }
}
