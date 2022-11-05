import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList testArray = new ArrayList();

        //region Test for add
        testArray.add(1);
        testArray.add(2);
        testArray.add(3);
        testArray.add(4);

        System.out.println("Result for .add(): " + testArray.toString());
        //endregion

        //region Test for remove
        testArray.remove(3);

        System.out.println("Result for .remove(): " + testArray.toString());
        //endregion

        //region Test for toArray
        System.out.println("Result for .toArray(): " + Arrays.toString(testArray.toArray()));
        //endregion

        //region Test for isEmpty
        System.out.println("Result for .isEmpty(): " + testArray.isEmpty());
        //endregion

        //region Test for size
        System.out.println("Result for .size(): " + testArray.size());
        System.out.println("Value array[.size() - 1]: " + testArray.get(testArray.size() - 1));
        //endregion

        //region Test for clear
        testArray.clear();
        System.out.println("Result for .clear(): " + testArray.toString());
        System.out.println("Result for .isEmpty(): " + testArray.isEmpty());
        //endregion

        //region Test for get
        testArray.add(25);
        System.out.println("Result for .get(): " + testArray.get(0));
        //endregion
    }
}
