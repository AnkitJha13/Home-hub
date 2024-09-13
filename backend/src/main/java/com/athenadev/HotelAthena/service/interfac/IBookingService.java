package com.athenadev.HotelAthena.service.interfac;

import com.athenadev.HotelAthena.dto.Response;
import com.athenadev.HotelAthena.entity.Booking;

public interface IBookingService {

    Response saveBooking(Long rooId, Long userId, Booking bookingRequest);
    Response findBookingByConfirmationCode(String confirmationCode);
    Response getAllBookings();
    Response cancelBooking(Long bookingId);
}