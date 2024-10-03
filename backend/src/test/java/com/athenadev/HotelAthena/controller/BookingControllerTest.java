package com.athenadev.HotelAthena.controller;

import com.athenadev.HotelAthena.dto.Response;
import com.athenadev.HotelAthena.entity.Booking;
import com.athenadev.HotelAthena.service.interfac.IBookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;

    @Mock
    private IBookingService bookingService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testSaveBooking() {
        // Arrange
        Long roomId = 1L;
        Long userId = 1L;
        Booking bookingRequest = new Booking(); // Initialize with necessary fields
        Response expectedResponse = new Response(); // Set up the expected response
        expectedResponse.setStatusCode(HttpStatus.CREATED.value());
        expectedResponse.setMessage("Booking successful");

        when(bookingService.saveBooking(anyLong(), anyLong(), any(Booking.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<Response> responseEntity = bookingController.saveBooking(roomId, userId, bookingRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Booking successful", responseEntity.getBody().getMessage());
    }

    @Test
    void testGetAllBookings() {
        // Arrange
        Response expectedResponse = new Response();
        expectedResponse.setStatusCode(HttpStatus.OK.value());
        expectedResponse.setMessage("All bookings retrieved successfully");

        when(bookingService.getAllBookings()).thenReturn(expectedResponse);

        // Act
        ResponseEntity<Response> responseEntity = bookingController.getAllBookings();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("All bookings retrieved successfully", responseEntity.getBody().getMessage());
    }

    @Test
    void testGetBookingsByConfirmationCode() {
        // Arrange
        String confirmationCode = "CONF123";
        Response expectedResponse = new Response();
        expectedResponse.setStatusCode(HttpStatus.OK.value());
        expectedResponse.setMessage("Booking found");

        when(bookingService.findBookingByConfirmationCode(confirmationCode)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<Response> responseEntity = bookingController.getBookingsByConfirmationCode(confirmationCode);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Booking found", responseEntity.getBody().getMessage());
    }

    @Test
    void testCancelBooking() {
        // Arrange
        Long bookingId = 1L;
        Response expectedResponse = new Response();
        expectedResponse.setStatusCode(HttpStatus.OK.value());
        expectedResponse.setMessage("Booking canceled");

        when(bookingService.cancelBooking(bookingId)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<Response> responseEntity = bookingController.cancelBooking(bookingId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Booking canceled", responseEntity.getBody().getMessage());
    }
}
