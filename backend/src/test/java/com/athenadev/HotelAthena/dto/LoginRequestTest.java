package com.athenadev.HotelAthena.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LoginRequestTest {

    private LoginRequest loginRequest; // No need for @InjectMocks

    @BeforeEach
    void setUp() {
        loginRequest = new LoginRequest();  // Initialize loginRequest before each test
    }

    @Test
    void testEmailGetterAndSetter() {
        // Arrange: Set email
        String email = "test@example.com";
        loginRequest.setEmail(email);

        // Act & Assert: Check if the getter returns the correct value
        assertEquals(email, loginRequest.getEmail());
    }

    @Test
    void testPasswordGetterAndSetter() {
        // Arrange: Set password
        String password = "password123";
        loginRequest.setPassword(password);

        // Act & Assert: Check if the getter returns the correct value
        assertEquals(password, loginRequest.getPassword());
    }

    @Test
    void testAllFieldsNotNull() {
        // Arrange: Set both fields
        String email = "test@example.com";
        String password = "password123";
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        // Act & Assert: Ensure fields are not null
        assertNotNull(loginRequest.getEmail(), "Email should not be null");
        assertNotNull(loginRequest.getPassword(), "Password should not be null");
    }

    @Test
    void testToString() {
        // Arrange: Set email and password
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password123");

        // Act & Assert: Check if toString() contains these values
        String result = loginRequest.toString();
        assertNotNull(result);
        assertEquals("LoginRequest(email=test@example.com, password=password123)", result);
    }
}
