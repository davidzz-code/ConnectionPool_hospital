package org.example;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    private static HikariDataSource dataSource;

    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        String name;
        String speciality;
        int age;

        try {
            initDatabaseConnectionPool();
            deleteData("%"); // Deletes all the data
            readData();
            for (int i = 0; i < 3; i++){ // Creating three data rows
                System.out.print("Doctor's name: ");
                name = scan.nextLine();
                System.out.print("Age: ");
                age = scan.nextInt();
                scan.nextLine();
                System.out.print("Speciality: ");
                speciality = scan.nextLine();
                createData(name, speciality, age);
            }
            readData();

            System.out.println("Which doctor's speciality do you want to change?: "); // Updating data
            System.out.print("Name: ");
            name = scan.nextLine();
            System.out.print("Which is the new speciality?: ");
            String newSpeciality = scan.nextLine();
            updateData(name, newSpeciality);
            readData();

            System.out.println("Let's query by speciality"); // Querying data
            System.out.print("Write the speciality to query: ");
            speciality = scan.nextLine();
            queryData(speciality);

            System.out.println("Doctor's table complete:");
            readData();

            System.out.println("Which doctor do you want to delete?"); // Deleting data by doctor's name
            System.out.print("Name: ");
            String nameToDelete = scan.nextLine();
            deleteData(nameToDelete);
            readData();
        } finally {
            closeDatabaseConnectionPool();
        }
    }

    private static void createData(String name, String speciality, int age) throws SQLException {
        System.out.println("Creating data...");
        int rowsInserted;
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    INSERT INTO doctors(name, speciality, age)
                    VALUES (?, ?, ?)
                    """)) {
                statement.setString(1, name);
                statement.setString(2, speciality);
                statement.setInt(3, age);
                rowsInserted = statement.executeUpdate();
            }
        }
        System.out.println("Rows inserted: " + rowsInserted);
    }


    private static void readData() throws SQLException {
        System.out.println("Reading data...");
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                        SELECT name, speciality, age
                        FROM doctors
                        ORDER BY age DESC;
                    """)) {
                ResultSet resultSet = statement.executeQuery();
                boolean empty = true;
                while(resultSet.next()){
                    String name = resultSet.getString(1);
                    String speciality = resultSet.getString(2);
                    int age = resultSet.getInt(3);
                    System.out.println("\t> " + name + ", " + age + " years old: " + speciality);
                    empty = false;
                }
                if(empty){
                    System.out.println("\t (No data)");
                }
            }
        }
    }

    private static void updateData(String name, String newSpeciality) throws SQLException {
        System.out.println("Updating data...");
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                        UPDATE doctors
                        SET speciality = ?
                        WHERE name = ?
                    """)) {

                statement.setString(1, newSpeciality);
                statement.setString(2, name);
                int rowsUpdated = statement.executeUpdate();
                System.out.println("Rows updated: " + rowsUpdated);

            }
        }
    }

    private static void queryData(String speciality) throws SQLException {
        System.out.println("Querying data...");
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                        SELECT *
                        FROM doctors
                        WHERE speciality = ?
                        ORDER BY age DESC
                    """)) {

                statement.setString(1, speciality);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        // It shows data stored in ResultSet
                        String name = resultSet.getString("name");
                        int age = resultSet.getInt("age");
                        System.out.println("\t > " + name + ", " + age + ", " + speciality);
                    }
                }
            }
        }
    }

    private static void deleteData(String nameToDelete) throws SQLException {
        System.out.println("Deleting data...");
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                        DELETE FROM doctors
                        WHERE name LIKE ?
                    """)) {
                statement.setString(1, nameToDelete);
                int rowsDeleted = statement.executeUpdate();
                System.out.println("Rows deleted: " + rowsDeleted);
            }
        }

    }

    private static void initDatabaseConnectionPool() {
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mariadb://localhost:3306/hospitalDB");
        dataSource.setUsername("davidramirez");
        dataSource.setPassword("password");
    }

    private static void closeDatabaseConnectionPool() {
        dataSource.close();
    }
}
