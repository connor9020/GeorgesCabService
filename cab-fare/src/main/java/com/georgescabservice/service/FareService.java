package com.georgescabservice.service;

import com.georgescabservice.entity.Fare;
import com.georgescabservice.repository.FareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FareService {

    @Autowired
    private FareRepository fareRepository;

    public List<Fare> getAllFares() {
        return fareRepository.findAll();
    }

    public Fare saveFare(Fare fare) {
        return fareRepository.save(fare);
    }
}
