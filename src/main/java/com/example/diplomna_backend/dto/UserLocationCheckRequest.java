package com.example.diplomna_backend.dto;


import lombok.Getter;
import lombok.Setter;

public class UserLocationCheckRequest {
    @Getter
    @Setter
    private String user_id;
    @Getter
    @Setter
    private String location_id;

    public UserLocationCheckRequest(String user_id, String location_id) {
        this.user_id = user_id;
        this.location_id = location_id;
    }

    public UserLocationCheckRequest() {
        this.user_id = "";
        this.location_id = "";
    }
}
