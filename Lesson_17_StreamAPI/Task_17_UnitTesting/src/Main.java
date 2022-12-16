import testresult.TestResultParser;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //region Test for tasks 10-13
        System.out.println("==============================");
        System.out.println("Test for parser with String (10-13)");
        System.out.println("==============================");
        System.out.println(TestResultParser.parse("logs/TEST-junit-jupiter.xml"));
        System.out.println("==============================");
        System.out.println("Test for parser with File (10-13)");
        System.out.println("==============================");
        System.out.println(TestResultParser.parse(new File("logs/TEST-junit-jupiter.xml")));
        System.out.println("==============================");
        System.out.println("Test for parser with File (10-13)");
        System.out.println("==============================");
        Path path = Path.of("logs/TEST-junit-jupiter.xml");
        System.out.println(TestResultParser.parse(path));
        System.out.println("==============================");
        //endregion

        //region Test for tasks 14-15
        Main main = new Main();
        int[] testArray = new int[]{4, 0, 0, 7, 0, 8, 5};
        int[] testArray1 = new int[]{4, 1, 1, 1, 4, 2, 1, 4};

        System.out.println("==============================");
        System.out.println("Test for get array after last 4 in input array (14)");
        System.out.println("==============================");
        System.out.println("Input array: " + Arrays.toString(testArray));
        System.out.println("Out array: " + Arrays.toString(main.getAllAfterLast4(testArray)));
        System.out.println("==============================");

        System.out.println("==============================");
        System.out.println("Test for check if input array contain only 4 and 1 numbers (15)");
        System.out.println("==============================");
        System.out.println("Input array: " + Arrays.toString(testArray1));
        System.out.println("Contain only 1 and 4?: " + main.isContainOnly1And4(testArray1));
        System.out.println("==============================");
        //endregion
    }

    public int[] getAllAfterLast4(final int[] inputArray) {
        for (int i = inputArray.length - 1; i >= 0; i--) {
            if (inputArray[i] == 4) {
                return Arrays.copyOfRange(inputArray, i + 1, inputArray.length);
            }
        }
        throw new RuntimeException("Input array should have least one 4");
    }

    public boolean isContainOnly1And4(final int[] inputArray) {
        boolean containPattern = false;

        for (int i = inputArray.length - 1; i > 0; i--) {
            if (inputArray[i] != 4 && inputArray[i] != 1) {
                return false;
            }

            if ((inputArray[i] == 4 && inputArray[i - 1] == 1) || (inputArray[i] == 1 && inputArray[i - 1] == 4)) {
                containPattern = true;
            }
        }

        return containPattern;
    }
}
