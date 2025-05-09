package com.mycompany.mycontacts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ContactsApp;encrypt=false";
    static final String USER = "";
    static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /*
        CREATE DATABASE ContactsApp;
        USE ContactsApp;
        
        -- Users table
        CREATE TABLE Users (
            id INT PRIMARY KEY IDENTITY(1,1),
            username NVARCHAR(100) NOT NULL UNIQUE,
            email NVARCHAR(100) NOT NULL UNIQUE,
            phone NVARCHAR(50),
            work NVARCHAR(50),
            password NVARCHAR(100) NOT NULL
        );

        -- Contacts table with user reference
        CREATE TABLE Contacts (
            ID INT IDENTITY PRIMARY KEY,
            FirstName NVARCHAR(50) NOT NULL,
            LastName NVARCHAR(50) NOT NULL,
            Email NVARCHAR(100) NOT NULL,
            MobilePhone NVARCHAR(20) NOT NULL,
            HomePhone NVARCHAR(20),
            Address NVARCHAR(100),
            UserId INT NOT NULL,
            FOREIGN KEY (UserId) REFERENCES Users(id)
        );      

    */
}
