package hw_9_UserExceptions;

public class ArrayDataException extends Exception {
    public ArrayDataException() {
    }

    public ArrayDataException(String message) {
        super(message);
    }

    public ArrayDataException(String message, Exception e) {
        super(message, e);
    }
}
