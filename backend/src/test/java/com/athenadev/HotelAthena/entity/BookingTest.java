package com.athenadev.HotelAthena.entity;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    private Booking booking;

    @BeforeEach
    public void setup(){
        booking = new Booking();
        booking.setCheckInDate(LocalDate.now());
        booking.setCheckOutDate(LocalDate.now().plusDays(1));
    }

    @Test
    public void testSetAdultsUpdatesTotalGuests(){
        booking.setNumOfAdults(2);
        booking.setNumOfChildren(1);

        assertEquals(3, booking.getTotalNumOfGuest(), "Total number of guests should be 3");

    }

    @Test
    public void testSetChildrenUpdatesTotalGuests() {
        booking.setNumOfAdults(1); // Set number of adults to 1
        booking.setNumOfChildren(3); // Set number of children to 3

        assertEquals(4, booking.getTotalNumOfGuest(), "Total number of guests should be 4"); // Assert total guests
    }


    @Test
    public void testToString() {
        booking.setNumOfAdults(2); // Set number of adults
        booking.setNumOfChildren(1); // Set number of children
        booking.setBookingConfirmationCode("ABC123"); // Set booking confirmation code

        String expectedString = "Booking{id=null, checkInDate=" + booking.getCheckInDate() +
                ", checkOutDate=" + booking.getCheckOutDate() +
                ", numOfAdults=2, numOfChildren=1, totalNumOfGuest=3, bookingConfirmationCode='ABC123'}";

        assertEquals(expectedString, booking.toString(), "toString() should return the expected string representation"); // Assert string output
    }


    @Test
    public void testSettersWithValidation() {
        assertThrows(IllegalArgumentException.class, () -> booking.setNumOfAdults(-1), "Should throw exception for negative adults"); // Check negative adults
        assertThrows(IllegalArgumentException.class, () -> booking.setNumOfChildren(-1), "Should throw exception for negative children"); // Check negative children
    }



}