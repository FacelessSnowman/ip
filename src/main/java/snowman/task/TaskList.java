package ip.src.main.java.snowman.task;

import ip.src.main.java.snowman.SnowmanException;

import java.util.ArrayList;

/**
 * TaskList stores and manages the list of tasks.
 * All operations related to adding, deleting, marking, unmarking, and listing tasks
 * are encapsulated here.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /** Default constructor initializes empty task list */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /** Overloaded constructor with initial tasks in storage */
    public TaskList(ArrayList<Task> storedTasks) {
        tasks = storedTasks;
    }

    /** Add a task to the list */
    public void add(Task task) {
        tasks.add(task);
    }

    /** Remove a task by 0-based index */
    public Task remove(int index) throws SnowmanException {
        if (index < 0 || index >= tasks.size()) {
            throw new SnowmanException("Invalid task number to delete.");
        }
        return tasks.remove(index);
    }

    /** Mark a task as done */
    public Task mark(int index) throws SnowmanException {
        if (index < 0 || index >= tasks.size()) {
            throw new SnowmanException("Invalid task number to mark.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /** Unmark a task */
    public Task unmark(int index) throws SnowmanException {
        if (index < 0 || index >= tasks.size()) {
            throw new SnowmanException("Invalid task number to unmark.");
        }
        Task task = tasks.get(index);
        task.unmark();
        return task;
    }

    /** Get a task at a specific index */
    public Task get(int index) throws SnowmanException {
        if (index < 0 || index >= tasks.size()) {
            throw new SnowmanException("Invalid task number.");
        }
        return tasks.get(index);
    }

    /** Return the number of tasks */
    public int size() {
        return tasks.size();
    }

    /** Return all tasks (for listing) */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /** Return a string of all tasks formatted for display */
    public String listTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }
}
