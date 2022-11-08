import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import valueCalculator.ValueCalculator;
import valueCalculatorwithFunction.ValueCalculatorWithFunc;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ValueCalculator.class.getSimpleName())
                .include(ValueCalculatorWithFunc.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
