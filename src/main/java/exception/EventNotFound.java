package exception;

public class EventNotFound extends RuntimeException {
    public EventNotFound(String eventName) {
        super(eventName + " not found or passed");
    }
}
