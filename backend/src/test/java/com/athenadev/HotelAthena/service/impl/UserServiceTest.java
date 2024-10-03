package com.athenadev.HotelAthena.service.impl;

import com.athenadev.HotelAthena.dto.Response;
import com.athenadev.HotelAthena.entity.User;
import com.athenadev.HotelAthena.repo.UserRepository;
import com.athenadev.HotelAthena.utils.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JWTUtils jwtUtils;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    public void setUp() {
        // No need to call MockitoAnnotations.openMocks(this) when using @ExtendWith
    }

    @Test
    public void testRegister_UserNotFound() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        Response response = userService.register(user);

        assertEquals(200, response.getStatusCode());
        assertNotNull(response.getUser());
        assertEquals("successful", response.getMessage());
    }


    @Test
    public void testRegister_UserAlreadyExists() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        Response response = userService.register(user);

        assertEquals(400, response.getStatusCode());
        assertEquals("test@example.com Already Exists", response.getMessage());
    }

}