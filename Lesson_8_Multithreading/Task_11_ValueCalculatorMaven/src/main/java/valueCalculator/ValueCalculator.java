package valueCalculator;

import jdk.jfr.DataAmount;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 3)
@Measurement(iterations = 8)
public class ValueCalculator {
    private final int size = 1000000;
    private final int halfSize = size / 2;
    private final float[] baseArray = new float[size];

    @Benchmark
    public void doCalc() {
        long start = System.currentTimeMillis();
        float[] halfArray1 = new float[halfSize];
        float[] halfArray2 = new float[halfSize];
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<float[]>> futures = new ArrayList<>();

        Arrays.fill(baseArray, 3.25f);
        System.arraycopy(baseArray, 0, halfArray1, 0, halfSize);
        System.arraycopy(baseArray, halfSize, halfArray2, 0, halfSize);

        futures.add(executor.submit(changeValuesGetFutures(halfArray1)));
        futures.add(executor.submit(changeValuesGetFutures(halfArray2)));

        try {
            halfArray1 = futures.get(0).get();
            halfArray2 = futures.get(1).get();
            executor.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.arraycopy(halfArray1, 0, baseArray, 0, halfSize);
        System.arraycopy(halfArray2, 0, baseArray, halfSize, halfSize);

        System.out.printf("time execute program - %s milliseconds%n", System.currentTimeMillis() - start + " ms");
    }

    private Callable<float[]> changeValuesGetFutures(final float[] arr) {
        return () -> {
            float[] internalArray = new float[arr.length];
            for (int i = 0; i < arr.length; i++) {
                internalArray[i] = (float) (arr[i] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float) i / 5)
                        * Math.cos(0.4f + (float) i / 2));
            }
            return internalArray;
        };
    }
}