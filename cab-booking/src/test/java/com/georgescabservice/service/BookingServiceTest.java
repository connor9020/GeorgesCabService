package com.georgescabservice.service;

import com.georgescabservice.entity.Booking;
import com.georgescabservice.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * This test verifies that the getAllBookings() method of the BookingService
     * correctly returns a list of all bookings from the BookingRepository.
     * It mocks the BookingRepository's findAll() method to return a predefined list of bookings
     * and then asserts that the service method returns this list as expected.
     */
    @Test
    void testGetAllBookings() {
        // Arrange
        Booking booking1 = new Booking();
        booking1.setId(1L);
        booking1.setStartLat(10.0);
        booking1.setStartLng(10.0);
        booking1.setEndLat(20.0);
        booking1.setEndLng(20.0);
        booking1.setDistance(15.0);
        booking1.setFare(50.0);

        Booking booking2 = new Booking();
        booking2.setId(2L);
        booking2.setStartLat(30.0);
        booking2.setStartLng(30.0);
        booking2.setEndLat(40.0);
        booking2.setEndLng(40.0);
        booking2.setDistance(25.0);
        booking2.setFare(100.0);

        when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking1, booking2));

        // Act
        List<Booking> result = bookingService.getAllBookings();

        // Assert
        assertEquals(2, result.size());
        verify(bookingRepository, times(1)).findAll();
    }

    /**
     * This test verifies that the saveBooking() method of the BookingService
     * correctly saves a booking by calling the save() method of the BookingRepository.
     * It mocks the BookingRepository's save() method to return the same booking object that was passed in,
     * and then asserts that the service method returns the saved booking as expected.
     */
    @Test
    void testSaveBooking() {
        // Arrange
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setStartLat(10.0);
        booking.setStartLng(10.0);
        booking.setEndLat(20.0);
        booking.setEndLng(20.0);
        booking.setDistance(15.0);
        booking.setFare(50.0);

        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        // Act
        Booking result = bookingService.saveBooking(booking);

        // Assert
        assertEquals(booking, result);
        verify(bookingRepository, times(1)).save(booking);
    }
}
