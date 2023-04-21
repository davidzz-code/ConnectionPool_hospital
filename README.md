# ConnectionPool_hospital

# Project Name

The project is a Java application for managing doctor data in a database using HikariCP as the JDBC connection pool with MariaDB.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- MariaDB installed and configured on port 3306
- HikariCP library

## Configuration

1. Clone the repository.
2. Configure the database connection details in the `initDatabaseConnectionPool()` method in the `Application` class.
3. Compile the project with JDK.
4. Run the `Application` class to start the application.

## Features

The application offers the following functionalities:

- Create doctor data in the database.
- Read doctor data from the database.
- Update the specialty of a doctor in the database.
- Query doctor data by specialty from the database.
- Delete doctor data from the database.

## Usage

The application displays an interactive menu in the console that allows the user to interact with the doctor database. The menu has the following options:

- Create doctor data: The user can enter the name, specialty, and age of a doctor to add them to the database.
- Read doctor data: The user can view all the doctor data stored in the database, sorted by age in descending order.
- Update doctor's specialty: The user can update the specialty of an existing doctor in the database by entering the doctor's name and the new specialty.
- Query data by specialty: The user can query doctor data by specialty by entering the desired specialty.
- Delete doctor data: The user can delete the data of a doctor from the database by entering the name of the doctor to be deleted.

## Contribution

If you wish to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a working branch in your fork.
3. Make your changes and commit them.
4. Submit a pull request to the main branch of the repository.
5. Your contribution will be reviewed and merged if accepted.

## License

This project is licensed under the [MIT License](LICENSE).

## Author

This project was created by [Author's Name](https://github.com/authorname).
