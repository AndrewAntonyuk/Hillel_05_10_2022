import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.function.Function;

public class ValueCalculator {
    private final int size = 2000000;
    private final int halfSize = size / 2;
    private Float[] baseArray = new Float[size];
    private CountDownLatch countDownLatch = new CountDownLatch(2);

    public void doCalc() {
        long start = System.currentTimeMillis();
        Float[] halfArray1 = new Float[halfSize];
        Float[] halfArray2 = new Float[halfSize];

        Arrays.setAll(baseArray, n -> baseArray[n] = 3.25f);
        System.arraycopy(baseArray, 0, halfArray1, 0, halfSize);
        System.arraycopy(baseArray, halfSize, halfArray2, 0, halfSize);

        startThread(halfArray1);
        startThread(halfArray2);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.arraycopy(halfArray1, 0, baseArray, 0, halfSize);
        System.arraycopy(halfArray2, 0, baseArray, halfSize, halfSize);

        System.out.println("Running time is: " + (System.currentTimeMillis() - start) + " ms");
    }

    private void startThread(Float[] inputArray){
        new Thread(()->{
            Arrays.setAll(inputArray, (i) -> inputArray[i] = (float) (inputArray[i]
                    * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float) i / 5)
                    * Math.cos(0.4f + (float) i / 2)));
            this.countDownLatch.countDown();
        }).start();
    }
}
