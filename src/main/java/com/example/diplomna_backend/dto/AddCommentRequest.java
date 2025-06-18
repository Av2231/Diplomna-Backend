package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class AddCommentRequest {
    @Getter
    @Setter
    private String user_id;
    @Getter
    @Setter
    private String location_id;
    @Getter
    @Setter
    private String comment;

    public AddCommentRequest(String user_id, String location_id, String comment) {
        this.user_id = user_id;
        this.location_id = location_id;
        this.comment = comment;
    }
}
