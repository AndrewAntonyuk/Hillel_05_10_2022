package coffee.order;

import java.util.LinkedList;
import java.util.Queue;

public class CoffeeOrderBoard {
    private final Queue<Order> internalQueue = new LinkedList<Order>();
    private Integer currentNumber = 1;

    public void add(String name) {
        internalQueue.offer(new Order((currentNumber++), name));
    }

    public void deliver() {
        internalQueue.poll();
    }

    public void deliver(int number) {
        internalQueue.removeIf(o -> o.getNumber() == number);
    }

    public void draw() {
        System.out.println("Num  | Name");
        System.out.println("-----------");
        internalQueue.forEach(o -> System.out.print(o.toString() + "\n"));
    }
}
