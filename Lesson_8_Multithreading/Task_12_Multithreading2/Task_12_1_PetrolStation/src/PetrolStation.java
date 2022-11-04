import java.util.Random;
import java.util.concurrent.*;

public class PetrolStation {
    Semaphore semaphore = new Semaphore(3);
    private Float amount;
    private ExecutorService executor = Executors.newFixedThreadPool(3);

    //region Constructors
    public PetrolStation() {
        amount = 10000.0f;
    }

    public PetrolStation(Float amount) {
        this.amount = amount;
    }
    //endregion

    public void doRefuel(Float value) throws IllegalArgumentException {
        Runnable runnable = () -> {
            int sleepTime = (new Random().nextInt(10000 - 3000) + 3000);

            try {
                semaphore.acquire();

                Thread.sleep(sleepTime);
                synchronized (this) {
                    System.out.println("Fuel before refuel: " + amount);
                    doSubtraction(value);
                    System.out.println("Fuel after refuel: " + amount);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
            }
        };

        executor.execute(runnable);
    }

    private void doSubtraction(final float valueSubtract) {
        if (amount - valueSubtract >= 0.0f) {
            amount -= valueSubtract;
        } else {
            throw new IllegalArgumentException("Amount fuel for refuel (" + valueSubtract
                    + ") can't be greater than the remainder (" + amount + ")");
        }
    }

    //region Getters/Setters
    public Float getAmount() {
        return amount;
    }

    public ExecutorService getExecutor() {
        return executor;
    }
    //endregion
}
