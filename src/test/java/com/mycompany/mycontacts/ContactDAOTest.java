package com.mycompany.mycontacts;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ContactDAOTest {

    private ContactDAO contactDAO;
    
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
        contactDAO = new ContactDAO();
        // Use reflection to set the connection
        java.lang.reflect.Field connectionField = ContactDAO.class.getDeclaredField("connection");
        connectionField.setAccessible(true);
        connectionField.set(contactDAO, mockConnection);
    }
    
    @Test
    public void testCreateContact() throws Exception {
        // Arrange
        Contacts contact = new Contacts("John", "Doe", "john@email.com", "1234567890");
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        
        // Act
        boolean result = contactDAO.createContact(contact, 1);
        
        // Assert
        assertTrue(result);
        verify(mockPreparedStatement).setString(1, contact.getFirstName());
        verify(mockPreparedStatement).setString(2, contact.getLastName());
        verify(mockPreparedStatement).setString(3, contact.getEmail());
        verify(mockPreparedStatement).setString(4, contact.getMobilePhone());
        verify(mockPreparedStatement).setString(5, contact.getHomePhone());
        verify(mockPreparedStatement).setString(6, contact.getAddress());
        verify(mockPreparedStatement).setInt(7, 1);
    }
    
    @Test
    public void testGetAllContacts() throws Exception {
        // Arrange
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("FirstName")).thenReturn("John", "Jane");
        when(mockResultSet.getString("LastName")).thenReturn("Doe", "Smith");
        when(mockResultSet.getString("Email")).thenReturn("john@email.com", "jane@email.com");
        when(mockResultSet.getString("MobilePhone")).thenReturn("1234567890", "0987654321");
        when(mockResultSet.getString("HomePhone")).thenReturn("", "");
        when(mockResultSet.getString("Address")).thenReturn("", "");
        
        // Act
        List<Contacts> contacts = contactDAO.getAllContacts(1);
        
        // Assert
        assertEquals(2, contacts.size());
        assertEquals("John", contacts.get(0).getFirstName());
        assertEquals("Jane", contacts.get(1).getFirstName());
    }
    
    @Test
    public void testUpdateContact() throws Exception {
        // Arrange
        Contacts contact = new Contacts("John", "Doe", "john@email.com", "1234567890");
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        
        // Act
        boolean result = contactDAO.updateContact(1, contact, 1);
        
        // Assert
        assertTrue(result);
        verify(mockPreparedStatement).setString(1, contact.getFirstName());
        verify(mockPreparedStatement).setString(2, contact.getLastName());
        verify(mockPreparedStatement).setString(3, contact.getEmail());
        verify(mockPreparedStatement).setString(4, contact.getMobilePhone());
        verify(mockPreparedStatement).setString(5, contact.getHomePhone());
        verify(mockPreparedStatement).setString(6, contact.getAddress());
        verify(mockPreparedStatement).setInt(7, 1);
        verify(mockPreparedStatement).setInt(8, 1);
    }
    
    @Test
    public void testDeleteContact() throws Exception {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        
        // Act
        boolean result = contactDAO.deleteContact(1, 1);
        
        // Assert
        assertTrue(result);
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).setInt(2, 1);
    }
    
    @Test
    public void testSearchContacts() throws Exception {
        // Arrange
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("FirstName")).thenReturn("John");
        when(mockResultSet.getString("LastName")).thenReturn("Doe");
        when(mockResultSet.getString("Email")).thenReturn("john@email.com");
        when(mockResultSet.getString("MobilePhone")).thenReturn("1234567890");
        when(mockResultSet.getString("HomePhone")).thenReturn("");
        when(mockResultSet.getString("Address")).thenReturn("");
        
        // Act
        List<Contacts> contacts = contactDAO.searchContacts("FirstName", "John", 1);
        
        // Assert
        assertEquals(1, contacts.size());
        assertEquals("John", contacts.get(0).getFirstName());
        assertEquals("Doe", contacts.get(0).getLastName());
    }
} 