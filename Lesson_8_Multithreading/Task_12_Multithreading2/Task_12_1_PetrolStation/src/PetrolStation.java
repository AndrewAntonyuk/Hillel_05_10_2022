import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class PetrolStation {
    private ExecutorService executor = Executors.newFixedThreadPool(3);
    //final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
    //ExecutorService executor = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, queue);
    private Float amount;

    public PetrolStation(Float amount) {
        this.amount = amount;
    }

    public synchronized void doRefuel(Float value) {
        Runnable runnable;
        runnable = () -> {
            int sleepTime = (new Random().nextInt(10000 - 3000) + 3000);
            System.out.println("Starting task with set: " + value + " and sleep: " + sleepTime);

            try {
                System.out.println("In process...");
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Finished task with set: " + value + " and sleep: " + sleepTime);
        };

        //CompletableFuture<Void> future = CompletableFuture.runAsync(runnable, executor);
        //queue.add(runnable);
        executor.execute(runnable);

       // executor.execute(runnable);
        //System.out.println("After execute set:" + value);

    }

    @Override
    protected void finalize() throws Throwable {
        executor.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!executor.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            executor.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
