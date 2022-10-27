package exception;

public class NotEnoughTickets extends RuntimeException{
    public NotEnoughTickets(String eventName) {
        super("Not enough tickets on " + eventName);
    }
}
