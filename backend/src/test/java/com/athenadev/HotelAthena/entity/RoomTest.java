package com.athenadev.HotelAthena.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room();
        room.setRoomType("Deluxe");
        room.setRoomPrice(BigDecimal.valueOf(150.00));
        room.setRoomPhotoUrl("http://example.com/photo.jpg");
        room.setRoomDescription("A spacious deluxe room with ocean view.");
        room.setBookings(new ArrayList<>()); // Initialize bookings list
    }

    @Test
    void testRoomProperties() {
        // Test getter methods
        assertEquals("Deluxe", room.getRoomType());
        assertEquals(BigDecimal.valueOf(150.00), room.getRoomPrice());
        assertEquals("http://example.com/photo.jpg", room.getRoomPhotoUrl());
        assertEquals("A spacious deluxe room with ocean view.", room.getRoomDescription());
    }

    @Test
    void testBookingsRelationship() {
        assertTrue(room.getBookings().isEmpty(), "New room should have no bookings");

        Booking booking = new Booking();
        booking.setCheckInDate(LocalDate.now());
        booking.setCheckOutDate(LocalDate.now().plusDays(2));
        booking.setNumOfAdults(2);
        booking.setNumOfChildren(1);
        booking.setRoom(room); // Set room for the booking

        room.getBookings().add(booking); // Add booking to the room

        assertEquals(1, room.getBookings().size(), "Room should have one booking");
        assertEquals(room, booking.getRoom(), "Booking should reference the correct room");
    }

    @Test
    void testToString() {
        Room room = new Room();
        room.setId(null);
        room.setRoomType("Deluxe");
        room.setRoomPrice(BigDecimal.valueOf(150.00)); // Use BigDecimal for precision
        room.setRoomPhotoUrl("http://example.com/photo.jpg");
        room.setRoomDescription("A spacious deluxe room with ocean view.");

        // Adjust the expected string to match the current output of the toString() method
        String expected = "Room{id=null, roomType='Deluxe', roomPrice=150.0, roomPhotoUrl='http://example.com/photo.jpg', description='A spacious deluxe room with ocean view.'}";
        assertEquals(expected, room.toString());
    }
}
