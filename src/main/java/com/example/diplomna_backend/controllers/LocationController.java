package com.example.diplomna_backend.controllers;

import com.example.diplomna_backend.dto.*;
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

    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    public LocationController(LocationRepository locationRepository, UserRepository userRepository) {
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> addLocation(@RequestBody AddLocationRequest request) {
        if (request.getLocation_id() == null) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "Location id was not provided!"
            ));
        }

        Optional<Location> existing = locationRepository.findById(request.getLocation_id());
        if (existing.isPresent()) {
            return ResponseEntity.ok(Map.of(
                    "status", "existing",
                    "message", "This location id is already being saved!"
            ));
        }

        Location newLocation = new Location();
        newLocation.setId(request.getLocation_id());

        Location saved = locationRepository.save(newLocation);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Location added successfully!",
                "generated_location_id", saved.getId()
        ));
    }

    @PostMapping("/select_locations")
    public ResponseEntity<?> checkIfLocationExists(@RequestBody CheckLocationRequest request) {
        if (request.getLocation_id() == null || request.getLocation_id().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "Location id was not provided!"
            ));
        }

        Optional<Location> locationOpt = locationRepository.findById(request.getLocation_id());

        if (locationOpt.isPresent()) {
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Location is already existing!",
                    "base_id", locationOpt.get().getId()
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "Location is not existing!",
                    "base_id", "0"
            ));
        }
    }

    @PostMapping("/location_average_rating")
    public ResponseEntity<?> getAverageRatingForLocation(@RequestBody LocationIdRequest request) {
        if (request.getLocation_id() == null || request.getLocation_id().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "Location ID was not provided!"
            ));
        }

        String targetLocationId = request.getLocation_id();
        List<User> users = userRepository.findAll();

        List<Double> ratings = new ArrayList<>();

        for (User user : users) {
            if (user.getRatings() != null) {
                for (User.Rating rating : user.getRatings()) {
                    if (targetLocationId.equals(rating.getLocation_id())) {
                        ratings.add(rating.getRating());
                    }
                }
            }
        }

        if (ratings.isEmpty()) {
            return ResponseEntity.ok(Map.of(
                    "status", "not rated",
                    "message", "Location has never been rated!"
            ));
        }

        double avg = ratings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Returned average rate!",
                "average_rating", String.format("%.2f", avg)
        ));
    }

    @PostMapping("/find_locations")
    public ResponseEntity<?> findLocations(@RequestBody LocationSearchRequest req) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.getDefault());
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date fromDate;
        Date toDate;

        try {
            fromDate = formatter.parse(req.getFromDate());
            toDate = formatter.parse(req.getToDate());
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "Invalid date format. Expected: MM/dd/yyyy HH:mm"
            ));
        }

        List<Location> allLocations = locationRepository.findAll();

        List<Location> filtered = new ArrayList<>();

        for (Location loc : allLocations) {
            if (!loc.getCategory().equalsIgnoreCase(req.getCategory()) ||
                    !loc.getRegion().equalsIgnoreCase(req.getRegion())) {
                continue;
            }

            List<Location.Availability> matchingTimes = new ArrayList<>();

            for (Location.Availability av : loc.getAvailable_time()) {
                try {
                    Date avFrom = formatter.parse(av.getFrom());
                    Date avTo = formatter.parse(av.getTo());

                    boolean sameDay = fromDate.getYear() == avFrom.getYear() &&
                            fromDate.getMonth() == avFrom.getMonth() &&
                            fromDate.getDate() == avFrom.getDate();

                    boolean containsTimeRange = !fromDate.before(avFrom) && !toDate.after(avTo);

                    if (sameDay && containsTimeRange) {
                        matchingTimes.add(av);
                    }

                } catch (ParseException e) {
                }
            }

            if (!matchingTimes.isEmpty()) {
                Location locCopy = new Location();
                locCopy.setId(loc.getId());
                locCopy.setName(loc.getName());
                locCopy.setCategory(loc.getCategory());
                locCopy.setRegion(loc.getRegion());
                locCopy.setAddress(loc.getAddress());
                locCopy.setX(loc.getX());
                locCopy.setY(loc.getY());
                locCopy.setAvailable_time((ArrayList<Location.Availability>) matchingTimes);
                filtered.add(locCopy);
            }
        }

        return ResponseEntity.ok(new LocationSearchResponse((ArrayList<Location>) filtered));
    }
}