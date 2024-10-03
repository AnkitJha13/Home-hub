package com.athenadev.HotelAthena.controller;

import com.athenadev.HotelAthena.dto.LoginRequest;
import com.athenadev.HotelAthena.dto.Response;
import com.athenadev.HotelAthena.entity.User;
import com.athenadev.HotelAthena.service.interfac.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private IUserService userService; // Mocking the service layer

    @InjectMocks
    private AuthController authController; // Injecting the mock service into the controller

    @BeforeEach
    void setUp() {
        // The setup is already done by the @ExtendWith(MockitoExtension.class), no need to call openMocks() explicitly.
    }

    // Test case for successful registration
    @Test
    void testRegisterSuccess() {
        // Mock user input
        User user = new User();
        user.setEmail("testuser@example.com");
        user.setPassword("password");

        // Mock the response from the service layer
        Response mockResponse = new Response();
        mockResponse.setMessage("User registered successfully");
        mockResponse.setStatusCode(HttpStatus.OK.value());

        // Simulate the behavior of userService.register()
        when(userService.register(user)).thenReturn(mockResponse);

        // Call the controller method
        ResponseEntity<Response> responseEntity = authController.register(user);

        // Verify the status and the body of the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode()); // Asserting the HTTP status
        assertNotNull(responseEntity.getBody()); // Ensuring the response body is not null
        assertEquals("User registered successfully", responseEntity.getBody().getMessage()); // Checking the response message

        // Verify that userService.register() was called exactly once
        verify(userService, times(1)).register(user);
    }

    // Test case for successful login
    @Test
    void testLoginSuccess() {
        // Mock login request
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("testuser@example.com");
        loginRequest.setPassword("password");

        // Mock the response from the service layer
        Response mockResponse = new Response();
        mockResponse.setMessage("Login successful");
        mockResponse.setStatusCode(HttpStatus.OK.value());

        // Simulate the behavior of userService.login()
        when(userService.login(loginRequest)).thenReturn(mockResponse);

        // Call the controller method
        ResponseEntity<Response> responseEntity = authController.login(loginRequest);

        // Verify the status and the body of the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Login successful", responseEntity.getBody().getMessage());

        // Verify that userService.login() was called exactly once
        verify(userService, times(1)).login(loginRequest);
    }

    // Test case for unsuccessful login
    @Test
    void testLoginFailure() {
        // Mock login request with incorrect credentials
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("wronguser@example.com");
        loginRequest.setPassword("wrongpassword");

        // Mock the response from the service layer
        Response mockResponse = new Response();
        mockResponse.setMessage("Invalid credentials");
        mockResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());

        // Simulate the behavior of userService.login() when login fails
        when(userService.login(loginRequest)).thenReturn(mockResponse);

        // Call the controller method
        ResponseEntity<Response> responseEntity = authController.login(loginRequest);

        // Verify the status and the body of the response
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode()); // Asserting 401 status
        assertNotNull(responseEntity.getBody());
        assertEquals("Invalid credentials", responseEntity.getBody().getMessage());

        // Verify that userService.login() was called exactly once
        verify(userService, times(1)).login(loginRequest);
    }
}
