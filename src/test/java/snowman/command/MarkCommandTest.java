package snowman.command;

import org.junit.jupiter.api.Test;
import snowman.task.TaskList;
import snowman.task.Todo;
import snowman.ui.FakeUi;
import snowman.storage.StubStorage;

import static org.junit.jupiter.api.Assertions.*;

public class MarkCommandTest {
    @Test
    public void execute_validIndex_taskMarkedDone() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));

        MarkCommand command = new MarkCommand("mark 1");

        command.execute(tasks, new FakeUi(), new StubStorage());

        assertTrue(tasks.get(0).getStatus());
    }

    @Test
    public void execute_invalidIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        MarkCommand command = new MarkCommand("mark 1");

        try {
            command.execute(tasks, new FakeUi(), new StubStorage());
            fail(); // should not reach here
        } catch (Exception e) {
            assertEquals("Invalid task number.", e.getMessage());
        }
    }
}

