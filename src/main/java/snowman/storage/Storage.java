package snowman.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import snowman.task.Deadline;
import snowman.task.Event;
import snowman.task.Task;
import snowman.task.Todo;

/**
 * Handles loading and saving tasks to a file for the Snowman application.
 * Provides methods to read tasks from storage at startup and write tasks back to storage.
 */
public class Storage {
    /** Path of the file used for storing tasks. */
    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath Path to the file used for saving and loading tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * Creates a new file if it does not exist.
     * Skips corrupted lines with a warning.
     *
     * @return List of tasks loaded from the file, or an empty list if the file is new or empty.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs(); // create folder if not exist
            }

            if (!file.exists()) {
                file.createNewFile(); // create file if not exist
                return tasks; // empty list on first run
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                assert task == null || task instanceof Task : "Parsed object must be a Task or null";
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

    /**
     * Saves a list of tasks to the storage file.
     * Each task is written in a format suitable for loading later.
     *
     * @param tasks List of tasks to save.
     */
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

    /**
     * Parses a single line from the storage file into a Task object.
     * Supports Todo, Deadline, and Event tasks.
     * Marks tasks as done if indicated.
     * Returns null if the line is corrupted or the type is unrecognized.
     *
     * @param line Line from the file representing a task.
     * @return Parsed Task object, or null if parsing failed.
     */
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
