package com.example.diplomna_backend.interfaces;

import com.example.diplomna_backend.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Map;

public interface LocationService {

    ResponseEntity<?> addLocation(AddLocationRequest request);

    ResponseEntity<?> checkIfLocationExists(CheckLocationRequest request);

    ResponseEntity<?> getAverageRatingForLocation(LocationIdRequest request);

    ResponseEntity<?> findLocations(LocationSearchRequest request);
}