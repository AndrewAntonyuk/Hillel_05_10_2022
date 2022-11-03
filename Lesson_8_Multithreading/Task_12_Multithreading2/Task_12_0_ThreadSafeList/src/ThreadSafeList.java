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
        if (i >= 0 && i < collection.size()) {
            collection.remove(i);
        } else {
            throw new IndexOutOfBoundsException("Index " + i + " is beyond the legal range!");
        }
    }

    public synchronized void remove(T i) throws IndexOutOfBoundsException {
        if (collection.contains(i)) {
            collection.remove(i);
        } else {
            throw new IndexOutOfBoundsException("Index " + i + " is beyond the legal range!");
        }
    }

    public synchronized T get(int i) throws IndexOutOfBoundsException {
        if (i < collection.size() && i >= 0) {
            return collection.get(i);
        } else {
            throw new IndexOutOfBoundsException("Index " + i + " is beyond the legal range!");
        }
    }

    @Override
    public String toString() {
        return "ThreadSafeList{" +
                "list=" + collection.toString() +
                '}';
    }
}
