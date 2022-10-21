import hw_9_UserExceptions.ArrayDataException;
import hw_9_UserExceptions.ArraySizeException;

public class ArrayValueCalculator {
    private final int size = 4;

    /**
     * This method calculates sum of elements of the input String array
     *
     * @param inputArray - String array with size 4x4
     * @return int sum
     * @throws ArrayDataException - occurred when can't convert String to int
     * @throws ArraySizeException - occurred when size of the input array isn't with size 4x4
     */
    public int doCalc(String[][] inputArray) throws ArrayDataException, ArraySizeException {
        int sum = 0;
        int i = 0;
        int j = 0;

        if (isIllegalSize(inputArray)) {
            throw new ArraySizeException("Array has illegal size! Legal size: " + size + "x" + size);
        }

        try {
            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    sum += Integer.parseInt(inputArray[i][j]);
                }
            }
        } catch (NumberFormatException e) {
            throw new ArrayDataException("Illegal digit at the position [" + i + "][" + j + "]", e);
        }

        return sum;
    }

    private boolean isIllegalSize(String[][] s) {
        if (s.length != size) return true;

        for (String[] array : s) {
            if (array.length != size) return true;
        }

        return false;
    }
}
