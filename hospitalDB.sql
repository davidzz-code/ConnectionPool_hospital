CREATE DATABASE hospitalDB;

USE hospitalDB;

CREATE TABLE doctors (
    id   INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    speciality VARCHAR(50),
    age INT,
    PRIMARY KEY (id)
);
