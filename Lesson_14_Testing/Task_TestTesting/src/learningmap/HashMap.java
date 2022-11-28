package learningmap;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class HashMap implements LearningMap {
    private final double loadFactor = 0.75;
    private int setSize = 5;
    private Bucket[] internalArray = new Bucket[setSize];
    private int currentSize = 0;

    @Override
    public void put(Integer key, String value) {
        isIllegalKey(key);

        int hash = getHash(key);

        if (internalArray[hash] == null) {
            internalArray[hash] = new Bucket();
        }

        internalArray[hash].add(new Node(key, value));

        currentSize++;

        if (((double) currentSize / (double) setSize) > loadFactor) {
            createNewArrayWithRehashing();
        }
    }

    @Override
    public String get(Integer key) {
        isIllegalKey(key);

        int hash = getHash(key);

        return (internalArray[hash].get(key) == null ? null : internalArray[hash].get(key).getValue());
    }

    @Override
    public String remove(Integer key) {
        isIllegalKey(key);

        int hash = getHash(key);

        if (internalArray[hash].get(key) == null) {
            return null;
        } else {
            currentSize--;
            return internalArray[hash].remove(internalArray[hash].get(key));
        }
    }

    @Override
    public int getSize() {
        return currentSize;
    }

    private void createNewArrayWithRehashing() {
        Bucket[] oldInternalArray = internalArray;

        setSize *= 2;
        internalArray = new Bucket[setSize];

        Arrays.stream(oldInternalArray)
                .filter(Objects::nonNull)
                .forEach(bucket -> bucket.getList()
                        .forEach(this::putInNewArray));
    }

    private void putInNewArray(Node node) {
        int hash = getHash(node.getKey());

        if (internalArray[hash] == null) {
            internalArray[hash] = new Bucket();
        }

        internalArray[hash].add(node);
    }

    private int getHash(Integer key) {
        return key % setSize;
    }

    private void isIllegalKey(Integer key) {
        if (key == null || key < 0) {
            throw new IllegalArgumentException("Key " + key + " is illegal");
        }
    }

    @Override
    public String toString() {
        final String[] returnValue = {""};

        Arrays.stream(internalArray)
                .filter(Objects::nonNull)
                .forEach(bucket -> bucket.getList()
                        .forEach(node -> {
                            returnValue[0] += "{" + node.getKey() + " : " + node.getValue() + "}";
                        }));

        return returnValue[0];
    }

    private static class Node {
        private Integer key;
        private String value;

        private Node(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        private Integer getKey() {
            return key;
        }

        private void setKey(Integer key) {
            this.key = key;
        }

        private String getValue() {
            return value;
        }

        private void setValue(String value) {
            this.value = value;
        }
    }

    private static class Bucket {
        private final List<Node> list = new LinkedList<>();

        private void add(Node node) {
            if (isExistAlready(node.getKey())) {
                throw new IllegalArgumentException("Element " + node.getKey() + " existed already");
            }
            list.add(node);
        }

        private String remove(Node node) {
            if (!isExistAlready(node.getKey())) {
                throw new NoSuchElementException("Element " + node.getKey() + " doesn't exist");
            }

            String value = node.getValue();

            list.remove(node);
            return value;
        }

        private Node get(Integer key) {
            return list.stream()
                    .filter(o -> o.getKey().equals(key))
                    .findFirst()
                    .orElse(null);
        }

        private boolean isExistAlready(Integer key) {
            return get(key) != null;
        }

        private List<Node> getList() {
            return list;
        }
    }
}

class HashMapTest {
    @Test
    public void testPut() {
        HashMap testHashMap = new HashMap();

        testHashMap.put(3200, "Kiev");
        testHashMap.put(3201, "Lviv");
        assertEquals("Kiev", testHashMap.get(3200));
    }

    @Test
    public void testPutTheSameKey() {
        HashMap testHashMap = new HashMap();

        try {
            testHashMap.put(3200, "Kiev");
            testHashMap.put(3200, "Kiev111");
            fail();
        } catch (RuntimeException ignored) {

        }
    }

    @Test
    public void testPutNullKey() {
        HashMap testHashMap = new HashMap();

        try {
            testHashMap.put(null, "Kiev");
            fail();
        } catch (RuntimeException ignored) {

        }
    }

    @Test
    public void testPutLessThanZeroKey() {
        HashMap testHashMap = new HashMap();

        try {
            testHashMap.put(-3200, "Kiev");
            fail();
        } catch (RuntimeException ignored) {

        }
    }

    @Test
    public void testGet() {
        HashMap testHashMap = new HashMap();

        testHashMap.put(3200, "Kiev");
        testHashMap.put(3201, "London");
        testHashMap.put(3202, "Paris");
        assertEquals("Kiev", testHashMap.get(3200));
    }

    @Test
    public void testGetIllegalKey() {
        HashMap testHashMap = new HashMap();

        testHashMap.put(3200, "Kiev");
        assertNull(testHashMap.get(10));
    }

    @Test
    public void testGetNullKey() {
        HashMap testHashMap = new HashMap();

        testHashMap.put(3200, "Kiev");
        try {
            assertNull(testHashMap.get(null));
            fail();
        } catch (RuntimeException ignored) {

        }
    }

    @Test
    public void testRemove() {
        HashMap testHashMap = new HashMap();

        testHashMap.put(3200, "Kiev");
        testHashMap.put(3201, "London");
        testHashMap.put(3202, "Paris");
        assertEquals("London", testHashMap.remove(3201));
    }

    @Test
    public void testRemoveIllegalKey() {
        HashMap testHashMap = new HashMap();

        testHashMap.put(3200, "Kiev");
        testHashMap.put(3201, "London");
        testHashMap.put(3202, "Paris");
        assertNull(testHashMap.remove(32));
    }

    @Test
    public void testRemoveNullKey() {
        HashMap testHashMap = new HashMap();

        try {
            testHashMap.remove(null);
            fail();
        } catch (RuntimeException ignored) {

        }
    }

    @Test
    public void testGetSize() {
        HashMap testHashMap = new HashMap();

        testHashMap.put(3200, "Kiev");
        testHashMap.put(3201, "London");
        testHashMap.put(3202, "Paris");
        assertEquals(3, testHashMap.getSize());
    }
}
