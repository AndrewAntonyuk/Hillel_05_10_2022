package utils;

import db.DBView;
import models.Person;

import java.util.Scanner;

public class Menu {
    private final DBView dbView = new DBView();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        showAdvice();
        readUserInput();
    }

    private void readUserInput() {
        int typed = -1;

        while (typed != 0) {
            typed = scanner.nextInt();

            switch (typed) {
                case 0 -> System.out.println("Good luck!");
                case 1 -> {
                    showAllPeople();
                }
                case 2 -> {
                    addNewPerson();
                }
                case 3 -> {
                    showOlderPeople();
                }
                case 4 -> {
                    deletePerson();
                }
                case 5 -> {
                    updatePerson();
                }
                case 6 -> {
                    showPersonByID();
                }
                case 7 -> {
                    showPeopleByFirstName();
                }
                default -> {
                    System.out.println("Undefined input " + typed);
                    showAdvice();
                }
            }
        }
    }

    private void showAllPeople() {
        dbView.getAllPeople();
        System.out.println("=================================");
    }

    private void addNewPerson() {
        dbView.addPerson(readPersonData());
        System.out.println("=================================");
    }

    private void showOlderPeople() {
        System.out.println("Enter age for filter:");

        int typedAge = scanner.nextInt();

        dbView.getOlderPeople(typedAge);

        System.out.println("=================================");
    }

    private void deletePerson() {
        System.out.println("Enter ID of person for delete:");

        int typedID = scanner.nextInt();

        dbView.delete(typedID);

        System.out.println("=================================");
    }

    private void updatePerson() {
        System.out.println("Enter ID of person for update:");

        int typedID = scanner.nextInt();

        System.out.println("Old person data:");
        dbView.getPersonByID(typedID);

        Person person = new Person();

        person.setId(typedID);

        System.out.println("Enter new first name:");
        person.setFirstName(scanner.next());

        System.out.println("Enter new last name:");
        person.setLastName(scanner.next());

        System.out.println("Enter new age:");
        person.setAge(scanner.nextInt());

        dbView.update(person);
        System.out.println("=================================");
    }

    private void showPersonByID() {
        System.out.println("Enter ID of searched person:");

        int typedID = scanner.nextInt();

        dbView.getPersonByID(typedID);

        System.out.println("=================================");
    }

    private void showPeopleByFirstName() {
        System.out.println("Enter first name of searched person:");

        String typedName = scanner.next();

        dbView.getPeopleByFirstName(typedName);

        System.out.println("=================================");
    }

    private Person readPersonData() {
        Person person = new Person();

        System.out.println("Enter first name:");
        person.setFirstName(scanner.next());

        System.out.println("Enter last name:");
        person.setLastName(scanner.next());

        System.out.println("Enter age:");
        person.setAge(scanner.nextInt());

        return person;
    }

    private void showAdvice() {
        System.out.println("1 - Show all users");
        System.out.println("2 - Add new users");
        System.out.println("3 - Show users older than set age");
        System.out.println("4 - Delete user");
        System.out.println("5 - Update user data");
        System.out.println("6 - Show user by ID");
        System.out.println("7 - Show user by first name");
        System.out.println("0 - Exit");
        System.out.println("Please, enter your choice:");
        System.out.println("=================================");
    }
}
