package com.georgescabservice.controller;

import com.georgescabservice.entity.Fare;
import com.georgescabservice.repository.FareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fares")
public class FareController {

    @Autowired
    private FareRepository fareRepository;

    @PostMapping
    public ResponseEntity<Fare> createFare(@RequestBody Fare fare) {
        Fare savedFare = fareRepository.save(fare);
        return ResponseEntity.ok(savedFare);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fare> getFare(@PathVariable Long id) {
        Optional<Fare> fare = fareRepository.findById(id);
        return fare.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Fare>> getAllFares() {
        List<Fare> fares = fareRepository.findAll();
        return ResponseEntity.ok(fares);
    }
}
