import java.util.Arrays;

public class ThreadSafeList<T> {
    private T[] list;

    //region Constructors
    public ThreadSafeList() {
        this.list = (T[]) new Object[0];
    }

    public ThreadSafeList(T[] list) {
        this.list = list;
    }
    //endregion

    public synchronized void add(T t) {
        list = Arrays.copyOf(list, list.length + 1);
        list[list.length - 1] = t;
    }

    public synchronized void remove(int i) throws IndexOutOfBoundsException {
        if (i >= 0 && i < list.length) {
            T[] newList = (T[]) new Object[list.length - 1];
            System.arraycopy(list, 0, newList, 0, i);
            System.arraycopy(list, i + 1, newList, i, list.length - i - 1);
            list = newList;
        } else {
            throw new IndexOutOfBoundsException("Index " + i + " is beyond the legal range!");
        }
    }

    public synchronized T get(int i) throws IndexOutOfBoundsException {
        if (i < list.length && i >= 0) {
            return list[i];
        } else {
            throw new IndexOutOfBoundsException("Index " + i + " is beyond the legal range!");
        }
    }

    @Override
    public String toString() {
        return "ThreadSafeList{" +
                "list=" + Arrays.toString(list) +
                '}';
    }
}
