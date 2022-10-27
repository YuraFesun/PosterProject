package exception;

public class ObjectNotFound extends RuntimeException {
    public ObjectNotFound() {
        super("Event is not found.");
    }
}
