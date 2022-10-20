package hw_9_UserExceptions;

public class ArraySizeException extends Exception {
    public ArraySizeException() {
    }

    public ArraySizeException(String message) {
        super(message);
    }
}
