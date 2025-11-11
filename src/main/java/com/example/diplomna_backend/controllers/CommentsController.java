package com.example.diplomna_backend.controllers;

import com.example.diplomna_backend.dto.LocationIdRequest;
import com.example.diplomna_backend.interfaces.CommentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CommentsController {

    private final CommentService commentsService;

    public CommentsController(CommentService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/select_comments")
    public ResponseEntity<?> getCommentsByLocation(@RequestBody LocationIdRequest request) {
        return commentsService.getCommentsByLocation(request);
    }

    @PostMapping("/insert_comment")
    public ResponseEntity<?> insertComment(@RequestBody LocationIdRequest request) {
        return commentsService.getCommentsByLocation(request);
    }
}