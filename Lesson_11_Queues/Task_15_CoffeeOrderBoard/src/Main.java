import coffee.order.CoffeeOrderBoard;
import coffee.order.Order;

public class Main {
    public static void main(String[] args) {
        CoffeeOrderBoard testOrderBoard = new CoffeeOrderBoard();

        //region Tests for add() + draw()
        System.out.println("==========================");
        System.out.println("Test for .add() + draw()\n");

        testOrderBoard.add("Luk");
        testOrderBoard.add("Sky");
        testOrderBoard.add("Walker");
        testOrderBoard.add("Obi");
        testOrderBoard.add("Wan");
        testOrderBoard.add("Kenobi");

        testOrderBoard.draw();
        System.out.println("==========================");
        //endregion

        //region Tests for deliver() + draw()
        System.out.println("==========================");
        System.out.println("Test for .deliver() + .draw()\n");

        testOrderBoard.deliver();
        testOrderBoard.deliver();

        testOrderBoard.draw();
        System.out.println("==========================");
        //endregion

        //region Tests for deliver(int number) + draw()
        System.out.println("==========================");
        System.out.println("Test for .deliver(int number) + .draw()\n");

        testOrderBoard.deliver(6);
        testOrderBoard.deliver(4);

        testOrderBoard.draw();
        System.out.println("==========================");
        //endregion

        //region Tests for add() + draw()
        System.out.println("==========================");
        System.out.println("Test for .add() + draw()\n");

        testOrderBoard.add("Chubaka");

        testOrderBoard.draw();
        System.out.println("==========================");
        //endregion
    }
}
