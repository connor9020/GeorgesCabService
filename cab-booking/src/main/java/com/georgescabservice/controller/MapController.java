package com.georgescabservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class MapController {

    private static final String ORS_API_KEY = "5b3ce3597851110001cf62489159f6f137e844d7a1060febbb4a1d79";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/map")
    public String showMap() {
        return "map";
    }

    @GetMapping("/route")
    public String calculateRoute(@RequestParam double startLat, @RequestParam double startLng,
                                 @RequestParam double endLat, @RequestParam double endLng, Model model) {
        // Fetch route data from ORS API
        String url = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=" + ORS_API_KEY
                + "&start=" + startLng + "," + startLat + "&end=" + endLng + "," + endLat;
        String routeData = restTemplate.getForObject(url, String.class);

        // Fetch fare rates from the cab-fare microservice
        String fareUrl = "http://localhost:8080/fares"; 
        String fares = restTemplate.getForObject(fareUrl, String.class);

        model.addAttribute("routeData", routeData);
        model.addAttribute("startLat", startLat);
        model.addAttribute("startLng", startLng);
        model.addAttribute("endLat", endLat);
        model.addAttribute("endLng", endLng);
        model.addAttribute("fares", fares);
        return "route";
    }
}
