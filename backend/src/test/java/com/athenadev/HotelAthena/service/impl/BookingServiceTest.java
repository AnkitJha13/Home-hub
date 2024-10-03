package com.athenadev.HotelAthena.service.impl;

import com.athenadev.HotelAthena.dto.Response;
import com.athenadev.HotelAthena.entity.Booking;
import com.athenadev.HotelAthena.entity.Room;
import com.athenadev.HotelAthena.entity.User;
import com.athenadev.HotelAthena.repo.BookingRepository;
import com.athenadev.HotelAthena.repo.RoomRepository;
import com.athenadev.HotelAthena.repo.UserRepository;
import com.athenadev.HotelAthena.service.interfac.IRoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private IRoomService roomService;

    private static final Long TEST_ROOM_ID = 1L;
    private static final Long TEST_USER_ID = 1L;

    private Room room;
    private User user;
    private Booking booking;

    @BeforeEach
    public void setup() {
        room = createTestRoom(TEST_ROOM_ID);
        user = createTestUser(TEST_USER_ID);
        booking = createTestBooking();
    }

    private Room createTestRoom(Long roomId) {
        Room room = new Room();
        room.setId(roomId);
        room.setRoomType("Deluxe");
        room.setRoomPrice(BigDecimal.valueOf(200.00));
        room.setRoomPhotoUrl("http://example.com/photo.jpg");
        room.setRoomDescription("A luxurious deluxe room.");
        room.setBookings(new ArrayList<>());
        return room;
    }

    private User createTestUser(Long userId) {
        User user = new User();
        user.setId(userId);
        return user;
    }

    private Booking createTestBooking() {
        Booking booking = new Booking();
        booking.setCheckInDate(LocalDate.now());
        booking.setCheckOutDate(LocalDate.now().plusDays(1));
        booking.setRoom(room);
        booking.setUser(user);
        return booking;
    }

    @Test
    public void testSaveBooking_Success() {
        when(roomRepository.findById(TEST_ROOM_ID)).thenReturn(Optional.of(room));
        when(userRepository.findById(TEST_USER_ID)).thenReturn(Optional.of(user));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        Response response = bookingService.saveBooking(TEST_ROOM_ID, TEST_USER_ID, booking);

        assertEquals(200, response.getStatusCode(), "Expected status code to be 200");
        assertEquals("successful", response.getMessage());
        assertNotNull(response.getBookingConfirmationCode(), "Expected booking confirmation code to be present");
    }

    @Test
    public void testSaveBooking_RoomNotFound() {
        when(roomRepository.findById(TEST_ROOM_ID)).thenReturn(Optional.empty());

        Response response = bookingService.saveBooking(TEST_ROOM_ID, TEST_USER_ID, booking);

        assertEquals(404, response.getStatusCode(), "Expected status code to be 404");
        assertEquals("Room Not Found", response.getMessage());
    }

    @Test
    public void testFindBookingByConfirmationCode_Success() {
        booking.setBookingConfirmationCode("ABC123");
        when(bookingRepository.findByBookingConfirmationCode("ABC123")).thenReturn(Optional.of(booking));

        Response response = bookingService.findBookingByConfirmationCode("ABC123");

        assertEquals(200, response.getStatusCode());
        assertEquals("successful", response.getMessage());
        assertNotNull(response.getBooking(), "Expected booking to be present");
    }

    @Test
    public void testFindBookingByConfirmationCode_NotFound() {
        when(bookingRepository.findByBookingConfirmationCode("ABC123")).thenReturn(Optional.empty());

        Response response = bookingService.findBookingByConfirmationCode("ABC123");

        assertEquals(404, response.getStatusCode());
        assertEquals("Booking Not Found", response.getMessage());
    }

    @Test
    public void testCancelBooking_Success() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

        Response response = bookingService.cancelBooking(1L);

        assertEquals(200, response.getStatusCode());
        assertEquals("successful", response.getMessage());
        verify(bookingRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testCancelBooking_NotFound() {
        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

        Response response = bookingService.cancelBooking(1L);

        assertEquals(404, response.getStatusCode());
        assertEquals("Booking Not Found", response.getMessage());
    }
}
