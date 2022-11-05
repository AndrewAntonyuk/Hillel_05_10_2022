import java.util.Random;
import java.util.concurrent.*;

public class PetrolStation {
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final Semaphore semaphore = new Semaphore(3);
    private Float amount;

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
                boolean isRefuel = false;

                synchronized (PetrolStation.this) {
                    if (amount - value >= 0.0f) {
                        System.out.println("Fuel before refuel: " + amount);
                        amount -= value;
                        System.out.println("Fuel after refuel: " + amount);
                        isRefuel = true;
                    } else {
                        throw new IllegalArgumentException("Amount fuel for refuel (" + value
                                + ") can't be greater than the remainder (" + amount + ")");
                    }
                }

                if (isRefuel) {
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
            }
        };

        executor.execute(runnable);
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
