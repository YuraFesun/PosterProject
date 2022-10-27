package exception;

public class EmptyStorage extends  RuntimeException {
    public EmptyStorage() {
        super("There are no events in the storage");
    }
}


