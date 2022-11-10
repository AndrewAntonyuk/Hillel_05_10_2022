package phonebook.recordextend;

import phonebook.Phonebook;

import java.util.ArrayList;
import java.util.List;

public class PhonebookRecordExtend extends Phonebook<RecordExtend> {
    private final List<RecordExtend> internalList = new ArrayList<>();

    public void add(RecordExtend newRecord) {
        internalList.add(newRecord);
    }

    public RecordExtend find(String key) {
        return internalList.stream()
                .filter(record -> key.equals(record.getName()))
                .findFirst()
                .orElse(null);
    }

    public List<RecordExtend> findAll(String key) {
        List<RecordExtend> newList = internalList.stream()
                .filter(record -> key.equals(record.getName()))
                .toList();

        if (newList.isEmpty()) {
            return null;
        } else {
            return newList;
        }
    }
}
