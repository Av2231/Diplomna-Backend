package com.example.diplomna_backend.repository;

import com.example.diplomna_backend.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LocationRepository extends MongoRepository<Location, String> {
    Optional<Location> findById(String id);
}
