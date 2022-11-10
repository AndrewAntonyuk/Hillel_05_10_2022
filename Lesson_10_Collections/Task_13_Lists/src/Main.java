import phonebook.Phonebook;
import phonebook.Record;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TestList<Integer> testListObject = new TestList<>();

        //region Test for countOccurrence
        final List<String> testList = new ArrayList<>(List.of("atalanta", "roma", "torino", "juventus",
                "fiorentina", "napoli", "juventus", "juventus", "lazio", "roma", "juventus", "milan",
                "napoli", "roma", "napoli", "sampdoria", "napoli", "juventus", "roma", "roma"));
        final String testString = "napoli";

        System.out.println("Test for countOccurrence():");
        System.out.println("=======================================================");
        System.out.println("Word " + testString + " occurrences in list: " + testListObject.countOccurrence(testList, testString));
        System.out.println("=======================================================");
        System.out.println("=======================================================");
        //endregion

        //region Test for toList
        Integer[] testArray = new Integer[]{1, 2, 3, 4, 5};

        System.out.println("Test for toList():");
        System.out.println("=======================================================");
        System.out.println("List from array: " + testListObject.toList(testArray).toString());
        System.out.println("=======================================================");
        System.out.println("=======================================================");
        //endregion

        //region Test for findUnique
        List<Integer> testList1 = new ArrayList<>(25);

        for (int i = 0; i < 15; i++) {
            testList1.add(new Random().nextInt(15));
        }

        System.out.println("Test for findUnique():");
        System.out.println("=======================================================");
        System.out.println("Base list: " + testList1.toString());
        System.out.println("Unique list from base: " + testListObject.findUnique(testList1).toString());
        System.out.println("=======================================================");
        System.out.println("=======================================================");
        //endregion

        //region Test for calcOccurrence
        System.out.println("Test for calcOccurrence():");
        System.out.println("=======================================================");
        testListObject.calcOccurrence(testList);
        System.out.println("=======================================================");
        System.out.println("=======================================================");
        //endregion

        //region Test for findOccurrence
        System.out.println("Test for findOccurrence():");
        System.out.println("=======================================================");
        System.out.println(testListObject.findOccurrence(testList).toString());
        System.out.println("=======================================================");
        System.out.println("=======================================================");
        //endregion

        //region Test for Phonebook
        Phonebook<Record> phonebookRecord = new Phonebook<Record>();
        phonebookRecord.add(new Record("Howard", "3564875265"));
        phonebookRecord.add(new Record("Jams", "11235845"));
        phonebookRecord.add(new Record("Jams", "11235999"));
        phonebookRecord.add(new Record("Jams", "2221111"));
        phonebookRecord.add(new Record("Jams", "33366588"));
        phonebookRecord.add(new Record("Edward", "88888888"));

        System.out.println("Test for phonebookRecord.find():");
        System.out.println("=======================================================");
        System.out.println(phonebookRecord.toString());
        System.out.println(phonebookRecord.find("Jams").toString());
        //System.out.println(phonebookRecord.find("FFF").toString()); //Null Exception!!!!
        System.out.println("=======================================================");
        System.out.println("=======================================================");

        System.out.println("Test for phonebookRecord.findAll():");
        System.out.println("=======================================================");
        System.out.println(phonebookRecord.findAll("Jams").toString());
        //System.out.println(phonebookRecord.findAll("Rik").toString());  //Null Exception!!!!
        System.out.println("=======================================================");
        System.out.println("=======================================================");
        //endregion
    }
}
