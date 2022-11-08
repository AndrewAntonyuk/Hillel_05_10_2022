package valueCalculatorwithFunction;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.*;

import java.util.function.Function;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 3)
@Measurement(iterations = 8)
public class ValueCalculatorWithFunc {
    private final int size = 1000000;
    private final int halfSize = size / 2;
    private final Float[] baseArray = new Float[size];

    @Benchmark
    public void doCalc() {
        long start = System.currentTimeMillis();
        Function<Float[], Runnable> startThreadArray;
        Float[] halfArray1 = new Float[halfSize];
        Float[] halfArray2 = new Float[halfSize];
        Thread threadHalfArray1;
        Thread threadHalfArray2;
        CountDownLatch countLatch = new CountDownLatch(2);

        startThreadArray = (inputArray) -> {
            return () -> {
                Arrays.setAll(inputArray, (i) -> inputArray[i] = (float) (inputArray[i]
                        * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float) i / 5)
                        * Math.cos(0.4f + (float) i / 2)));
                countLatch.countDown();
            };
        };

        Arrays.setAll(baseArray, n -> baseArray[n] = 3.25f);
        System.arraycopy(baseArray, 0, halfArray1, 0, halfSize);
        System.arraycopy(baseArray, halfSize, halfArray2, 0, halfSize);

        threadHalfArray1 = new Thread(startThreadArray.apply(halfArray1));
        threadHalfArray2 = new Thread(startThreadArray.apply(halfArray2));

        threadHalfArray1.start();
        threadHalfArray2.start();

        try {
            countLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.arraycopy(halfArray1, 0, baseArray, 0, halfSize);
        System.arraycopy(halfArray2, 0, baseArray, halfSize, halfSize);

        System.out.println("Running time is: " + (System.currentTimeMillis() - start) + " ms");
    }
}