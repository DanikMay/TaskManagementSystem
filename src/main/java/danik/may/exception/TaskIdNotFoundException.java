package danik.may.exception;

public class TaskIdNotFoundException extends RuntimeException{
    public TaskIdNotFoundException(String message) {
        super(message);
    }

    public TaskIdNotFoundException() {
    }
}
