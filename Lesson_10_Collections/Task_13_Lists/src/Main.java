import phonebook.*;
import phonebook.record.PhonebookRecord;
import phonebook.record.Record;
import phonebook.recordextend.PhonebookRecordExtend;
import phonebook.recordextend.RecordExtend;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TestList<Integer> testListObject = new TestList<>();

        //region Test for countOccurrence
        final List<String> testList = new ArrayList<>(List.of("atalanta", "roma", "torino", "juventus",
                "fiorentina", "napoli", "juventus", "juventus", "lazio", "roma", "juventus", "milan",
                "napoli", "roma", "napoli", "sampdoria", "napoli", "juventus", "roma", "roma"));
        final String testString = "napoli";

        System.out.println("Word " + testString + " occurrences in list: " + testListObject.countOccurrence(testList, testString));
        //endregion

        //region Test for toList
        Integer[] testArray = new Integer[]{1, 2, 3, 4, 5};
        System.out.println("List from array: " + testListObject.toList(testArray).toString());
        //endregion

        //region Test for findUnique
        List<Integer> testList1 = new ArrayList<>(25);

        for (int i = 0; i < 15; i++) {
            testList1.add(new Random().nextInt(15));
        }

        System.out.println("Base list: " + testList1.toString());
        System.out.println("Unique list from base: " + testListObject.findUnique(testList1).toString());
        //endregion

        //region Test for calcOccurrence
        testListObject.calcOccurrence(testList);
        //endregion

        //region Test for findOccurrence
        System.out.println("[");
        for (Map.Entry<String, Integer> e : testListObject.findOccurrence(testList).entrySet()) {
            System.out.println("{name: \"" + e.getKey() + "\", occurrence: " + e.getValue() + "},");
        }
        System.out.println("]");
        //endregion

        //region Test for Phonebook
        Phonebook<Record> phonebookRecord = new PhonebookRecord();
        phonebookRecord.add(new Record("Howard", "3564875265"));
        phonebookRecord.add(new Record("Jams", "11235845"));
        phonebookRecord.add(new Record("Jams", "11235999"));
        phonebookRecord.add(new Record("Jams", "2221111"));
        phonebookRecord.add(new Record("Jams", "33366588"));
        phonebookRecord.add(new Record("Edward", "88888888"));

        System.out.println(phonebookRecord.toString());
        System.out.println(phonebookRecord.find("Jams").toString());
        //System.out.println(phonebookRecord.find("FFF").toString()); //Null Exception!!!!

        System.out.println(phonebookRecord.findAll("Jams").toString());
        //System.out.println(phonebookRecord.findAll("Rik").toString());  //Null Exception!!!!

        Phonebook<RecordExtend> recordExtendPhonebook = new PhonebookRecordExtend();
        recordExtendPhonebook.add(new RecordExtend("Howard", "3564875265", "Zip1"));
        recordExtendPhonebook.add(new RecordExtend("Jams", "11235845", "Zip2"));
        recordExtendPhonebook.add(new RecordExtend("Jams", "11235999", "Zip3"));
        recordExtendPhonebook.add(new RecordExtend("Jams", "2221111", "Zip4"));
        recordExtendPhonebook.add(new RecordExtend("Jams", "33366588", "Zip5"));
        recordExtendPhonebook.add(new RecordExtend("Edward", "88888888", "Zip6"));
        recordExtendPhonebook.add(new RecordExtend("Howard", "777777", "Zip8"));

        System.out.println(recordExtendPhonebook.toString());
        System.out.println(recordExtendPhonebook.find("Howard").toString());

        System.out.println(recordExtendPhonebook.findAll("Howard").toString());
        //endregion
    }
}
