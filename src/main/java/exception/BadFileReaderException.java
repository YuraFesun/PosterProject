package exception;

public class BadFileReaderException extends RuntimeException {
    public BadFileReaderException() {
        super("The given file was not found or it is corrupted");
    }
}
