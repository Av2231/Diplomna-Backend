package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class UserIdRequest {
    @Getter
    @Setter
    private String user_id;

    public UserIdRequest(String user_id) {
        this.user_id = user_id;
    }

    public UserIdRequest() {
        this.user_id = "";
    }
}
