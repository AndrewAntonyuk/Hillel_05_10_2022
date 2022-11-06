public interface MyLinkedList<T>{
    public boolean addFirst(T t);
    public boolean isContain(T t);
    public int size();
    public boolean add(T t);
    public boolean remove(Object o);
    public void clear();
    public T get(int i);
    public boolean set(int i, T t);
    public Object[] toArray();
    public  boolean isEmpty();
    public  LinkedList getFirst();
    public  LinkedList getLast();
}
