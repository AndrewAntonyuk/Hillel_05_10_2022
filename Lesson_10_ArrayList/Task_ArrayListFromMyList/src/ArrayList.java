import java.util.Arrays;

public class ArrayList implements MyList {
    private final int stepSize = 5;
    private int currentPosition = 0;
    private Object[] internalArray = new Object[stepSize];

    //region Constructors
    public ArrayList() {
    }

    public ArrayList(Object[] internalArray) {
        this.internalArray = internalArray;
    }
    //endregion

    @Override
    public boolean add(Object e) {
        if (currentPosition > internalArray.length - 1) {
            Object[] newArray = new Object[internalArray.length + stepSize];
            System.arraycopy(internalArray, 0, newArray, 0, internalArray.length);
            newArray[currentPosition++] = e;
            internalArray = newArray;
        } else {
            internalArray[currentPosition++] = e;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < currentPosition; i++) {
            if (internalArray[i].equals(o)) {
                System.arraycopy(internalArray, i + 1, internalArray, i, currentPosition - i - 1);
                internalArray[--currentPosition] = null;
                return true;
            }
        }

        return false;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(internalArray, currentPosition);
    }

    @Override
    public boolean isEmpty() {
        return currentPosition == 0;
    }

    @Override
    public int size() {
        return currentPosition;
    }

    @Override
    public void clear() {
        internalArray = new Object[stepSize];
        currentPosition = 0;
    }

    @Override
    public Object get(int index) {
        if (index < currentPosition) {
            return internalArray[index];
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " greater than " + currentPosition);
        }
    }

    @Override
    public String toString() {
        String returnString = "Array = ";

        for (Object o : internalArray) {
            returnString += o;
            returnString += " ";
        }

        return returnString;
    }
}
