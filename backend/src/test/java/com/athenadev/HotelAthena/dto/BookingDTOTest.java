package com.athenadev.HotelAthena.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingDTOTest {

    private BookingDTO bookingDTO;
    private RoomDTO roomDTO;

    @BeforeEach
    void setUp() {
        // Setting up BookingDTO with sample data
        bookingDTO = new BookingDTO();
        bookingDTO.setId(1L);
        bookingDTO.setCheckInDate(LocalDate.of(2024, 9, 1));
        bookingDTO.setCheckOutDate(LocalDate.of(2024, 9, 5));
        bookingDTO.setNumOfAdults(2);
        bookingDTO.setNumOfChildren(1);
        bookingDTO.setTotalNumOfGuest(3);
        bookingDTO.setBookingConfirmationCode("ABC123");

        // Setting up UserDTO
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("test@example.com");
        userDTO.setName("Test User");
        bookingDTO.setUser(userDTO);

        // Setting up RoomDTO with sample data
        roomDTO = new RoomDTO();
        roomDTO.setId(101L);
        roomDTO.setRoomType("Deluxe");
        roomDTO.setRoomPrice(BigDecimal.valueOf(200.50));
        roomDTO.setRoomPhotoUrl("http://example.com/room101.jpg");
        roomDTO.setRoomDescription("A spacious deluxe room with ocean view");

        // Linking RoomDTO to BookingDTO
        bookingDTO.setRoom(roomDTO);
    }

    @Test
    void testGetId() {
        assertEquals(1L, bookingDTO.getId());
    }

    @Test
    void testGetCheckInDate() {
        assertEquals(LocalDate.of(2024, 9, 1), bookingDTO.getCheckInDate());
    }

    @Test
    void testGetCheckOutDate() {
        assertEquals(LocalDate.of(2024, 9, 5), bookingDTO.getCheckOutDate());
    }

    @Test
    void testGetNumOfAdults() {
        assertEquals(2, bookingDTO.getNumOfAdults());
    }

    @Test
    void testGetNumOfChildren() {
        assertEquals(1, bookingDTO.getNumOfChildren());
    }

    @Test
    void testGetTotalNumOfGuest() {
        assertEquals(3, bookingDTO.getTotalNumOfGuest());
    }

    @Test
    void testGetBookingConfirmationCode() {
        assertEquals("ABC123", bookingDTO.getBookingConfirmationCode());
    }

    @Test
    void testGetUser() {
        UserDTO user = bookingDTO.getUser();
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("Test User", user.getName());
    }

    @Test
    void testGetRoom() {
        RoomDTO room = bookingDTO.getRoom();
        assertNotNull(room);
        assertEquals(101L, room.getId());
        assertEquals("Deluxe", room.getRoomType());
        assertEquals(BigDecimal.valueOf(200.50), room.getRoomPrice());
        assertEquals("http://example.com/room101.jpg", room.getRoomPhotoUrl());
        assertEquals("A spacious deluxe room with ocean view", room.getRoomDescription());
    }

    @Test
    void testSetRoom() {
        RoomDTO newRoom = new RoomDTO();
        newRoom.setId(102L);
        newRoom.setRoomType("Suite");
        newRoom.setRoomPrice(BigDecimal.valueOf(350.00));
        newRoom.setRoomPhotoUrl("http://example.com/room102.jpg");
        newRoom.setRoomDescription("A luxurious suite with a mountain view");

        bookingDTO.setRoom(newRoom);

        assertEquals(102L, bookingDTO.getRoom().getId());
        assertEquals("Suite", bookingDTO.getRoom().getRoomType());
        assertEquals(BigDecimal.valueOf(350.00), bookingDTO.getRoom().getRoomPrice());
        assertEquals("http://example.com/room102.jpg", bookingDTO.getRoom().getRoomPhotoUrl());
        assertEquals("A luxurious suite with a mountain view", bookingDTO.getRoom().getRoomDescription());
    }

    @Test
    void testSetRoomWithBookings() {
        // Setting up RoomDTO with Bookings
        RoomDTO newRoomWithBookings = new RoomDTO();
        newRoomWithBookings.setId(103L);
        newRoomWithBookings.setRoomType("Executive Suite");
        newRoomWithBookings.setRoomPrice(BigDecimal.valueOf(500.00));
        newRoomWithBookings.setRoomPhotoUrl("http://example.com/room103.jpg");
        newRoomWithBookings.setRoomDescription("An executive suite with premium amenities");

        // Simulating bookings
        BookingDTO booking1 = new BookingDTO();
        booking1.setId(1001L);
        booking1.setBookingConfirmationCode("CONFIRM123");

        BookingDTO booking2 = new BookingDTO();
        booking2.setId(1002L);
        booking2.setBookingConfirmationCode("CONFIRM456");

        newRoomWithBookings.setBookings(List.of(booking1, booking2));

        bookingDTO.setRoom(newRoomWithBookings);

        assertNotNull(bookingDTO.getRoom());
        assertEquals(103L, bookingDTO.getRoom().getId());
        assertEquals(2, bookingDTO.getRoom().getBookings().size());
        assertEquals("CONFIRM123", bookingDTO.getRoom().getBookings().get(0).getBookingConfirmationCode());
        assertEquals("CONFIRM456", bookingDTO.getRoom().getBookings().get(1).getBookingConfirmationCode());
    }

    @Test
    void testSetUser() {
        UserDTO newUser = new UserDTO();
        newUser.setId(2L);
        newUser.setEmail("new@example.com");
        newUser.setName("New User");

        bookingDTO.setUser(newUser);

        assertEquals(2L, bookingDTO.getUser().getId());
        assertEquals("new@example.com", bookingDTO.getUser().getEmail());
        assertEquals("New User", bookingDTO.getUser().getName());
    }
}
