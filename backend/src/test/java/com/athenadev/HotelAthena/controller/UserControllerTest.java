package com.athenadev.HotelAthena.controller;

import com.athenadev.HotelAthena.dto.Response;
import com.athenadev.HotelAthena.service.interfac.IUserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private IUserService userService;


    private Response mockResponse;

    @BeforeEach
    public void setUp() {
        mockResponse = new Response();
        mockResponse.setStatusCode(HttpStatus.OK.value());
        mockResponse.setMessage("Success");
    }

    @Test
    public void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(mockResponse);

        ResponseEntity<Response> responseEntity = userController.getAllUsers();

        verify(userService).getAllUsers();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }

    @Test
    public void testGetUserById() {
        String userId = "123";
        when(userService.getUserById(userId)).thenReturn(mockResponse);

        ResponseEntity<Response> responseEntity = userController.getUserById(userId);

        verify(userService).getUserById(userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }

    @Test
    public void testDeleteUser() {
        String userId = "123";
        when(userService.deleteUser(userId)).thenReturn(mockResponse);

        ResponseEntity<Response> responseEntity = userController.deleteUser(userId);

        verify(userService).deleteUser(userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }



    @Test
    public void testGetUserBookingHistory() {
        String userId = "123";
        when(userService.getUSerBookingHistory(userId)).thenReturn(mockResponse);

        ResponseEntity<Response> responseEntity = userController.getUSerBookingHistory(userId);

        verify(userService).getUSerBookingHistory(userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }
}
