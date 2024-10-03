package com.athenadev.HotelAthena.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomDTOTest {

    private RoomDTO roomDTO;

    @BeforeEach
    void setUp() {
        // Setting up RoomDTO with sample data
        roomDTO = new RoomDTO();
        roomDTO.setId(101L);
        roomDTO.setRoomType("Deluxe");
        roomDTO.setRoomPrice(BigDecimal.valueOf(200.50));
        roomDTO.setRoomPhotoUrl("http://example.com/room101.jpg");
        roomDTO.setRoomDescription("A spacious deluxe room with ocean view");

        // Simulating bookings for this room
        BookingDTO booking1 = new BookingDTO();
        booking1.setId(1001L);
        booking1.setBookingConfirmationCode("CONFIRM123");

        BookingDTO booking2 = new BookingDTO();
        booking2.setId(1002L);
        booking2.setBookingConfirmationCode("CONFIRM456");

        roomDTO.setBookings(List.of(booking1, booking2));
    }

    @Test
    void testGetId() {
        assertEquals(101L, roomDTO.getId());
    }

    @Test
    void testGetRoomType() {
        assertEquals("Deluxe", roomDTO.getRoomType());
    }

    @Test
    void testGetRoomPrice() {
        assertEquals(BigDecimal.valueOf(200.50), roomDTO.getRoomPrice());
    }

    @Test
    void testGetRoomPhotoUrl() {
        assertEquals("http://example.com/room101.jpg", roomDTO.getRoomPhotoUrl());
    }

    @Test
    void testGetRoomDescription() {
        assertEquals("A spacious deluxe room with ocean view", roomDTO.getRoomDescription());
    }

    @Test
    void testGetBookings() {
        List<BookingDTO> bookings = roomDTO.getBookings();
        assertNotNull(bookings);
        assertEquals(2, bookings.size());
        assertEquals(1001L, bookings.get(0).getId());
        assertEquals("CONFIRM123", bookings.get(0).getBookingConfirmationCode());
        assertEquals(1002L, bookings.get(1).getId());
        assertEquals("CONFIRM456", bookings.get(1).getBookingConfirmationCode());
    }

    @Test
    void testSetId() {
        roomDTO.setId(102L);
        assertEquals(102L, roomDTO.getId());
    }

    @Test
    void testSetRoomType() {
        roomDTO.setRoomType("Suite");
        assertEquals("Suite", roomDTO.getRoomType());
    }

    @Test
    void testSetRoomPrice() {
        roomDTO.setRoomPrice(BigDecimal.valueOf(350.00));
        assertEquals(BigDecimal.valueOf(350.00), roomDTO.getRoomPrice());
    }

    @Test
    void testSetRoomPhotoUrl() {
        roomDTO.setRoomPhotoUrl("http://example.com/room102.jpg");
        assertEquals("http://example.com/room102.jpg", roomDTO.getRoomPhotoUrl());
    }

    @Test
    void testSetRoomDescription() {
        roomDTO.setRoomDescription("A luxurious suite with a mountain view");
        assertEquals("A luxurious suite with a mountain view", roomDTO.getRoomDescription());
    }

    @Test
    void testSetBookings() {
        BookingDTO newBooking = new BookingDTO();
        newBooking.setId(1003L);
        newBooking.setBookingConfirmationCode("CONFIRM789");

        roomDTO.setBookings(List.of(newBooking));

        assertEquals(1, roomDTO.getBookings().size());
        assertEquals(1003L, roomDTO.getBookings().get(0).getId());
        assertEquals("CONFIRM789", roomDTO.getBookings().get(0).getBookingConfirmationCode());
    }
}
