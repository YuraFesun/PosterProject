package exception;

public class NoEventsForThisDate extends RuntimeException {
    public NoEventsForThisDate() {
        super("No events found for this date");
    }
}
