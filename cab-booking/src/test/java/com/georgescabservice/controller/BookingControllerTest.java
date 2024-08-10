package com.georgescabservice.controller;

import com.georgescabservice.entity.Booking;
import com.georgescabservice.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingRepository bookingRepository;

    private Booking booking;

    @BeforeEach
    public void setup() {
        booking = new Booking();
        booking.setId(1L);
        booking.setStartLat(51.5074);
        booking.setStartLng(-0.1278);
        booking.setEndLat(48.8566);
        booking.setEndLng(2.3522);
        booking.setDistance(343.5);
        booking.setFare(50.0);
    }

    @Test
    public void testCreateBooking() throws Exception {
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        mockMvc.perform(post("/bookings")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"startLat\": 51.5074, \"startLng\": -0.1278, \"endLat\": 48.8566, \"endLng\": 2.3522, \"distance\": 343.5, \"fare\": 50.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.startLat").value(51.5074))
                .andExpect(jsonPath("$.startLng").value(-0.1278))
                .andExpect(jsonPath("$.endLat").value(48.8566))
                .andExpect(jsonPath("$.endLng").value(2.3522))
                .andExpect(jsonPath("$.distance").value(343.5))
                .andExpect(jsonPath("$.fare").value(50.0));
    }

    @Test
    public void testGetBooking() throws Exception {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(booking));

        mockMvc.perform(get("/bookings/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.startLat").value(51.5074))
                .andExpect(jsonPath("$.startLng").value(-0.1278))
                .andExpect(jsonPath("$.endLat").value(48.8566))
                .andExpect(jsonPath("$.endLng").value(2.3522))
                .andExpect(jsonPath("$.distance").value(343.5))
                .andExpect(jsonPath("$.fare").value(50.0));
    }

    @Test
    public void testGetBookingNotFound() throws Exception {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/bookings/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllBookings() throws Exception {
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(booking));

        mockMvc.perform(get("/bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].startLat").value(51.5074))
                .andExpect(jsonPath("$[0].startLng").value(-0.1278))
                .andExpect(jsonPath("$[0].endLat").value(48.8566))
                .andExpect(jsonPath("$[0].endLng").value(2.3522))
                .andExpect(jsonPath("$[0].distance").value(343.5))
                .andExpect(jsonPath("$[0].fare").value(50.0));
    }

    @Test
    public void testConfirmBooking() throws Exception {
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        mockMvc.perform(post("/bookings/confirm-booking")
                .param("startLat", "51.5074")
                .param("startLng", "-0.1278")
                .param("endLat", "48.8566")
                .param("endLng", "2.3522")
                .param("totalDistance", "343.5")
                .param("fare", "50.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("Booking confirmed!"));
    }
}
