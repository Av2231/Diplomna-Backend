package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class CommentResponseModel {
    @Getter
    @Setter
    private String user;
    @Getter
    @Setter
    private String comment;
    @Getter
    @Setter
    private String user_picture;

    public CommentResponseModel(String user, String comment, String user_picture) {
        this.user = user;
        this.comment = comment;
        this.user_picture = user_picture;
    }

    public CommentResponseModel() {
        super();
        this.user = "";
        this.comment = "";
        this.user_picture = "";

    }
}