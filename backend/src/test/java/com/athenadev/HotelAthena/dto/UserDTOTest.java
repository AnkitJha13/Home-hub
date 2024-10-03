package com.athenadev.HotelAthena.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO();  // Initialize UserDTO object before each test
    }

    @Test
    void testIdGetterAndSetter() {
        // Arrange: Set id
        Long id = 1L;
        userDTO.setId(id);

        // Act & Assert: Check if the getter returns the correct value
        assertEquals(id, userDTO.getId());
    }

    @Test
    void testEmailGetterAndSetter() {
        // Arrange: Set email
        String email = "test@example.com";
        userDTO.setEmail(email);

        // Act & Assert: Check if the getter returns the correct value
        assertEquals(email, userDTO.getEmail());
    }

    @Test
    void testNameGetterAndSetter() {
        // Arrange: Set name
        String name = "John Doe";
        userDTO.setName(name);

        // Act & Assert: Check if the getter returns the correct value
        assertEquals(name, userDTO.getName());
    }

    @Test
    void testPhoneNumberGetterAndSetter() {
        // Arrange: Set phoneNumber
        String phoneNumber = "1234567890";
        userDTO.setPhoneNumber(phoneNumber);

        // Act & Assert: Check if the getter returns the correct value
        assertEquals(phoneNumber, userDTO.getPhoneNumber());
    }

    @Test
    void testRoleGetterAndSetter() {
        // Arrange: Set role
        String role = "Admin";
        userDTO.setRole(role);

        // Act & Assert: Check if the getter returns the correct value
        assertEquals(role, userDTO.getRole());
    }

    @Test
    void testBookingsGetterAndSetter() {
        // Arrange: Create a BookingDTO and set it in the bookings list
        BookingDTO booking = new BookingDTO();
        booking.setBookingConfirmationCode("CONF123");
        userDTO.getBookings().add(booking);

        // Act & Assert: Check if the bookings list contains the added booking
        assertNotNull(userDTO.getBookings());
        assertEquals(1, userDTO.getBookings().size());
        assertEquals("CONF123", userDTO.getBookings().get(0).getBookingConfirmationCode());
    }

    @Test
    void testBookingsInitialization() {
        // Act & Assert: Ensure bookings list is initialized as empty
        assertNotNull(userDTO.getBookings());
        assertTrue(userDTO.getBookings().isEmpty());
    }
}
