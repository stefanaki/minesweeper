package medialab.minesweeper.exception;

public class InvalidValueException extends Exception {
    public InvalidValueException() {
        super("Invalid Game Scenario Configuration Values");
    }

    public InvalidValueException(String message) {
        super(message);
    }

    public InvalidValueException(Throwable cause) {
        super(cause);
    }
}
