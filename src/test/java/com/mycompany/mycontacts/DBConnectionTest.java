package com.mycompany.mycontacts;

import static org.junit.Assert.*;
import org.junit.Test;

import java.sql.Connection;

public class DBConnectionTest {

    @Test
    public void testGetConnection() {
        // Act
        Connection connection = DBConnection.getConnection();
        
        // Assert
        assertNotNull("Database connection should not be null", connection);
    }
    
    @Test
    public void testConnectionProperties() {
        // Act
        Connection connection = DBConnection.getConnection();
        
        // Assert
        assertNotNull("Database connection should not be null", connection);
        try {
            assertFalse("Connection should not be closed", connection.isClosed());
            assertTrue("Connection should be valid", connection.isValid(5));
        } catch (Exception e) {
            fail("Exception occurred while checking connection properties: " + e.getMessage());
        }
    }
    
    @Test
    public void testMultipleConnections() {
        // Act
        Connection connection1 = DBConnection.getConnection();
        Connection connection2 = DBConnection.getConnection();
        
        // Assert
        assertNotNull("First connection should not be null", connection1);
        assertNotNull("Second connection should not be null", connection2);
        assertSame("Both connections should be the same instance", connection1, connection2);
    }
} 