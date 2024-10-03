package com.athenadev.HotelAthena.utils;

import com.athenadev.HotelAthena.dto.BookingDTO;
import com.athenadev.HotelAthena.dto.RoomDTO;
import com.athenadev.HotelAthena.dto.UserDTO;
import com.athenadev.HotelAthena.entity.Booking;
import com.athenadev.HotelAthena.entity.Room;
import com.athenadev.HotelAthena.entity.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {

    @Test
    public void testMapRoomEntityToRoomDTO() {
        // Create Room entity
        Room room = new Room();
        room.setId(1L);
        room.setRoomType("Deluxe");
        room.setRoomPrice(BigDecimal.valueOf(200.0));  // Fix: Using BigDecimal.valueOf for double
        room.setRoomPhotoUrl("photoUrl");
        room.setRoomDescription("Spacious room");

        // Map to RoomDTO
        RoomDTO roomDTO = Utils.mapRoomEntityToRoomDTO(room);

        // Assertions
        assertEquals(room.getId(), roomDTO.getId());
        assertEquals(room.getRoomType(), roomDTO.getRoomType());
        assertEquals(room.getRoomPrice(), roomDTO.getRoomPrice());
    }

    @Test
    public void testMapBookingEntityToBookingDTO() {
        // Create Booking entity
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setCheckInDate(LocalDate.parse("2024-01-01"));  // Fix: Using LocalDate.parse
        booking.setCheckOutDate(LocalDate.parse("2024-01-05")); // Fix: Using LocalDate.parse
        booking.setNumOfChildren(2);
        booking.setNumOfAdults(2);
        booking.setTotalNumOfGuest(4);
        booking.setBookingConfirmationCode("ABC123");

        // Map to BookingDTO
        BookingDTO bookingDTO = Utils.mapBookingEntityToBookingDTO(booking);

        // Assertions
        assertEquals(booking.getId(), bookingDTO.getId());
        assertEquals(booking.getCheckInDate(), bookingDTO.getCheckInDate());
        assertEquals(booking.getCheckOutDate(), bookingDTO.getCheckOutDate());
    }

    @Test
    public void testMapUserEntityToUserDTO() {
        // Create User entity
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPhoneNumber("1234567890");
        user.setRole("Admin");

        // Map to UserDTO
        UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);

        // Assertions
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getEmail(), userDTO.getEmail());
    }

    @Test
    public void testMapRoomEntityToRoomDTOWithPriceAndDate() {
        // Create Room entity with price and dates
        Room room = new Room();
        room.setId(2L);
        room.setRoomType("Standard");
        room.setRoomPrice(BigDecimal.valueOf(150.0));  // Fix: BigDecimal for price
        room.setRoomPhotoUrl("photoUrl2");
        room.setRoomDescription("Comfortable room");

        // Map to RoomDTO
        RoomDTO roomDTO = Utils.mapRoomEntityToRoomDTO(room);

        // Assertions
        assertEquals(room.getId(), roomDTO.getId());
        assertEquals(room.getRoomType(), roomDTO.getRoomType());
        assertEquals(room.getRoomPrice(), roomDTO.getRoomPrice());
    }
}
