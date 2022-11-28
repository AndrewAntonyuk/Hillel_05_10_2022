import learningmap.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap testMap = new HashMap();

        testMap.put(25, "AAA");
        System.out.println(testMap);
        System.out.println("Size = " + testMap.getSize());

        testMap.put(26, "BBB");
        System.out.println(testMap);
        System.out.println("Size = " + testMap.getSize());

        testMap.put(27, "CCC");
        System.out.println(testMap);
        System.out.println("Size = " + testMap.getSize());

        testMap.put(28, "DDD");
        System.out.println(testMap);
        System.out.println("Size = " + testMap.getSize());

        testMap.put(29, "EEE");
        System.out.println(testMap);
        System.out.println("Size = " + testMap.getSize());

        testMap.put(5, "gty");
        System.out.println(testMap);
        System.out.println("Size = " + testMap.getSize());

        testMap.put(7, "jjju");
        System.out.println(testMap);
        System.out.println("Size = " + testMap.getSize());

        testMap.put(589, "loi");
        System.out.println(testMap);
        System.out.println("Size = " + testMap.getSize());

        testMap.remove(28);
        System.out.println(testMap);
        System.out.println("Size = " + testMap.getSize());
    }
}
