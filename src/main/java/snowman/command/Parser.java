package snowman.command;

import snowman.SnowmanException;

/**
 * Parser: Converts user input into a Command object.
 */
public class Parser {
    /** Parse the raw user input and return the corresponding Command */
    public static Command parse(String input) throws SnowmanException {
        input = input.trim();

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
