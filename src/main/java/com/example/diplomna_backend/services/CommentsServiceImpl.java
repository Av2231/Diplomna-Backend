package com.example.diplomna_backend.services;

import com.example.diplomna_backend.dto.CommentResponseModel;
import com.example.diplomna_backend.dto.LocationIdRequest;
import com.example.diplomna_backend.interfaces.CommentService;
import com.example.diplomna_backend.model.User;
import com.example.diplomna_backend.repository.LocationRepository;
import com.example.diplomna_backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommentsServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    public CommentsServiceImpl(UserRepository userRepository,
                              LocationRepository locationRepository) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public ResponseEntity<?> getCommentsByLocation(LocationIdRequest request) {
        if (request.getLocation_id() == null) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "failed",
                    "message", "Location ID was not provided!"
            ));
        }

        List<CommentResponseModel> commentResponses = new ArrayList<>();

        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            if (user.getComments() != null) {
                for (User.Comment comment : user.getComments()) {
                    if (request.getLocation_id().equals(comment.getLocation_id())) {
                        CommentResponseModel res = new CommentResponseModel();
                        res.setUser(user.getFirst_name() + " " + user.getLast_name());
                        res.setComment(comment.getComment());
                        res.setUser_picture(user.getPicture() != null ? user.getPicture().getData() : "");
                        commentResponses.add(res);
                    }
                }
            }
        }

        if (commentResponses.isEmpty()) {
            return ResponseEntity.ok(Map.of(
                    "status", "no_comments",
                    "message", "No comments found for this location"
            ));
        }

        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Returned comments",
                "comments", commentResponses
        ));
    }
}