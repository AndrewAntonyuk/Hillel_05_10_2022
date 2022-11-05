public interface MyList {
    boolean add(Object e);
    boolean remove(Object o);
    Object[] toArray();
    boolean isEmpty();
    int size();
    void clear();
    Object get(int index);
}
