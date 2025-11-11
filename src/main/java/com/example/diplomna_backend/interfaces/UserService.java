package com.example.diplomna_backend.interfaces;

import com.example.diplomna_backend.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    ResponseEntity<?> login(LoginRequest request);

    ResponseEntity<?> register(RegisterRequest request);

    ResponseEntity<?> changeProfilePicture(UpdatePictureRequest request);

    ResponseEntity<?> addComment(AddCommentRequest request);

    ResponseEntity<?> rateLocation(AddRatingRequest request);

    ResponseEntity<?> getUserCommentScore(UserIdRequest request);

    ResponseEntity<?> getUserRatingScore(UserIdRequest request);

    ResponseEntity<?> hasUserRatedLocation(UserLocationCheckRequest request);

    ResponseEntity<?> getUserReservations(String userId);

    ResponseEntity<?> saveReservation(SaveReservationRequest request);
}