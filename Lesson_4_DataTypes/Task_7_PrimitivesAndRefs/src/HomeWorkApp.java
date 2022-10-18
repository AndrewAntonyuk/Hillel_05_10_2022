public class HomeWorkApp {
    public static void main(String[] args) {
        HomeWorkApp hw = new HomeWorkApp();
        int valPosNeg = 50;
        int sumAdd1 = 10;
        int sumAdd2 = 10;
        int valNeg = -10;
        int valYear = 1992;

        hw.printThreeWords();
        hw.checkSumSign();
        hw.printColor();
        hw.compareNumbers();
        System.out.println("Is sum of " + sumAdd1 + " and " + sumAdd2 + " in range 10...20? - " + hw.sumInRange10_20(sumAdd1, sumAdd2));
        hw.determPositiveNegative(valPosNeg);
        System.out.println("Is value " + valNeg + " negative? - " + hw.isNegative(valNeg));
        hw.printNTimes("Hillel", 5);
        System.out.println("Is year " + valYear + " leap? - " + hw.isLeapYear(valYear));
    }

    public void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    public void checkSumSign() {
        int a = 45;
        int b = -45;
        int sum = a + b;

        if (sum >= 0) {
            System.out.println("Sum is positive");
        } else {
            System.out.println("Sum is negative");
        }
    }

    public void printColor() {
        int value = 101;

        if (value <= 0) {
            System.out.println("Red");
        } else if (value > 0 & value <= 100) {
            System.out.println("Yellow");
        } else {
            System.out.println("Green");
        }
    }

    public void compareNumbers() {
        int a = 41;
        int b = 40;

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    public boolean sumInRange10_20(int val1, int val2) {
        int sum = val1 + val2;

        return (sum >= 10 & sum <= 20);
    }

    public void determPositiveNegative(int val) {
        if (val >= 0) {
            System.out.println("Value is positive");
        } else {
            System.out.println("Value is negative");
        }
    }

    public boolean isNegative(int value) {
        return value < 0;
    }

    public void printNTimes(String str, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + ". " + str);
        }
    }

    public boolean isLeapYear(int year) {
        return year > 0 & (year % 4) == 0 & ((year % 100) != 0 | (year % 400) == 0);
    }
}
