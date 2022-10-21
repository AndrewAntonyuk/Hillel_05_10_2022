import hw_9_UserExceptions.ArrayDataException;
import hw_9_UserExceptions.ArraySizeException;

public class Main {
    public static void main(String[] args) {
        ArrayValueCalculator arrayValueCalculator = new ArrayValueCalculator();
        int result = 0;
        String[][] legalArray = {{"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"-9", "10", "11", "-12"},
                {"13", "14", "+17", "-1"}};
        String[][] illegalSizeArray = {{"1", "2", "3", "4", "88"},
                {"5", "6", "7", "8"},
                {"-9", "10", "11", "-12"},
                {"13", "14", "+17", "-1"}};
        String[][] illegalDataArray = {{"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"-9", "bbb", "11", "-12"},
                {"13", "14", "+17", "-1"}};

        //region Legal array
        try {
            result = arrayValueCalculator.doCalc(legalArray);
            System.out.println("Sum is: " + result);
        } catch (ArrayDataException | ArraySizeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Code from finally!");
        }
        //endregion

        //region Array with illegal size
        try {
            result = arrayValueCalculator.doCalc(illegalSizeArray);
            System.out.println("Sum is: " + result);
        } catch (ArrayDataException | ArraySizeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Code from finally!");
        }
        //endregion

        //region Array with illegal data
        try {
            result = arrayValueCalculator.doCalc(illegalDataArray);
            System.out.println("Sum is: " + result);
        } catch (ArrayDataException | ArraySizeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Code from finally!");
        }
        //endregion

        //region Other illegal function
        try {
            divideByZero();
            result = arrayValueCalculator.doCalc(illegalDataArray);
            System.out.println("Sum is: " + result);
        } catch (ArrayDataException | ArraySizeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Code from finally!");
        }
        //endregion
    }

    public static void divideByZero() {
        int x = 100 / 0;
    }
}
