import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class FileNavigator {
    private final HashMap<String, List<FileData>> internalHashMap = new HashMap<String, List<FileData>>();

    public void add(String key, FileData value) throws IllegalArgumentException {
        itConsistentData(key, value.getFilePath());
        internalHashMap.computeIfAbsent(key, o -> new ArrayList<FileData>()).add(value);
    }

    private void itConsistentData(String setValue, String actValue) {
        if (!setValue.equals(actValue)) {
            throw new IllegalArgumentException("Set path " + setValue + " and actual path " + actValue + " are different!");
        }
    }

    public List<FileData> find(String key) {
        return internalHashMap.get(key);
    }

    public List<FileData> filterBySize(int maxSize) {
        return internalHashMap.values()
                .stream()
                .flatMap(e -> e.stream()
                        .filter(o -> o.getFileSize() < maxSize))
                .collect(Collectors.toList());
    }

    public void remove(String key) {
        internalHashMap.remove(key);
    }

    public List<FileData> sortBySize() {
        List<FileData> sortedList = new ArrayList<>(internalHashMap.values()
                .stream()
                .flatMap(Collection::stream)
                .toList());

        Collections.sort(sortedList);

        return sortedList;
    }

    @Override
    public String toString() {
        return internalHashMap.toString();
    }
}
