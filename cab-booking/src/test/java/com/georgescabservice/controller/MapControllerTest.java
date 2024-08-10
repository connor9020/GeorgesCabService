package com.georgescabservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class MapControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @InjectMocks
    private MapController mapController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowMap() throws Exception {
        mockMvc.perform(get("/map"))
                .andExpect(status().isOk())
                .andExpect(view().name("map"));
    }

    @Test
    public void testCalculateRoute() throws Exception {
        // Mock responses for the RestTemplate calls
        String mockedRouteData = "{ \"route\": \"mockedRouteData\" }";
        String mockedFareData = "{ \"fares\": \"mockedFareData\" }";

        String routeUrl = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=5b3ce3597851110001cf62489159f6f137e844d7a1060febbb4a1d79&start=-74.0060,40.7128&end=-73.935242,40.730610";
        String fareUrl = "http://localhost:8080/fares";

        when(restTemplate.getForObject(routeUrl, String.class)).thenReturn(mockedRouteData);
        when(restTemplate.getForObject(fareUrl, String.class)).thenReturn(mockedFareData);

        mockMvc.perform(get("/route")
                .param("startLat", "40.7128")
                .param("startLng", "-74.0060")
                .param("endLat", "40.730610")
                .param("endLng", "-73.935242"))
                .andExpect(status().isOk())
                .andExpect(view().name("route"))
                //.andExpect(model().attributeExists("routeData"))
                .andExpect(model().attributeExists("startLat"))
                .andExpect(model().attributeExists("startLng"))
                .andExpect(model().attributeExists("endLat"))
                .andExpect(model().attributeExists("endLng"))
                .andExpect(model().attributeExists("fares"));
    }

}
