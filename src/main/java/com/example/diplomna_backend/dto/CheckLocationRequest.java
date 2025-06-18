package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class CheckLocationRequest {
    @Getter
    @Setter
    private String location_id;

    public CheckLocationRequest(String location_id) {
        this.location_id = location_id;
    }

    public CheckLocationRequest() {
        this.location_id = "";
    }
}