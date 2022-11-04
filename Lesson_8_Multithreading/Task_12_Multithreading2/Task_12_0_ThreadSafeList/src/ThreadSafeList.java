import java.util.ArrayList;

public class ThreadSafeList<T> {
    private ArrayList<T> collection;

    //region Constructors
    public ThreadSafeList() {
        this.collection = new ArrayList<T>();
    }

    public ThreadSafeList(ArrayList<T> collection) {
        this.collection = collection;
    }
    //endregion

    public synchronized void add(T t) {
        collection.add(t);
    }

    public synchronized void remove(int i) throws IndexOutOfBoundsException {
        collection.remove(i);
    }

    public synchronized void remove(T i) throws IndexOutOfBoundsException {
        collection.remove(i);
    }

    public synchronized T get(int i) throws IndexOutOfBoundsException {
        return collection.get(i);
    }

    @Override
    public String toString() {
        return "ThreadSafeList{" +
                "list=" + collection.toString() +
                '}';
    }
}
