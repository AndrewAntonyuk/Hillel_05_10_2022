package db;

import models.Person;

import java.sql.*;
import java.util.ArrayList;

public class DBView {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_example";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456789";

    public void getAllPeople() {
        Connection connection = getConnection();
        ArrayList<Person> people = new ArrayList<>();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM persons");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                people.add(new Person(resultSet.getInt(1), resultSet.getString(2)
                        , resultSet.getString(3), resultSet.getInt(4)
                ));
            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        people.forEach(System.out::println);
    }

    public void addPerson(Person person) {
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO persons(first_name, last_name, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getAge());

            preparedStatement.execute();

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getOlderPeople(int age) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;
        ArrayList<Person> people = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM persons WHERE age > ?");
            preparedStatement.setInt(1, age);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                people.add(new Person(resultSet.getInt(1), resultSet.getString(2)
                        , resultSet.getString(3), resultSet.getInt(4)
                ));
            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        people.forEach(System.out::println);
    }

    public void delete(int id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM persons WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Person person) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement("UPDATE persons SET " +
                    "first_name = ?, " +
                    "last_name = ?, " +
                    "age = ? " +
                    "WHERE id = ?");

            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.setInt(4, person.getId());
            preparedStatement.execute();

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getPersonByID(int id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;
        Person person = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM persons WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                person = new Person(resultSet.getInt(1), resultSet.getString(2)
                        , resultSet.getString(3), resultSet.getInt(4));

            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (person != null) {
            System.out.println(person);
        } else {
            System.out.println("Person with ID " + id + " is undefined");
        }
    }

    public void getPeopleByFirstName(String name) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;
        ArrayList<Person> people = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM persons WHERE first_name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                people.add(new Person(resultSet.getInt(1), resultSet.getString(2)
                        , resultSet.getString(3), resultSet.getInt(4)
                ));
            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        people.forEach(System.out::println);
    }

    private Connection getConnection() {
        Connection connection;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}
