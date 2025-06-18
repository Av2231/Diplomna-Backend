package com.example.diplomna_backend.dto;


import lombok.Getter;
import lombok.Setter;

public class UpdatePictureRequest {
    @Getter
    @Setter
    private String user_id;
    @Getter
    @Setter
    private String user_picture;

    public UpdatePictureRequest(String user_id, String user_picture) {
        this.user_id = user_id;
        this.user_picture = user_picture;
    }

}