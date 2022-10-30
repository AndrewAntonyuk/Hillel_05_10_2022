package hw_10_LoggerToFile;

public class FileMaxSizeReachedException extends Exception{
    public FileMaxSizeReachedException(String message) {
        super(message);
    }
}
