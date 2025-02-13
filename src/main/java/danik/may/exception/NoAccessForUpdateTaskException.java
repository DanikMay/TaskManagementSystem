package danik.may.exception;

public class NoAccessForUpdateTaskException extends RuntimeException{
    public NoAccessForUpdateTaskException(String message) {
        super(message);
    }

    public NoAccessForUpdateTaskException() {
    }
}
