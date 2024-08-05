package com.georgescabservice.controller;

import com.georgescabservice.entity.Fare;
import com.georgescabservice.service.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fares")
public class FareController {

    @Autowired
    private FareService fareService;

    @GetMapping
    public List<Fare> getAllFares() {
        return fareService.getAllFares();
    }

    @PostMapping
    public Fare saveFare(@RequestBody Fare fare) {
        return fareService.saveFare(fare);
    }
}
