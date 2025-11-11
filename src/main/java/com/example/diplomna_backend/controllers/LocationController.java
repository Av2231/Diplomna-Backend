package com.example.diplomna_backend.controllers;

import com.example.diplomna_backend.dto.*;
import com.example.diplomna_backend.interfaces.LocationService;
import com.example.diplomna_backend.model.Location;
import com.example.diplomna_backend.model.User;
import com.example.diplomna_backend.repository.LocationRepository;
import com.example.diplomna_backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> addLocation(@RequestBody AddLocationRequest request) {
        return locationService.addLocation(request);
    }

    @PostMapping("/select_locations")
    public ResponseEntity<?> checkIfLocationExists(@RequestBody CheckLocationRequest request) {
        return locationService.checkIfLocationExists(request);
    }

    @PostMapping("/location_average_rating")
    public ResponseEntity<?> getAverageRatingForLocation(@RequestBody LocationIdRequest request) {
        return locationService.getAverageRatingForLocation(request);
    }

    @PostMapping("/find_locations")
    public ResponseEntity<?> findLocations(@RequestBody LocationSearchRequest req) {
        return locationService.findLocations(req);
    }
}