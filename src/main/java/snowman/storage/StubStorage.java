package snowman.storage;

import snowman.task.Task;
import java.util.ArrayList;

public class StubStorage extends Storage {
    public StubStorage() {
        super("dummy.txt");
    }

    @Override
    public void save(ArrayList<Task> tasks) {
        // do nothing
    }
}
