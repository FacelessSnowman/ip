package ip.src.main.java;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /** Load tasks from file */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            if (!file.exists()) {
                file.createNewFile(); // create file if not exist
                return tasks; // empty list on first run
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error loading saved data.");
        }

        return tasks;
    }

    /** Save tasks to file */
    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        try {
            String type = parts[0];
            boolean done = parts[1].equals("1");

            Task task;
            switch (type) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                return null;
            }

            if (done) {
                task.markAsDone();
            }
            return task;
        } catch (Exception e) {
            System.out.println("Warning: corrupted line skipped: " + line);
            return null; // corrupted line
        }
    }
}
