package com.mycompany.mycontacts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    private Connection connection;

    public ContactDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create a new contact for a specific user
    public boolean createContact(Contacts contact, int userId) {
        String sql = "INSERT INTO Contacts (FirstName, LastName, Email, MobilePhone, HomePhone, Address, UserId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, contact.getFirstName());
            pstmt.setString(2, contact.getLastName());
            pstmt.setString(3, contact.getEmail());
            pstmt.setString(4, contact.getMobilePhone());
            pstmt.setString(5, contact.getHomePhone());
            pstmt.setString(6, contact.getAddress());
            pstmt.setInt(7, userId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read all contacts for a specific user
    public List<Contacts> getAllContacts(int userId) {
        List<Contacts> contacts = new ArrayList<>();
        String sql = "SELECT * FROM Contacts WHERE UserId = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Contacts contact = new Contacts(
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    rs.getString("MobilePhone"),
                    rs.getString("HomePhone"),
                    rs.getString("Address")
                );
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    // Update a contact for a specific user
    public boolean updateContact(int contactId, Contacts contact, int userId) {
        String sql = "UPDATE Contacts SET FirstName=?, LastName=?, Email=?, MobilePhone=?, HomePhone=?, Address=? WHERE ID=? AND UserId=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, contact.getFirstName());
            pstmt.setString(2, contact.getLastName());
            pstmt.setString(3, contact.getEmail());
            pstmt.setString(4, contact.getMobilePhone());
            pstmt.setString(5, contact.getHomePhone());
            pstmt.setString(6, contact.getAddress());
            pstmt.setInt(7, contactId);
            pstmt.setInt(8, userId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a contact for a specific user
    public boolean deleteContact(int contactId, int userId) {
        String sql = "DELETE FROM Contacts WHERE ID=? AND UserId=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, contactId);
            pstmt.setInt(2, userId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Search contacts for a specific user
    public List<Contacts> searchContacts(String field, String value, int userId) {
        List<Contacts> contacts = new ArrayList<>();
        String sql = "SELECT * FROM Contacts WHERE " + field + " LIKE ? AND UserId = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + value + "%");
            pstmt.setInt(2, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Contacts contact = new Contacts(
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    rs.getString("MobilePhone"),
                    rs.getString("HomePhone"),
                    rs.getString("Address")
                );
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }
} 