package com.athenadev.HotelAthena.service.interfac;

import com.athenadev.HotelAthena.dto.LoginRequest;
import com.athenadev.HotelAthena.dto.Response;
import com.athenadev.HotelAthena.entity.User;

public interface IUserService {

    Response register(User user);
    Response login(LoginRequest loginRequest);
    Response getAllUsers();
    Response getUSerBookingHistory(String userId);
    Response deleteUser(String userId);
    Response getUserById(String userId);
    Response getMyInfo(String email);
}