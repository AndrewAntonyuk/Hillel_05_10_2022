import valueCalculator.ValueCalculator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
        ValueCalculator calculator = new ValueCalculator();

        calculator.doCalc();
    }
}
