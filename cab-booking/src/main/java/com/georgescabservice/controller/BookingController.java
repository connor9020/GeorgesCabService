package com.georgescabservice.controller;

import com.georgescabservice.entity.Booking;
import com.georgescabservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/book-cab")
    public String bookCabForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "book-cab";
    }

    @PostMapping("/book-cab")
    public String createBooking(@ModelAttribute Booking booking, Model model) {
        Booking savedBooking = bookingRepository.save(booking);
        model.addAttribute("booking", savedBooking);
        return "booking-confirmation";
    }

    @GetMapping("/past-rides")
    public String getAllBookings(Model model) {
        List<Booking> bookings = bookingRepository.findAll();
        model.addAttribute("bookings", bookings);
        return "past-rides";
    }
}

