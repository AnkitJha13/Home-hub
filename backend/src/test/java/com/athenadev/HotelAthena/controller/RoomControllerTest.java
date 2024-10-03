package com.athenadev.HotelAthena.controller;

import com.athenadev.HotelAthena.dto.Response;
import com.athenadev.HotelAthena.service.interfac.IBookingService;
import com.athenadev.HotelAthena.service.interfac.IRoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomControllerTest {

    @InjectMocks
    private RoomController roomController;

    @Mock
    private IRoomService roomService;

    @Mock
    private IBookingService bookingService;

    @Mock
    private MultipartFile photo;

    @Test
    void testAddNewRoom() {
        // Arrange
        Response expectedResponse = new Response();
        expectedResponse.setStatusCode(HttpStatus.CREATED.value());
        expectedResponse.setMessage("Room added successfully");

        when(roomService.addNewRoom(any(MultipartFile.class), any(String.class), any(BigDecimal.class), any(String.class)))
                .thenReturn(expectedResponse);

        // Act
        ResponseEntity<Response> responseEntity = roomController.addNewRoom(photo, "Deluxe", BigDecimal.valueOf(150.00), "A beautiful deluxe room");

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Room added successfully", responseEntity.getBody().getMessage());
    }

    @Test
    void testGetAllRooms() {
        // Arrange
        Response expectedResponse = new Response();
        expectedResponse.setStatusCode(HttpStatus.OK.value());
        expectedResponse.setMessage("All rooms retrieved successfully");

        when(roomService.getAllRooms()).thenReturn(expectedResponse);

        // Act
        ResponseEntity<Response> responseEntity = roomController.getAllRooms();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("All rooms retrieved successfully", responseEntity.getBody().getMessage());
    }

    @Test
    void testGetRoomByID() {
        // Arrange
        Long roomId = 1L;
        Response expectedResponse = new Response();
        expectedResponse.setStatusCode(HttpStatus.OK.value());
        expectedResponse.setMessage("Room found");

        when(roomService.getRoomById(roomId)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<Response> responseEntity = roomController.getRoomByID(roomId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Room found", responseEntity.getBody().getMessage());
    }

    @Test
    void testGetAvailableRooms() {
        // Arrange
        Response expectedResponse = new Response();
        expectedResponse.setStatusCode(HttpStatus.OK.value());
        expectedResponse.setMessage("Available rooms retrieved successfully");

        when(roomService.getAllAvailableRooms()).thenReturn(expectedResponse);

        // Act
        ResponseEntity<Response> responseEntity = roomController.getAvailableRooms();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Available rooms retrieved successfully", responseEntity.getBody().getMessage());
    }

    @Test
    void testGetAvailableRoomsByDateAndType() {
        // Arrange
        LocalDate checkInDate = LocalDate.now();
        LocalDate checkOutDate = LocalDate.now().plusDays(1);
        String roomType = "Deluxe";
        Response expectedResponse = new Response();
        expectedResponse.setStatusCode(HttpStatus.OK.value());
        expectedResponse.setMessage("Available rooms retrieved successfully");

        when(roomService.getAvailableRoomsByDateAndType(checkInDate, checkOutDate, roomType)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<Response> responseEntity = roomController.getAvailableRoomsByDateAndType(checkInDate, checkOutDate, roomType);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Available rooms retrieved successfully", responseEntity.getBody().getMessage());
    }

    @Test
    void testUpdateRoom() {
        // Arrange
        Long roomId = 1L;
        Response expectedResponse = new Response();
        expectedResponse.setStatusCode(HttpStatus.OK.value());
        expectedResponse.setMessage("Room updated successfully");

        when(roomService.updateRoom(anyLong(), any(String.class), any(String.class), any(BigDecimal.class), any(MultipartFile.class)))
                .thenReturn(expectedResponse);

        // Act
        ResponseEntity<Response> responseEntity = roomController.updateRoom(roomId, photo, "Suite", BigDecimal.valueOf(200.00), "A luxurious suite");

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Room updated successfully", responseEntity.getBody().getMessage());
    }

    @Test
    void testDeleteRoom() {
        // Arrange
        Long roomId = 1L;
        Response expectedResponse = new Response();
        expectedResponse.setStatusCode(HttpStatus.OK.value());
        expectedResponse.setMessage("Room deleted successfully");

        when(roomService.deleteRoom(roomId)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<Response> responseEntity = roomController.deleteRoom(roomId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Room deleted successfully", responseEntity.getBody().getMessage());
    }
}
