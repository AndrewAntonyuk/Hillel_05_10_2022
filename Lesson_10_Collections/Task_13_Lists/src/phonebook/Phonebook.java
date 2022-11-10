package phonebook;

import java.util.ArrayList;
import java.util.List;

public class Phonebook<R> {
    private final List<Record> internalList = new ArrayList<>();

    public void add(Record newRecord) {
        internalList.add(newRecord);
    }

    public Record find(String key) {
        return internalList.stream()
                .filter(record -> key.equals(record.getName()))
                .findFirst()
                .orElse(null);
    }

    public List<Record> findAll(String key) {
        List<Record> newList = internalList.stream()
                .filter(record -> key.equals(record.getName()))
                .toList();

        if (newList.isEmpty()) {
            return null;
        } else {
            return newList;
        }
    }

    @Override
    public String toString() {
        return internalList.toString();
    }
}
