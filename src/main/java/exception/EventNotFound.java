package exception;

public class EventNotFound extends RuntimeException {
    public EventNotFound() {
        super("Event not found pr passed");
    }
}
