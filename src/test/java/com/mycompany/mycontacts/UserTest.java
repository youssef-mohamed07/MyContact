package com.mycompany.mycontacts;

import static org.junit.Assert.*;
import org.junit.Test;

public class UserTest {

    @Test
    public void testUserCreation() {
        User user = new User(1, "testUser", "test@email.com", "1234567890", "work123");
        
        assertEquals(1, user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("test@email.com", user.getEmail());
        assertEquals("1234567890", user.getPhone());
        assertEquals("work123", user.getWork());
    }

    @Test
    public void testUserSetters() {
        User user = new User(1, "testUser", "test@email.com", "1234567890", "work123");
        
        user.setUsername("newUsername");
        user.setEmail("new@email.com");
        user.setPhone("9876543210");
        user.setWork("newWork");
        
        assertEquals("newUsername", user.getUsername());
        assertEquals("new@email.com", user.getEmail());
        assertEquals("9876543210", user.getPhone());
        assertEquals("newWork", user.getWork());
    }

    @Test
    public void testUserValidation() {
        // Test valid user
        User validUser = new User(1, "testUser", "test@email.com", "1234567890", "work123");
        assertTrue(validUser.isValid());
        
        // Test invalid email
        User invalidEmailUser = new User(1, "testUser", "invalid-email", "1234567890", "work123");
        assertFalse(invalidEmailUser.isValid());
        
        // Test invalid phone
        User invalidPhoneUser = new User(1, "testUser", "test@email.com", "invalid-phone", "work123");
        assertFalse(invalidPhoneUser.isValid());
    }
} 