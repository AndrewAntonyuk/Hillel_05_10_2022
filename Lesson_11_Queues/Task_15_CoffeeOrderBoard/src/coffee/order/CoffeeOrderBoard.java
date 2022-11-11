package coffee.order;

import java.util.LinkedList;

public class CoffeeOrderBoard {
    private final LinkedList<Order> internalLinkedList = new LinkedList<Order>();
    private Integer currentNumber = 1;

    public void add(String name) {
        internalLinkedList.offer(new Order((currentNumber++), name));
    }

    public void deliver() {
        internalLinkedList.poll();
    }

    public void deliver(int number) {
        internalLinkedList.removeIf(o -> o.getNumber() == number);
    }

    public void draw() {
        System.out.println("Num  | Name");
        System.out.println("-----------");
        internalLinkedList.forEach(o -> System.out.print(o.toString() + "\n"));
    }
}
