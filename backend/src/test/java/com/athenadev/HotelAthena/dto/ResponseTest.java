package com.athenadev.HotelAthena.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {

    private Response response;

    @BeforeEach
    void setUp() {
        response = new Response();  // Initialize Response object before each test
    }

    @Test
    void testStatusCodeGetterAndSetter() {
        // Arrange: Set statusCode
        int statusCode = 200;
        response.setStatusCode(statusCode);

        // Act & Assert: Check if the getter returns the correct value
        assertEquals(statusCode, response.getStatusCode());
    }

    @Test
    void testMessageGetterAndSetter() {
        // Arrange: Set message
        String message = "Success";
        response.setMessage(message);

        // Act & Assert: Check if the getter returns the correct value
        assertEquals(message, response.getMessage());
    }

    @Test
    void testTokenGetterAndSetter() {
        // Arrange: Set token
        String token = "someToken123";
        response.setToken(token);

        // Act & Assert: Check if the getter returns the correct value
        assertEquals(token, response.getToken());
    }

    @Test
    void testUserGetterAndSetter() {
        // Arrange: Create a UserDTO and set it
        UserDTO user = new UserDTO();
        user.setEmail("test@example.com");
        response.setUser(user);

        // Act & Assert: Check if the getter returns the correct value
        assertNotNull(response.getUser());
        assertEquals("test@example.com", response.getUser().getEmail());
    }

    @Test
    void testRoomGetterAndSetter() {
        // Arrange: Create a RoomDTO and set it
        RoomDTO room = new RoomDTO();
        room.setRoomType("Deluxe");
        response.setRoom(room);

        // Act & Assert: Check if the getter returns the correct value
        assertNotNull(response.getRoom());
        assertEquals("Deluxe", response.getRoom().getRoomType());
    }

    @Test
    void testBookingGetterAndSetter() {
        // Arrange: Create a BookingDTO and set it
        BookingDTO booking = new BookingDTO();
        booking.setBookingConfirmationCode("CONF123");
        response.setBooking(booking);

        // Act & Assert: Check if the getter returns the correct value
        assertNotNull(response.getBooking());
        assertEquals("CONF123", response.getBooking().getBookingConfirmationCode());
    }

    @Test
    void testUserListGetterAndSetter() {
        // Arrange: Create a user list and set it
        List<UserDTO> userList = new ArrayList<>();
        userList.add(new UserDTO());
        response.setUserList(userList);

        // Act & Assert: Check if the getter returns the correct value
        assertNotNull(response.getUserList());
        assertEquals(1, response.getUserList().size());
    }

    @Test
    void testRoomListGetterAndSetter() {
        // Arrange: Create a room list and set it
        List<RoomDTO> roomList = new ArrayList<>();
        roomList.add(new RoomDTO());
        response.setRoomList(roomList);

        // Act & Assert: Check if the getter returns the correct value
        assertNotNull(response.getRoomList());
        assertEquals(1, response.getRoomList().size());
    }

    @Test
    void testBookingListGetterAndSetter() {
        // Arrange: Create a booking list and set it
        List<BookingDTO> bookingList = new ArrayList<>();
        bookingList.add(new BookingDTO());
        response.setBookingList(bookingList);

        // Act & Assert: Check if the getter returns the correct value
        assertNotNull(response.getBookingList());
        assertEquals(1, response.getBookingList().size());
    }
}
