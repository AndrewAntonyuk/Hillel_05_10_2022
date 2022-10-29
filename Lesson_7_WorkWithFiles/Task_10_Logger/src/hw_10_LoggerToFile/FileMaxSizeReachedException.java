package hw_10_LoggerToFile;

public class FileMaxSizeReachedException extends Exception{
    public FileMaxSizeReachedException() {
    }

    public FileMaxSizeReachedException(String message) {
        super(message);
    }

    public FileMaxSizeReachedException(String message, Throwable cause) {
        super(message, cause);
    }
}
