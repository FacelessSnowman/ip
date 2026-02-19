package snowman.task;

import java.util.ArrayList;

import snowman.SnowmanException;

/**
 * Stores and manages a list of tasks in the Snowman application.
 * <p>
 * Provides methods to add, remove, retrieve, and get the number of tasks.
 * Encapsulates all operations related to task management.
 */
public class TaskList {

    /** Internal list of tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList initialized with tasks loaded from storage.
     *
     * @param storedTasks List of tasks retrieved from storage.
     */
    public TaskList(ArrayList<Task> storedTasks) {
        tasks = storedTasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index 0-based index of the task to remove.
     * @return The task that was removed.
     * @throws SnowmanException If the index is out of bounds.
     */
    public Task remove(int index) throws SnowmanException {
        if (index < 0 || index >= tasks.size()) {
            throw new SnowmanException("Invalid task number to delete.");
        }
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index 0-based index of the task to retrieve.
     * @return The task at the given index.
     * @throws SnowmanException If the index is out of bounds.
     */
    public Task get(int index) throws SnowmanException {
        if (index < 0 || index >= tasks.size()) {
            throw new SnowmanException("Task number out of range.");
        }
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns all tasks in the list.
     * <p>
     * Useful for listing or saving tasks.
     *
     * @return ArrayList of all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
