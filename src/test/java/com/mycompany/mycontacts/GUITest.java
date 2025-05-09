package com.mycompany.mycontacts;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GUITest {

    private GUI gui;
    private User testUser;
    
    @Mock
    private ContactDAO mockContactDAO;
    
    @Mock
    private UserDAO mockUserDAO;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testUser = new User(1, "testUser", "test@email.com", "1234567890", "work123");
        gui = new GUI(testUser);
        gui.contactDAO = mockContactDAO;
        gui.userDAO = mockUserDAO;
    }
    
    @Test
    public void testLoadContactsFromDatabase() {
        // Arrange
        List<Contacts> mockContacts = new ArrayList<>();
        mockContacts.add(new Contacts("John", "Doe", "john@email.com", "1234567890"));
        mockContacts.add(new Contacts("Jane", "Smith", "jane@email.com", "0987654321"));
        
        when(mockContactDAO.getAllContacts(testUser.getId())).thenReturn(mockContacts);
        
        // Act
        gui.loadContactsFromDatabase();
        
        // Assert
        DefaultTableModel model = (DefaultTableModel) gui.table.getModel();
        assertEquals(2, model.getRowCount());
        assertEquals("John", model.getValueAt(0, 0));
        assertEquals("Doe", model.getValueAt(0, 1));
        assertEquals("john@email.com", model.getValueAt(0, 2));
        assertEquals("1234567890", model.getValueAt(0, 3));
    }
    
    @Test
    public void testAddContact() {
        // Arrange
        gui.namefField.setText("John");
        gui.namelField.setText("Doe");
        gui.emailField.setText("john@email.com");
        gui.mobilePhoneField.setText("1234567890");
        
        Contacts expectedContact = new Contacts("John", "Doe", "john@email.com", "1234567890");
        when(mockContactDAO.createContact(any(Contacts.class), eq(testUser.getId()))).thenReturn(true);
        
        // Act
        ActionEvent addEvent = new ActionEvent(gui.addButton, ActionEvent.ACTION_PERFORMED, "Add");
        gui.actionPerformed(addEvent);
        
        // Assert
        verify(mockContactDAO).createContact(any(Contacts.class), eq(testUser.getId()));
        DefaultTableModel model = (DefaultTableModel) gui.table.getModel();
        assertEquals(1, model.getRowCount());
        assertEquals("John", model.getValueAt(0, 0));
        assertEquals("Doe", model.getValueAt(0, 1));
        assertEquals("john@email.com", model.getValueAt(0, 2));
        assertEquals("1234567890", model.getValueAt(0, 3));
    }
    
    @Test
    public void testDeleteContact() {
        // Arrange
        DefaultTableModel model = (DefaultTableModel) gui.table.getModel();
        model.addRow(new Object[]{"John", "Doe", "john@email.com", "1234567890", "", ""});
        gui.table.setRowSelectionInterval(0, 0);
        gui.selectedContactId = 1;
        
        when(mockContactDAO.deleteContact(1, testUser.getId())).thenReturn(true);
        
        // Act
        ActionEvent deleteEvent = new ActionEvent(gui.deleteButton, ActionEvent.ACTION_PERFORMED, "Delete");
        gui.actionPerformed(deleteEvent);
        
        // Assert
        verify(mockContactDAO).deleteContact(1, testUser.getId());
        assertEquals(0, model.getRowCount());
    }
    
    @Test
    public void testUpdateContact() {
        // Arrange
        DefaultTableModel model = (DefaultTableModel) gui.table.getModel();
        model.addRow(new Object[]{"John", "Doe", "john@email.com", "1234567890", "", ""});
        gui.table.setRowSelectionInterval(0, 0);
        gui.selectedContactId = 1;
        
        gui.namefField.setText("John Updated");
        gui.namelField.setText("Doe Updated");
        gui.emailField.setText("john.updated@email.com");
        gui.mobilePhoneField.setText("9876543210");
        
        when(mockContactDAO.updateContact(eq(1), any(Contacts.class), eq(testUser.getId()))).thenReturn(true);
        
        // Act
        ActionEvent updateEvent = new ActionEvent(gui.updateButton, ActionEvent.ACTION_PERFORMED, "Update");
        gui.actionPerformed(updateEvent);
        
        // Assert
        verify(mockContactDAO).updateContact(eq(1), any(Contacts.class), eq(testUser.getId()));
        assertEquals("John Updated", model.getValueAt(0, 0));
        assertEquals("Doe Updated", model.getValueAt(0, 1));
        assertEquals("john.updated@email.com", model.getValueAt(0, 2));
        assertEquals("9876543210", model.getValueAt(0, 3));
    }
    
    @Test
    public void testSearchContacts() {
        // Arrange
        List<Contacts> mockSearchResults = new ArrayList<>();
        mockSearchResults.add(new Contacts("John", "Doe", "john@email.com", "1234567890"));
        
        when(mockContactDAO.searchContacts("FirstName", "John", testUser.getId())).thenReturn(mockSearchResults);
        
        gui.searchlField.setText("John");
        gui.searchComboBox.setSelectedItem("Name");
        
        // Act
        ActionEvent searchEvent = new ActionEvent(gui.searchButton, ActionEvent.ACTION_PERFORMED, "Search");
        gui.actionPerformed(searchEvent);
        
        // Assert
        verify(mockContactDAO).searchContacts("FirstName", "John", testUser.getId());
        DefaultTableModel model = (DefaultTableModel) gui.table.getModel();
        assertEquals(1, model.getRowCount());
        assertEquals("John", model.getValueAt(0, 0));
        assertEquals("Doe", model.getValueAt(0, 1));
        assertEquals("john@email.com", model.getValueAt(0, 2));
        assertEquals("1234567890", model.getValueAt(0, 3));
    }
    
    @Test
    public void testClearFields() {
        // Arrange
        gui.namefField.setText("John");
        gui.namelField.setText("Doe");
        gui.emailField.setText("john@email.com");
        gui.mobilePhoneField.setText("1234567890");
        gui.homePhoneField.setText("0987654321");
        gui.AddressField.setText("123 Main St");
        gui.selectedContactId = 1;
        
        // Act
        gui.clearFields();
        
        // Assert
        assertEquals("", gui.namefField.getText());
        assertEquals("", gui.namelField.getText());
        assertEquals("", gui.emailField.getText());
        assertEquals("", gui.mobilePhoneField.getText());
        assertEquals("", gui.homePhoneField.getText());
        assertEquals("", gui.AddressField.getText());
        assertEquals(-1, gui.selectedContactId);
    }
} 