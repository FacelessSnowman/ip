package snowman;

/**
 * Represents exceptions specific to the Snowman application.
 * Thrown when an error occurs during command parsing, execution, or other operations.
 */
public class SnowmanException extends Exception {

    /**
     * Constructs a SnowmanException with the specified error message.
     *
     * @param message Description of the exception.
     */
    public SnowmanException(String message) {
        super(message);
    }
}