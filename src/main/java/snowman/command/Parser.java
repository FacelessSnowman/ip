package snowman.command;

import snowman.SnowmanException;

/**
 * Converts raw user input into the corresponding Command object in the Snowman application.
 * Determines which type of command to create based on the input string.
 */
public class Parser {
    /**
     * Parses the raw user input and returns the corresponding Command object.
     * Recognizes commands such as list, bye, mark, unmark, todo, deadline, event, and delete.
     *
     * @param input Raw command input from the user.
     * @return Command object corresponding to the user input.
     * @throws SnowmanException If the input does not match any known command.
     */
    public static Command parse(String input) throws SnowmanException {
        input = input.trim();

        assert input != null : "Input string should never be null";
        assert !input.isEmpty() : "Input string should not be empty after trimming";
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("todo")) {
            return new TodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new EventCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else {
            throw new SnowmanException("Wait...what command are you typing?");
        }
    }
}
