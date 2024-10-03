package com.athenadev.HotelAthena.service.impl;

import com.athenadev.HotelAthena.dto.Response;
import com.athenadev.HotelAthena.dto.RoomDTO;
import com.athenadev.HotelAthena.entity.Room;
import com.athenadev.HotelAthena.exception.OurException;
import com.athenadev.HotelAthena.repo.BookingRepository;
import com.athenadev.HotelAthena.repo.RoomRepository;
import com.athenadev.HotelAthena.service.AwsS3Service;
import com.athenadev.HotelAthena.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private AwsS3Service awsS3Service;

    @Mock
    private MultipartFile photo;

    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room();
        room.setId(1L);
        room.setRoomType("Deluxe");
        room.setRoomPrice(BigDecimal.valueOf(200.00));
        room.setRoomDescription("Spacious room with ocean view.");
        room.setRoomPhotoUrl("http://example.com/photo.jpg");
    }

    @Test
    void addNewRoom_ShouldReturnSuccessResponse() throws Exception {
        when(awsS3Service.saveImageToS3(photo)).thenReturn("http://example.com/photo.jpg");
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        Response response = roomService.addNewRoom(photo, "Deluxe", BigDecimal.valueOf(200.00), "Spacious room with ocean view.");

        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getRoom());
        assertEquals("successful", response.getMessage());
    }

    @Test
    void getAllRoomTypes_ShouldReturnDistinctRoomTypes() {
        when(roomRepository.findDistinctRoomTypes()).thenReturn(List.of("Deluxe", "Standard"));

        List<String> roomTypes = roomService.getAllRoomTypes();

        assertEquals(2, roomTypes.size());
        assertTrue(roomTypes.contains("Deluxe"));
    }



    @Test
    void deleteRoom_ShouldReturnSuccessResponse() {
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        Response response = roomService.deleteRoom(1L);

        assertEquals(200, response.getStatusCode());
        assertEquals("successful", response.getMessage());
        verify(roomRepository, times(1)).deleteById(1L);
    }



    @Test
    void updateRoom_ShouldReturnUpdatedRoomResponse() throws Exception {
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(awsS3Service.saveImageToS3(photo)).thenReturn("http://example.com/photo.jpg");
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        Response response = roomService.updateRoom(1L, "Updated Description", "Suite", BigDecimal.valueOf(250.00), photo);

        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getRoom());
        assertEquals("successful", response.getMessage());
    }

    @Test
    void updateRoom_ShouldThrowExceptionIfRoomNotFound() {
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        Response response = roomService.updateRoom(1L, "Updated Description", "Suite", BigDecimal.valueOf(250.00), photo);

        assertEquals(404, response.getStatusCode());
        assertEquals("Room Not Found", response.getMessage());
    }


}
