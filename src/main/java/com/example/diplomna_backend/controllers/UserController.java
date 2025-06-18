package com.example.diplomna_backend.controllers;

import com.example.diplomna_backend.dto.*;
import com.example.diplomna_backend.model.User;
import com.example.diplomna_backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (request.getEmail() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest().body(Map.of("status", "failed", "message", "All fields are required!"));
        }

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("status", "failed", "message", "Invalid username!"));
        }

        User user = optionalUser.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("status", "failed", "message", "Incorrect password!"));
        }

        LoginResponse response = new LoginResponse();
        response.setStatus("success");
        response.setMessage("Logged successfully!");
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirst_name(user.getFirst_name());
        response.setLast_name(user.getLast_name());
        response.setDob(user.getDob() != null ? user.getDob().toString() : null);
        response.setPicture(user.getPicture() != null ? user.getPicture().getData() : "");
        response.setRole(user.getRole());


        return ResponseEntity.ok(response);
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        if (request.getEmail() == null || request.getPassword() == null ||
                request.getDob() == null || request.getFirst_name() == null ||
                request.getLast_name() == null) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "All fields are required!"
            ));
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "status", "failed",
                    "message", "There is account with this email address!"
            ));
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirst_name(request.getFirst_name());
        user.setLast_name(request.getLast_name());
        user.setRole(request.getRole());

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(request.getDob(), formatter);
            user.setDob(Date.from(localDate.atStartOfDay(ZoneOffset.UTC).toInstant()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "Invalid date format. Use dd/MM/yyyy (e.g. 16/06/2025)"
            ));
        }

        if (request.getUser_picture() != null && !request.getUser_picture().isBlank()) {
            User.Picture pic = new User.Picture();
            pic.setData(request.getUser_picture());
            pic.setSize_kib(Math.round((request.getUser_picture().length() * 3 / 4.0) / 1024.0));
            user.setPicture(pic);
        }

        userRepository.save(user);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Registered successfully!"
        ));
    }


    @PostMapping("/change-picture")
    public ResponseEntity<?> changeProfilePicture(@RequestBody UpdatePictureRequest request) {
        if (request.getUser_id() == null || request.getUser_picture() == null) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "All fields are required!"
            ));
        }

        Optional<User> optionalUser = userRepository.findById(request.getUser_id());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "failed",
                    "message", "User is not existing!"
            ));
        }

        User user = optionalUser.get();

        User.Picture picture = new User.Picture();
        picture.setData(request.getUser_picture());
        picture.setSize_kib(Math.round((request.getUser_picture().length() * 3 / 4.0) / 1024.0));
        user.setPicture(picture);

        userRepository.save(user);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Picture saved!"
        ));
    }

    @PostMapping("/insert-comment")
    public ResponseEntity<?> addComment(@RequestBody AddCommentRequest request) {
        if (request.getUser_id() == null || request.getLocation_id() == null || request.getComment() == null) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "Parameters were not provided!"
            ));
        }

        Optional<User> optionalUser = userRepository.findById(request.getUser_id());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "failed",
                    "message", "User not found!"
            ));
        }

        User user = optionalUser.get();

        User.Comment newComment = new User.Comment();
        newComment.setLocation_id(request.getLocation_id());
        newComment.setComment(request.getComment());

        user.getComments().add(newComment);
        userRepository.save(user);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Commented successfully!"
        ));
    }

    @PostMapping("/rate-location")
    public ResponseEntity<?> rateLocation(@RequestBody AddRatingRequest request) {
        if (request.getUser_id() == null || request.getLocation_id() == null) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "Some of the fields are empty!"
            ));
        }

        Optional<User> optionalUser = userRepository.findById(request.getUser_id());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "failed",
                    "message", "User not found!"
            ));
        }

        User user = optionalUser.get();

        Optional<User.Rating> existingRating = user.getRatings().stream()
                .filter(r -> r.getLocation_id().equals(request.getLocation_id()))
                .findFirst();

        if (existingRating.isPresent()) {
            existingRating.get().setRating(request.getRated());
        } else {
            User.Rating newRating = new User.Rating();
            newRating.setLocation_id(request.getLocation_id());
            newRating.setRating(request.getRated());
            user.getRatings().add(newRating);
        }

        userRepository.save(user);

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Location rated successfully!"
        ));
    }

    @PostMapping("/comment-score")
    public ResponseEntity<?> getUserCommentScore(@RequestBody UserIdRequest request) {
        if (request.getUser_id() == null || request.getUser_id().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "User id was not provided!"
            ));
        }

        Optional<User> userOpt = userRepository.findById(request.getUser_id());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "failed",
                    "message", "User not found!"
            ));
        }

        User user = userOpt.get();
        int count = (user.getComments() != null) ? user.getComments().size() : 0;

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", count > 0 ? "Returned average rate and user rating score!" : "User never rated!",
                "comment_score", String.valueOf(count)
        ));
    }

    @PostMapping("/rating-score")
    public ResponseEntity<?> getUserRatingScore(@RequestBody UserIdRequest request) {
        if (request.getUser_id() == null || request.getUser_id().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "User id was not provided!"
            ));
        }

        Optional<User> userOpt = userRepository.findById(request.getUser_id());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "failed",
                    "message", "User not found!"
            ));
        }

        User user = userOpt.get();
        List<User.Rating> ratings = user.getRatings();

        if (ratings == null || ratings.isEmpty()) {
            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", "User never rated!",
                    "average_rating", "0",
                    "rate_score", "0"
            ));
        }

        double sum = ratings.stream().mapToDouble(User.Rating::getRating).sum();
        int count = ratings.size();
        double avg = sum / count;

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Returned average rate and user rating score!",
                "average_rating", String.format("%.2f", avg),
                "rate_score", String.valueOf(count)
        ));
    }


    @PostMapping("/has-user-rated")
    public ResponseEntity<?> hasUserRatedLocation(@RequestBody UserLocationCheckRequest request) {
        if (request.getUser_id() == null || request.getLocation_id() == null ||
                request.getUser_id().isBlank() || request.getLocation_id().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "User ID or location ID was not provided!"
            ));
        }

        Optional<User> userOpt = userRepository.findById(request.getUser_id());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "failed",
                    "message", "User not found!"
            ));
        }

        User user = userOpt.get();
        boolean hasRated = user.getRatings() != null && user.getRatings().stream()
                .anyMatch(r -> request.getLocation_id().equals(r.getLocation_id()));

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "User already rated this location!",
                "rated", hasRated ? "true" : "false"
        ));
    }

    @PostMapping("/get_user_reservations")
    public ResponseEntity<?> getUserReservations(@RequestBody Map<String, String> request) {
        String userId = request.get("user_id");

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "failed",
                    "message", "User not found"
            ));
        }

        User user = optionalUser.get();
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "reservations", user.getReservations()
        ));
    }

}
