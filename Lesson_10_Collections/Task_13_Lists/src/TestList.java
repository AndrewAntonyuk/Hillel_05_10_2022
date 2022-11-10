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
            System.out.println(s + ": " + sourceList.stream().filter(o -> o.equals(s)).count());
        }
    }

    public List<String> findOccurrence(List<String> sourceList) {
        List<String> destinationList = new ArrayList<>();

        for (String s : new HashSet<>(sourceList)) {
            destinationList.add("\n{name: \"" + s + "\", occurrence: " + sourceList.stream().filter(o -> o.equals(s)).count() + "}");
        }

        return destinationList;
    }
}
