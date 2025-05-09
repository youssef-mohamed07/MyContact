package com.mycompany.mycontacts;

import static org.junit.Assert.*;
import org.junit.Test;

public class ContactsTest {

    @Test
    public void testValidContactCreation() {
        Contacts contact = new Contacts("John", "Doe", "john@email.com", "1234567890");
        assertTrue(Contacts.valid);
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("john@email.com", contact.getEmail());
        assertEquals("1234567890", contact.getMobilePhone());
        assertEquals("", contact.getHomePhone());
        assertEquals("", contact.getAddress());
    }

    @Test
    public void testContactWithHomePhone() {
        Contacts contact = new Contacts("John", "Doe", "john@email.com", "1234567890", "0987654321");
        assertTrue(Contacts.valid);
        assertEquals("0987654321", contact.getHomePhone());
    }

    @Test
    public void testContactWithAddress() {
        Contacts contact = new Contacts("John", "Doe", "john@email.com", "1234567890", "0987654321", "123 Main St");
        assertTrue(Contacts.valid);
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    public void testInvalidEmail() {
        new Contacts("John", "Doe", "invalid-email", "1234567890");
        assertFalse(Contacts.valid);
    }

    @Test
    public void testInvalidMobilePhone() {
        new Contacts("John", "Doe", "john@email.com", "invalid-phone");
        assertFalse(Contacts.valid);
    }

    @Test
    public void testInvalidHomePhone() {
        new Contacts("John", "Doe", "john@email.com", "1234567890", "invalid-phone");
        assertFalse(Contacts.valid);
    }

    @Test
    public void testEmptyFirstName() {
        new Contacts("", "Doe", "john@email.com", "1234567890");
        assertFalse(Contacts.valid);
    }

    @Test
    public void testEmptyLastName() {
        new Contacts("John", "", "john@email.com", "1234567890");
        assertFalse(Contacts.valid);
    }
} 