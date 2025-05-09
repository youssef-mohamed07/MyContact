package com.mycompany.mycontacts;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserDAOTest {

    private UserDAO userDAO;
    
    @Mock
    private Connection mockConnection;
    
    @Mock
    private PreparedStatement mockPreparedStatement;
    
    @Mock
    private Statement mockStatement;
    
    @Mock
    private ResultSet mockResultSet;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userDAO = new UserDAO();
        // Use reflection to set the connection
        java.lang.reflect.Field connectionField = UserDAO.class.getDeclaredField("connection");
        connectionField.setAccessible(true);
        connectionField.set(userDAO, mockConnection);
    }
    
    @Test
    public void testCreateUser() throws Exception {
        // Arrange
        User user = new User(1, "testUser", "test@email.com", "1234567890", "work123");
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        
        // Act
        boolean result = userDAO.createUser(user);
        
        // Assert
        assertTrue(result);
        verify(mockPreparedStatement).setString(1, user.getUsername());
        verify(mockPreparedStatement).setString(2, user.getEmail());
        verify(mockPreparedStatement).setString(3, user.getPhone());
        verify(mockPreparedStatement).setString(4, user.getWork());
    }
    
    @Test
    public void testGetUser() throws Exception {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("Id")).thenReturn(1);
        when(mockResultSet.getString("Username")).thenReturn("testUser");
        when(mockResultSet.getString("Email")).thenReturn("test@email.com");
        when(mockResultSet.getString("Phone")).thenReturn("1234567890");
        when(mockResultSet.getString("Work")).thenReturn("work123");
        
        // Act
        User user = userDAO.getUser("testUser");
        
        // Assert
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("test@email.com", user.getEmail());
        assertEquals("1234567890", user.getPhone());
        assertEquals("work123", user.getWork());
    }
    
    @Test
    public void testUpdateUser() throws Exception {
        // Arrange
        User user = new User(1, "testUser", "test@email.com", "1234567890", "work123");
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        
        // Act
        boolean result = userDAO.updateUser(user);
        
        // Assert
        assertTrue(result);
        verify(mockPreparedStatement).setString(1, user.getUsername());
        verify(mockPreparedStatement).setString(2, user.getEmail());
        verify(mockPreparedStatement).setString(3, user.getPhone());
        verify(mockPreparedStatement).setString(4, user.getWork());
        verify(mockPreparedStatement).setInt(5, user.getId());
    }
    
    @Test
    public void testDeleteUser() throws Exception {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        
        // Act
        boolean result = userDAO.deleteUser(1);
        
        // Assert
        assertTrue(result);
        verify(mockPreparedStatement).setInt(1, 1);
    }
    
    @Test
    public void testValidateUser() throws Exception {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("Id")).thenReturn(1);
        
        // Act
        int userId = userDAO.validateUser("testUser", "password123");
        
        // Assert
        assertEquals(1, userId);
        verify(mockPreparedStatement).setString(1, "testUser");
        verify(mockPreparedStatement).setString(2, "password123");
    }
} 