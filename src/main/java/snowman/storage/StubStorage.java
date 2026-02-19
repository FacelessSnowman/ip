package snowman.storage;

import java.util.ArrayList;

import snowman.task.Task;

/**
 * A stub implementation of {@code Storage} used for testing purposes.
 * <p>
 * This class overrides the {@link #save(ArrayList)} method to prevent actual file I/O operations,
 * allowing tests to run without modifying real storage files.
 * It uses a dummy file path and does not persist any data.
 */
public class StubStorage extends Storage {
    /**
     * Creates a StubStorage instance with a dummy file path.
     * No data will be written to disk.
     */
    public StubStorage() {
        super("dummy.txt");
    }

    /**
     * Overrides the save method to disable writing to file during testing.
     *
     * @param tasks The list of tasks to be saved (ignored).
     */
    @Override
    public void save(ArrayList<Task> tasks) {
        // do nothing
    }
}
