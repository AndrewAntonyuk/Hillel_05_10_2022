package learningmap;

public interface LearningMap {
    void put(Integer key, String value);

    String get(Integer key);

    String remove(Integer key);

    int getSize();
}
