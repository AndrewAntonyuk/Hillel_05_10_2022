public interface MyLinkedList<T> {
    public void addAtBegin(T t);

    public void addAtEnd(T t);

    public T removeFromBegin();

    public T removeFromEnd();

    public T remove(int i);

    public T remove(T t);

    public void clear();

    public T get(int i);

    public T set(int i, T t);

    public boolean isEmpty();

    public T getFirst();

    public T getLast();

    public int getSize();
}
