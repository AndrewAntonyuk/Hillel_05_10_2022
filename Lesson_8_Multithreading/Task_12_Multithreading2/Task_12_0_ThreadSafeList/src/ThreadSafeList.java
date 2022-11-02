import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

public class ThreadSafeList<T> {
    private T[] list;

    public ThreadSafeList() {
        this.list = (T[]) new Object[0];
    }

    public ThreadSafeList(T[] list) {
        this.list = list;
    }

    public synchronized void add(T t) {
        list = Arrays.copyOf(list, list.length + 1);
        list[list.length - 1] = t;
    }

    public synchronized void remove(int i) {
        if (i >= 0 && i < list.length) {
            T[] newList = (T[]) new Object[list.length - 1];
            System.arraycopy(list, 0, newList, 0, i);
            System.arraycopy(list, i + 1, newList, i, list.length - i - 1);
            list = newList;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public synchronized T get(int i) {
        if (i < list.length && i >= 0) {
            return list[i];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        return "ThreadSafeList{" +
                "list=" + Arrays.toString(list) +
                '}';
    }
}
