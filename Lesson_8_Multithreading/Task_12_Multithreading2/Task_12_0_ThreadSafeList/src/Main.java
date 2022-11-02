import java.util.Arrays;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        ThreadSafeList<Float> list = new ThreadSafeList<>();
        Thread thread1;
        Thread thread2;
        Thread thread3;

        Function<ThreadSafeList<Float>, Runnable> functionForThreads1 = (safeList)->{
            return () ->  {safeList.add(25.4f); safeList.add(21.4f);safeList.add(20.4f); safeList.remove(1);
            };
        };

        Function<ThreadSafeList<Float>, Runnable> functionForThreads2 = (safeList)->{
            return () ->  {safeList.add(0.4f); safeList.add(0.8f);safeList.add(10.7f); safeList.remove(3);
            };
        };

        Function<ThreadSafeList<Float>, Runnable> functionForThreads3 = (safeList)->{
            return () ->  {safeList.add(45.4f); safeList.add(48.4f);safeList.add(96.4f); safeList.remove(4);
            };
        };

        thread1 = new Thread(functionForThreads1.apply(list));
        thread2 = new Thread(functionForThreads2.apply(list));
        thread3 = new Thread(functionForThreads3.apply(list));

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(list.toString());
    }
}
