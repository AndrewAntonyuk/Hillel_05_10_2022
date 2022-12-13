import fruits.Apple;
import fruits.Fruit;
import fruits.Orange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main<E> {
    public static void main(String[] args) {
        //region Data for tests
        Box<Orange> boxWithOranges = new Box<>();
        Box<Apple> boxWithApples = new Box<>();
        Box<Apple> anotherBoxWithApples = new Box<>();
        Main<Integer> testToListInteger = new Main<>();
        Main<Double> testToListDouble = new Main<>();
        Main<String> testToListString = new Main<>();
        Integer[] testArrayInteger = new Integer[]{5, 78, -98, 16};
        Double[] testArrayDouble = new Double[]{21.25, -758.85, 198.0};
        String[] testArrayString = new String[]{"Java", "C#", "C++", "COBOL"};
        //endregion

        //region Test of toList() with Integer
        System.out.println("===================================================");
        System.out.println("Test of toList() with Integer ");

        System.out.println("Array before toList(): " + Arrays.toString(testArrayInteger));
        System.out.println("After use toList(): " + testToListInteger.toList(testArrayInteger));
        System.out.println("===================================================");
        //endregion

        //region Test of toList() with Double
        System.out.println("===================================================");
        System.out.println("Test of toList() with Double ");

        System.out.println("Array before toList(): " + Arrays.toString(testArrayDouble));
        System.out.println("After use toList(): " + testToListDouble.toList(testArrayDouble));
        System.out.println("===================================================");
        //endregion

        //region Test of toList() with String
        System.out.println("===================================================");
        System.out.println("Test of toList() with String ");

        System.out.println("Array before toList(): " + Arrays.toString(testArrayString));
        System.out.println("After use toList(): " + testToListString.toList(testArrayString));
        System.out.println("===================================================");
        //endregion

        //region Test of add() for one fruit
        System.out.println("===================================================");
        System.out.println("Test of add() for one fruit");

        boxWithOranges.add(new Orange());
        boxWithOranges.add(new Orange());
        boxWithOranges.add(new Orange());
        boxWithOranges.add(new Orange());
        boxWithOranges.add(new Orange());
        boxWithOranges.add(new Orange());
        boxWithOranges.add(new Orange());

        boxWithApples.add(new Apple());
        boxWithApples.add(new Apple());
        boxWithApples.add(new Apple());
        boxWithApples.add(new Apple());
        boxWithApples.add(new Apple());
        boxWithApples.add(new Apple());
        boxWithApples.add(new Apple());
        boxWithApples.add(new Apple());
        boxWithApples.add(new Apple());
        boxWithApples.add(new Apple());

        anotherBoxWithApples.add(new Apple());
        anotherBoxWithApples.add(new Apple());
        anotherBoxWithApples.add(new Apple());

        System.out.println("Quantity of fruits in boxes after add: ");
        System.out.println("In the first box with apples: " + boxWithApples.getInternalList().size());
        System.out.println("In the second box with apples: " + anotherBoxWithApples.getInternalList().size());
        System.out.println("In the box with oranges: " + boxWithOranges.getInternalList().size());
        System.out.println("===================================================");
        //endregion

        //region Test of add() some fruits
        System.out.println("===================================================");
        System.out.println("Test of add() some fruits");

        boxWithOranges.add(List.of(new Orange[]{new Orange(), new Orange(), new Orange()}));
        boxWithApples.add(List.of(new Apple[]{new Apple(), new Apple(), new Apple(), new Apple(), new Apple()}));
        anotherBoxWithApples.add(List.of(new Apple[]{new Apple(), new Apple(), new Apple()}));

        System.out.println("Quantity of fruits in boxes after add: ");
        System.out.println("In the first box with apples: " + boxWithApples.getInternalList().size());
        System.out.println("In the second box with apples: " + anotherBoxWithApples.getInternalList().size());
        System.out.println("In the box with oranges: " + boxWithOranges.getInternalList().size());
        System.out.println("===================================================");
        //endregion

        //region Test of getWeight()
        System.out.println("===================================================");
        System.out.println("Test of getWeight()");

        System.out.println("Weight in boxes: ");
        System.out.println("In the first box with apples: " + boxWithApples.getWeight());
        System.out.println("In the second box with apples: " + anotherBoxWithApples.getWeight());
        System.out.println("In the box with oranges: " + boxWithOranges.getWeight());
        System.out.println("===================================================");
        //endregion

        //region Test of compare()
        System.out.println("===================================================");
        System.out.println("Test of compare()");

        System.out.println("Boxes with apples are equal: " + boxWithApples.compare(anotherBoxWithApples));
        System.out.println("Boxes with apples and oranges are equal: " + boxWithApples.compare(boxWithOranges));
        System.out.println("===================================================");
        //endregion

        //region Test of merge()
        System.out.println("===================================================");
        System.out.println("Test of merge()");

        boxWithApples.merge(anotherBoxWithApples);

        System.out.println("Weight of first box with apples after merge : " + boxWithApples.getWeight());
        System.out.println("Weight of second box with apples after merge : " + anotherBoxWithApples.getWeight());
        System.out.println("===================================================");
        //endregion
    }

    public List<E> toList(E[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
