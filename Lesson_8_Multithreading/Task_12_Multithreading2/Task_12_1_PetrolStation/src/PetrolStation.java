import java.util.Random;
import java.util.concurrent.*;

public class PetrolStation {
    private final ExecutorService executor = Executors.newFixedThreadPool(3);
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
        Runnable runnable;

        runnable = () -> {
            int sleepTime = (new Random().nextInt(10000 - 3000) + 3000);

            try {
                Thread.sleep(sleepTime);

                synchronized (this) {
                    if (amount - value >= 0.0f) {
                        amount -= value;
                    } else {
                        throw new IllegalArgumentException("Amount fuel for refuel (" + value
                                + ") can't be greater than the remainder (" + amount + ")");
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
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
