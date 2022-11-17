public class Main {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<String>();

        //region Test for .getSize() .toString() .isEmpty()
        System.out.println("--------------------------------");
        System.out.println("Test for .getSize() .toString() .isEmpty()");
        System.out.println(linkedList);
        System.out.println("Is list empty: " + linkedList.isEmpty());
        System.out.println("Size: " + linkedList.getSize());
        System.out.println("================================");
        //endregion

        //region Test for .addAtBegin() .addAtEnd()
        System.out.println("--------------------------------");
        System.out.println("Test for .addAtBegin() .addAtEnd() .isEmpty()");
        linkedList.addAtEnd("A");
        linkedList.addAtEnd("B");
        linkedList.addAtEnd("C");
        linkedList.addAtBegin("D");
        linkedList.addAtBegin("Q");
        System.out.println(linkedList);
        System.out.println("Is list empty: " + linkedList.isEmpty());
        System.out.println("Size: " + linkedList.getSize());
        System.out.println("================================");
        //endregion

        //region Test for .removeFromBegin() .removeFromEnd()
        System.out.println("--------------------------------");
        System.out.println("Test for .removeFromBegin() .removeFromEnd()");
        linkedList.removeFromBegin();
        linkedList.removeFromEnd();
        System.out.println(linkedList);
        System.out.println("Size: " + linkedList.getSize());
        System.out.println("================================");
        //endregion

        //region Test for .remove(int) .remove(Object)
        System.out.println("--------------------------------");
        System.out.println("Test for .remove(int) .remove(Object)");
        linkedList.remove(0);
        linkedList.remove("B");
        System.out.println(linkedList);
        System.out.println("Size: " + linkedList.getSize());
        System.out.println("================================");
        //endregion

        //region Test for .clear()
        System.out.println("--------------------------------");
        System.out.println("Test for .clear()");
        linkedList.clear();
        System.out.println(linkedList);
        System.out.println("Size: " + linkedList.getSize());
        System.out.println("================================");
        //endregion

        //region Test for .get(int i) .getFirst() .getLast()
        System.out.println("--------------------------------");
        System.out.println("Test for .get(int i) .getFirst() .getLast()");
        linkedList.addAtEnd("A");
        linkedList.addAtEnd("B");
        linkedList.addAtEnd("C");
        linkedList.addAtEnd("E");
        linkedList.addAtEnd("F");
        linkedList.addAtEnd("G");
        System.out.println(linkedList);
        System.out.println("Element at position 2: " + linkedList.get(2));
        System.out.println("First element: " + linkedList.getFirst());
        System.out.println("Last element: " + linkedList.getLast());
        System.out.println("================================");
        //endregion

        //region Test for .set(int i, E e)
        System.out.println("--------------------------------");
        System.out.println("Test for .set(int i, E e)");
        linkedList.set(2, "P");
        System.out.println(linkedList);
        System.out.println("Element at position 2 after .set(2, \"P\"): " + linkedList.get(2));
        System.out.println("================================");
        //endregion
    }
}
