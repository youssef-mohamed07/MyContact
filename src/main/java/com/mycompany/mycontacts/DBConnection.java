package com.mycompany.mycontacts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=ContactsApp;encrypt=false";
    static final String USER = "your_username";
    static final String PASSWORD = "your_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /*
        CREATE DATABASE ContactsApp;
        USE ContactsApp;
        CREATE TABLE Users (
            id INT PRIMARY KEY IDENTITY(1,1),
            username NVARCHAR(100),
            email NVARCHAR(100),
            phone NVARCHAR(50),
            work NVARCHAR(50),
            password NVARCHAR(100)
        );
    */
}
