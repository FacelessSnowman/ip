package snowman.task;

import java.util.ArrayList;

import snowman.SnowmanException;

/**
 * TaskList stores and manages the list of tasks.
 * All operations related to adding, deleting, marking, unmarking, and listing tasks
 * are encapsulated here.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Default constructor initializes empty task list
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Overloaded constructor with initial tasks in storage
     */
    public TaskList(ArrayList<Task> storedTasks) {
        tasks = storedTasks;
    }

    /**
     * Add a task to the list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Remove a task by 0-based index
     */
    public Task remove(int index) throws SnowmanException {
        if (index < 0 || index >= tasks.size()) {
            throw new SnowmanException("Invalid task number to delete.");
        }
        return tasks.remove(index);
    }

    /**
     * Get a task at a specific index
     */
    public Task get(int index) throws SnowmanException {
        if (index < 0 || index >= tasks.size()) {
            throw new SnowmanException("Invalid task number.");
        }
        return tasks.get(index);
    }

    /**
     * Return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Return all tasks (for listing)
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
