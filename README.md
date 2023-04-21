# Connection Pool  - Hospital database

This is a Java application that interacts with a MariaDB database named "hospitalDB" using the HikariCP connection pool. The application allows for CRUD (Create, Read, Update, Delete) operations on a "doctors" table in the database.

## Prerequisites
To run this application, you need to have the following:

- Java Development Kit (JDK) installed on your system
- MariaDB database server installed and running on localhost
- MariaDB JDBC driver added to your application's classpath

## Getting Started
1. Clone the repository or download the source code.
2. Import the project into your Java IDE.
3. Update the database connection properties in the `initDatabaseConnectionPool()` method of the `Application` class. Set the appropriate values for the JDBC URL, username, and password according to your MariaDB setup.
4. Build and run the application.

## Functionality
The `Application` class provides the following functionality:

- `createData(String name, String speciality, int age)`: Inserts a new row into the "doctors" table with the given name, speciality, and age.
- `readData()`: Retrieves all rows from the "doctors" table and displays them on the console.
- `updateData(String name, String newSpeciality)`: Updates the speciality of a doctor with the given name in the "doctors" table.
- `queryData(String speciality)`: Retrieves all rows from the "doctors" table with the given speciality and displays them on the console.
- `deleteData(String nameToDelete)`: Deletes all rows from the "doctors" table that match the given name.

The application interacts with the user through the console, prompting for input for creating, updating, querying, and deleting doctors' data.

## Usage
1. Run the application.
2. Follow the prompts to perform CRUD operations on the "doctors" table in the "hospitalDB" database.
3. View the results of the operations on the console.

## Database Schema
The "doctors" table in the "hospitalDB" database has the following schema:

```sql
CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    speciality VARCHAR(255) NOT NULL,
    age INT NOT NULL
);
```

The table has four columns: id (auto-incrementing primary key), name (doctor's name), speciality (doctor's speciality), and age (doctor's age).

## Dependencies
This application uses the following dependencies:

- HikariCP: A high-performance JDBC connection pool.
- MariaDB JDBC Driver: A JDBC driver for connecting to MariaDB databases.
Make sure to include these dependencies in your project's classpath.

## Conclusion
This Java application provides basic CRUD operations on a MariaDB database using HikariCP connection pooling. It allows for creating, reading, updating, and deleting doctors' data in the "hospitalDB" database. You can customize the database connection properties and extend the functionality as needed for your specific use case.
