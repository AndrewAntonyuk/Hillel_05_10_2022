import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();

        //region Test for addFirst()
        linkedList.addFirst(25);
        linkedList.addFirst("Test");
        linkedList.addFirst(36.8f);

        System.out.println(linkedList.toString());
        //endregion

        //region Test for isContained(o)
        System.out.println("Is linkedList contain \"25\": " + linkedList.isContain(25));
        System.out.println("Is linkedList contain \"PppDDr\": " + linkedList.isContain("PppDDr"));
        //endregion

        //region Test for size()
        System.out.println("Current size: " + linkedList.size());
        //endregion

        //region Test for add(o)
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        System.out.println("List after add(): " + linkedList.toString());
        //endregion

        //region Test for remove(o)
        linkedList.remove("Test");
        linkedList.remove("GGGgggRR");

        System.out.println("List after remove(): " + linkedList.toString());
        //endregion

        //region Test for get(i)
        linkedList.remove("Test");
        linkedList.remove("GGGgggRR");

        System.out.println("Value at get(1): " + linkedList.get(1));
        //endregion

        //region Test for set(i) and isEmpty()
        linkedList.set(2, "Two");

        System.out.println("List after set(2, Two): " + linkedList.toString());
        System.out.println("Is List empty: " + linkedList.isEmpty());
        //endregion

        //region Test for clear()
        linkedList.clear();

        System.out.println("List after clear(): " + linkedList.toString());
        //endregion

        //region Test for isEmpty()
        System.out.println("Is List empty: " + linkedList.isEmpty());
        //endregion

        //region Test for toArray()
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        System.out.println("Array is: " + Arrays.toString(linkedList.toArray()));
        //endregion

        //region Test for getFirst()
        System.out.println("Data at the first: " + (linkedList.getFirst().getData()));
        //endregion

        //region Test for getLast()
        System.out.println("Data at the last: " + (linkedList.getLast().getData()));
        //endregion

        //region Reverse text with linkedList
        StringBuilder test = new StringBuilder("Hello java!");
        linkedList.clear();

        for (int i = 0; i < test.length(); i++) {
            linkedList.add(test.charAt(i));
        }

        System.out.println("Base text: " + test);

        test = new StringBuilder();
        for (LinkedList l = linkedList.getLast(); l != null; ) {
            test.append(l.getData());
            l = l.getPrevious();
        }

        System.out.println("Reversed text: " + test);
        //endregion
    }
}
