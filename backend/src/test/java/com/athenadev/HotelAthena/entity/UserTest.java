package com.athenadev.HotelAthena.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setName("Test User");
        user.setPhoneNumber("1234567890");
        user.setPassword("securepassword");
        user.setRole("ROLE_USER");
    }

    @Test
    void testGetId() {
        assertEquals(1L, user.getId());
    }

    @Test
    void testGetEmail() {
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void testGetName() {
        assertEquals("Test User", user.getName());
    }

    @Test
    void testGetPhoneNumber() {
        assertEquals("1234567890", user.getPhoneNumber());
    }

    @Test
    void testGetPassword() {
        assertEquals("securepassword", user.getPassword());
    }

    @Test
    void testGetRole() {
        assertEquals("ROLE_USER", user.getRole());
    }

    @Test
    void testGetBookings(){
        List<Booking> bookings = user.getBookings();
        assertNotNull(bookings);
        assertTrue(bookings.isEmpty());

    }

    @Test
    void testGetAuthorities(){
        assertEquals(List.of(new SimpleGrantedAuthority("ROLE_USER")), user.getAuthorities());
    }

    @Test
    void testGetUsername() {
        assertEquals("test@example.com", user.getUsername());
    }

    @Test
    void testIsAccountNonExpired() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        assertTrue(user.isEnabled());
    }

    @Test
    void testToString() {
        String expected = "User{id=1, email='test@example.com', name='Test User', phoneNumber='1234567890', password='securepassword', role='ROLE_USER'}";
        assertEquals(expected, user.toString());
    }

}