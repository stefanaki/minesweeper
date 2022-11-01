package medialab.minesweeper.exceptions;

public class InvalidValueException extends Exception {
    InvalidValueException() {
        super("Invalid Game Scenario Configuration Values");
    }

    public InvalidValueException(String message) {
        super(message);
    }

    public InvalidValueException(Throwable cause) {
        super(cause);
    }
}
