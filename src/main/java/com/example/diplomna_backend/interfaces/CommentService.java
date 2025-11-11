package com.example.diplomna_backend.interfaces;

import com.example.diplomna_backend.dto.CommentResponseModel;
import com.example.diplomna_backend.dto.LocationIdRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    ResponseEntity<?> getCommentsByLocation(LocationIdRequest request);
}