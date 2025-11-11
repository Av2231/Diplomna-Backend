package com.example.diplomna_backend.controllers;

import com.example.diplomna_backend.dto.CommentResponseModel;
import com.example.diplomna_backend.dto.LocationIdRequest;
import com.example.diplomna_backend.interfaces.CommentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final CommentService commentsService;

    public CommentsController(CommentService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/by-location")
    public ResponseEntity<?> getCommentsByLocation(@RequestBody LocationIdRequest request) {
        return commentsService.getCommentsByLocation(request);
    }
}