package ip.src.main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Snowman {
//    private static final String LINE = "____________________________________________________________";
//    private static final String FILE_PATH = "data/storage.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Snowman(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (SnowmanException e) {
//            ui.showError(e.getMessage());
//            tasks = new TaskList();
//        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SnowmanException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Snowman("data/storage.txt").run();
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Storage storage = new Storage(FILE_PATH);
////        ArrayList<Task> enterList = storage.load();
//
////        System.out.println("""
////                ____________________________________________________________
////                Hello! I'm snowman
////                What can I do for you?
////                ____________________________________________________________
////                """);
//
//        while (true) {
//            String input = scanner.nextLine();
//
//            try {
//                if (input.equals("list")) {
//                    System.out.println(LINE);
//                    System.out.println("Here are the tasks in your list:");
//                    for (int i = 0; i < enterList.size(); i++) {
//                        System.out.println((i + 1) + ". " + enterList.get(i));
//                    }
//                    System.out.println(LINE);
//
//                } else if (input.equals("bye")) {
//                    System.out.println(LINE + "\n"
//                            + "Bye. Hope to see you again soon!\n"
//                            + LINE);
//                    break;
//
//                } else if (input.startsWith("mark ")) {
//                    int index = Integer.parseInt(input.substring(5)) - 1;
//                    Task task = enterList.get(index);
//                    task.markAsDone();
//                    storage.save(enterList);
//
//                    System.out.println(LINE);
//                    System.out.println("Nice! I've marked this task as done:");
//                    System.out.println("  " + task);
//                    System.out.println(LINE);
//
//                } else if (input.startsWith("unmark ")) {
//                    int index = Integer.parseInt(input.substring(7)) - 1;
//                    Task task = enterList.get(index);
//                    task.unmark();
//                    storage.save(enterList);
//
//                    System.out.println(LINE);
//                    System.out.println("OK, I've marked this task as not done yet:");
//                    System.out.println("  " + task);
//                    System.out.println(LINE);
//
//                } else if (input.startsWith("todo ")) {
//                    String desc = input.substring(5);
//                    if (desc.isEmpty()) {
//                        throw new SnowmanException("Error, the description of a task cannot be empty.");
//                    }
//                    Task task = new Todo(desc);
//                    enterList.add(task);
//                    storage.save(enterList);
//                    printTask(task, enterList.size());
//
//                } else if (input.startsWith("deadline ")) {
//                    String[] inputSegment = input.substring(9).split(" /by ", 2);
//                    if (inputSegment.length < 2
//                            || inputSegment[0].trim().isEmpty()
//                            || inputSegment[1].trim().isEmpty()) {
//                        throw new SnowmanException("Error, the description of a task cannot be empty.");
//                    }
//                    Task task = new Deadline(inputSegment[0].trim(), inputSegment[1].trim());
//                    enterList.add(task);
//                    storage.save(enterList);
//                    printTask(task, enterList.size());
//
//                } else if (input.startsWith("event ")) {
//                    String[] inputSegment = input.substring(6).split(" /from | /to ", 3);
//                    if (inputSegment.length < 3
//                            || inputSegment[0].trim().isEmpty()
//                            || inputSegment[1].trim().isEmpty()
//                            || inputSegment[2].trim().isEmpty()) {
//                        throw new SnowmanException("Error, the description of a task cannot be empty.");
//                    }
//                    Task task = new Event(inputSegment[0].trim(), inputSegment[1].trim(), inputSegment[2].trim());
//                    enterList.add(task);
//                    storage.save(enterList);
//                    printTask(task, enterList.size());
//
//                } else if (input.startsWith("delete ")) {
//                    String arg = input.substring(7).trim(); // everything after "delete "
//                    if (arg.isEmpty()) {
//                        throw new SnowmanException("Error, the task number to delete cannot be empty.");
//                    }
//                    try {
//                        int index = Integer.parseInt(arg) - 1; // convert to 0-based index
//                        Task task = enterList.remove(index);
//                        storage.save(enterList);
//
//                        System.out.println(LINE);
//                        System.out.println("Noted. I've removed this task:");
//                        System.out.println("  " + task);
//                        System.out.println("Now you have " + enterList.size() + " tasks in the list.");
//                        System.out.println(LINE);
//                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
//                        throw new SnowmanException("Might have missed out description/index of task.");
//                    }
//                } else if (input.startsWith("on ")) {
//                    String dateStr = input.substring(3).trim(); // everything after "on "
//                    if (dateStr.isEmpty()) {
//                        throw new SnowmanException("Please specify a date in yyyy-MM-dd format.");
//                    }
//                    try {
//                        LocalDate date = LocalDate.parse(dateStr);
//                        System.out.println(LINE);
//                        System.out.println("Tasks on " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
//
//                        boolean found = false;
//                        for (Task task : enterList) {
//                            if (task instanceof Deadline) {
//                                Deadline deadline = (Deadline) task;
//                                if (deadline.getTime().equals(date)) {
//                                    System.out.println("  " + deadline);
//                                    found = true;
//                                }
//                            } else if (task instanceof Event) {
//                                Event event = (Event) task;
//                                if (!date.isBefore(event.getStart()) && !date.isAfter(event.getEnd())) {
//                                    System.out.println("  " + event);
//                                    found = true;
//                                }
//                            }
//                        }
//
//                        if (!found) {
//                            System.out.println("No tasks found on this date.");
//                        }
//
//                        System.out.println(LINE);
//
//                    } catch (Exception e) {
//                        throw new SnowmanException("Invalid date format. Use yyyy-MM-dd.");
//                    }
//                } else {
//                    throw new SnowmanException("Wait...what are you typing? Might have missed out description/index of task.");
//                }
//            } catch (SnowmanException e) {
//                System.out.println(LINE + "\n" + e.getMessage() + "\n" + LINE);
//            }
//        }
//    }
//
//    private static void printTask(Task task, int size) {
//        System.out.println(LINE);
//        System.out.println("Got it. I've added this task:");
//        System.out.println("  " + task);
//        System.out.println("Now you have " + size + " tasks in the list.");
//        System.out.println(LINE);
//    }
}
