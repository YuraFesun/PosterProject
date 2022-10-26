package exception;

public class NotEnoughTickets extends RuntimeException{
    public NotEnoughTickets() {
        super("Not enough tickets on this event");
    }
}
