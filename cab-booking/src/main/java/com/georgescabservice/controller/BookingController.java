package com.georgescabservice.controller;

import com.georgescabservice.entity.Booking;
import com.georgescabservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(savedBooking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/confirm-booking")
    public ResponseEntity<String> confirmBooking(@RequestParam double startLat, @RequestParam double startLng,
                                                 @RequestParam double endLat, @RequestParam double endLng,
                                                 @RequestParam double totalDistance, @RequestParam double fare) {
        Booking booking = new Booking();
        booking.setStartLat(startLat);
        booking.setStartLng(startLng);
        booking.setEndLat(endLat);
        booking.setEndLng(endLng);
        booking.setDistance(totalDistance);
        booking.setFare(fare);

        bookingRepository.save(booking);
        return ResponseEntity.ok("Booking confirmed!");
    }
}

