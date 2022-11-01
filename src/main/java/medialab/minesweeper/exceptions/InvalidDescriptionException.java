package medialab.minesweeper.exceptions;

public class InvalidDescriptionException extends Exception {
    public InvalidDescriptionException() {
        super("Invalid Game Scenario Description");
    }

    public InvalidDescriptionException(String message) {
        super(message);
    }

    public InvalidDescriptionException(Throwable cause) {
        super(cause);
    }
}
