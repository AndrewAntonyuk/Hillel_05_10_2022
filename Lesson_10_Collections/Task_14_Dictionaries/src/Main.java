public class Main {
    public static void main(String[] args) {
        FileNavigator testNavigator = new FileNavigator();

        testNavigator.add("/TestPath/New", new FileData("MyNewFile.txt", "/TestPath/New", 35));
        testNavigator.add("/TestPath/New", new FileData("MyNewFile1.txt", "/TestPath/New", 351));
        testNavigator.add("/TestPath/New", new FileData("MyNewFile2.txt", "/TestPath/New", 352));
        //testNavigator.add("/TestPath/New", new FileData("MyNewFile2.txt", "/TestPath/New111",352)); //IllegalArgumentException occurred
        testNavigator.add("/TestPath/New", new FileData("MyNewFile3.txt", "/TestPath/New", 353));
        testNavigator.add("/TestPath/Old", new FileData("MyOldFile3.txt", "/TestPath/Old", 353));
        testNavigator.add("/TestPath/Old", new FileData("MyOldFile3.txt", "/TestPath/Old", 353));

        System.out.println(testNavigator.toString());
        System.out.println("Test for find():");
        System.out.println("=======================================================");
        System.out.println(testNavigator.find("/TestPath/New").toString());
        System.out.println("=======================================================");
        System.out.println("=======================================================");

        System.out.println("Test for filterBySize():");
        System.out.println("=======================================================");
        System.out.println(testNavigator.filterBySize(353).toString());
        System.out.println("=======================================================");
        System.out.println("=======================================================");

        System.out.println("Test for remove():");
        System.out.println("=======================================================");
        testNavigator.remove("/TestPath/Old");
        System.out.println(testNavigator.toString());
        System.out.println("=======================================================");
        System.out.println("=======================================================");

        System.out.println("Test for sortBySize():");
        System.out.println("=======================================================");
        testNavigator.add("/TestPath/Old", new FileData("MyOldFile3.txt", "/TestPath/Old", 457));
        testNavigator.add("/TestPath/Old", new FileData("MyOldFile3.txt", "/TestPath/Old", 31));
        testNavigator.add("/TestPath/Old", new FileData("MyOldFile3.txt", "/TestPath/Old", 31));
        testNavigator.add("/TestPath/Old", new FileData("MyOldFile3.txt", "/TestPath/Old", 3));
        testNavigator.add("/TestPath/Old", new FileData("MyOldFile3.txt", "/TestPath/Old", 3539));
        testNavigator.add("/TestPath/Old", new FileData("MyOldFile3.txt", "/TestPath/Old", 589));
        System.out.println(testNavigator.sortBySize().toString());
        System.out.println("=======================================================");
        System.out.println("=======================================================");
    }
}
