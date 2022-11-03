import java.util.Random;
import java.util.concurrent.*;

public class PetrolStation {
    Semaphore semaphore = new Semaphore(3);
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

                Thread.sleep(sleepTime);
                synchronized (this) {
                    System.out.println("Fuel before refuel: " + amount);

                    if (amount - value >= 0.0f) {
                        amount -= value;
                    } else {
                        throw new IllegalArgumentException("Amount fuel for refuel (" + value
                                + ") can't be greater than the remainder (" + amount + ")");

                    }
                    System.out.println("Fuel after refuel: " + amount);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                semaphore.release();
            }
        };

        Thread thread = new Thread(runnable, ("Thread_For_Value_" + value));
        thread.start();
    }

    //region Getters/Setters
    public Float getAmount() {
        return amount;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }
    //endregion
}
