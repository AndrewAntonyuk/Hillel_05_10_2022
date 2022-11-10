import java.util.*;

public class TestList<T> {
    public int countOccurrence(List<String> inputList, String s) {
        return Collections.frequency(inputList, s);
    }

    public List<Integer> toList(Integer[] sourceArray) {
        return new ArrayList<>(List.of(sourceArray));
    }

    public Set<T> findUnique(List<T> sourceList) {
        return new HashSet<>(sourceList);
    }

    public void calcOccurrence(List<String> sourceList) {
        for (String s : new HashSet<>(sourceList)) {
            System.out.println(s + ": " + Collections.frequency(sourceList, s));
        }
    }

    public HashMap<String, Integer> findOccurrence(List<String> sourceList) {
        HashMap<String, Integer> destinationHashMap = new HashMap<>();

        for (String s : new HashSet<>(sourceList)) {
            destinationHashMap.put(s, Collections.frequency(sourceList, s));
        }

        return destinationHashMap;
    }
}
