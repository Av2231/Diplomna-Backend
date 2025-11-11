package com.example.diplomna_backend.controllers;

import com.example.diplomna_backend.dto.*;
import com.example.diplomna_backend.interfaces.UserService;
import com.example.diplomna_backend.model.Location;
import com.example.diplomna_backend.model.User;
import com.example.diplomna_backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/change-picture")
    public ResponseEntity<?> changeProfilePicture(@RequestBody UpdatePictureRequest request) {
        return userService.changeProfilePicture(request);
    }

    @PostMapping("/insert-comment")
    public ResponseEntity<?> addComment(@RequestBody AddCommentRequest request) {
        return userService.addComment(request);
    }

    @PostMapping("/rate-location")
    public ResponseEntity<?> rateLocation(@RequestBody AddRatingRequest request) {
        return userService.rateLocation(request);
    }

    @PostMapping("/comment-score")
    public ResponseEntity<?> getUserCommentScore(@RequestBody UserIdRequest request) {
        return userService.getUserCommentScore(request);
    }

    @PostMapping("/rating-score")
    public ResponseEntity<?> getUserRatingScore(@RequestBody UserIdRequest request) {
        return userService.getUserRatingScore(request);
    }

    @PostMapping("/has-user-rated")
    public ResponseEntity<?> hasUserRatedLocation(@RequestBody UserLocationCheckRequest request) {
        return userService.hasUserRatedLocation(request);
    }

    @PostMapping("/get_user_reservations")
    public ResponseEntity<?> getUserReservations(@RequestBody Map<String, String> request) {
        return userService.getUserReservations(request.get("user_id"));
    }

    @PostMapping("/save_location_details")
    public ResponseEntity<?> saveReservation(@RequestBody SaveReservationRequest request) {
        return userService.saveReservation(request);
    }
}