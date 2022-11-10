package phonebook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract public class Phonebook<T> {
    private final List<T> internalList = new ArrayList<>();

    abstract public void add(T newRecord);

    abstract public T find(String key);

    abstract public List<T> findAll(String key);

    @Override
    public String toString() {
        return internalList.toString();
    }
}
