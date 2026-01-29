package snowman.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import snowman.SnowmanException;
import snowman.storage.Storage;
import snowman.storage.StubStorage;
import snowman.task.TaskList;
import snowman.task.Task;
import snowman.task.Todo;
import snowman.ui.FakeUi;
import snowman.ui.Ui;

class UnmarkCommandTest {

    @Test
    void execute_unmarksTaskSuccessfully() throws Exception {
        // Arrange
        TaskList tasks = new TaskList();
        Task task = new Todo("read book");
        task.markAsDone(); // start as marked
        tasks.add(task);

        Ui ui = new Ui();
        Storage storage = new StubStorage();
        UnmarkCommand command = new UnmarkCommand("unmark 1");

        // Act
        command.execute(tasks, ui, storage);

        // Assert
        assertFalse(tasks.get(0).getStatus(),
                "Task should be unmarked after UnmarkCommand");
    }

    @Test
    void execute_invalidIndex_throwsException() {
        // Arrange
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new StubStorage();
        UnmarkCommand command = new UnmarkCommand("unmark 1");

        // Act + Assert
        SnowmanException e = assertThrows(
                SnowmanException.class,
                () -> command.execute(tasks, ui, storage)
        );

        assertEquals("Invalid task number.", e.getMessage());
    }
}
