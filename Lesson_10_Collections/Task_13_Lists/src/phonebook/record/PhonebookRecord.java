package phonebook.record;

import phonebook.Phonebook;

import java.util.ArrayList;
import java.util.List;

public class PhonebookRecord extends Phonebook<Record> {
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
}
