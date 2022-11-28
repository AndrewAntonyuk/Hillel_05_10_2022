public class Main {
    public static void main(String[] args) {
        Tree<Integer> testTree = new Tree<>();

        testTree.insert(7);
        System.out.println(testTree);
        testTree.insert(4);
        System.out.println(testTree);
        testTree.insert(5);
        System.out.println(testTree);
        testTree.insert(9);
        System.out.println(testTree);
        testTree.insert(1);
        System.out.println(testTree);
        testTree.insert(22);
        System.out.println(testTree);
        testTree.insert(50);
        System.out.println(testTree);

        Tree<String> testTree1 = new Tree<>();

        testTree1.insert("A");
        System.out.println(testTree1);
        testTree1.insert("F");
        System.out.println(testTree1);
        testTree1.insert("H");
        System.out.println(testTree1);
        testTree1.remove("F");
        System.out.println(testTree1);
        testTree1.insert("F");
        System.out.println(testTree1);
    }
}
