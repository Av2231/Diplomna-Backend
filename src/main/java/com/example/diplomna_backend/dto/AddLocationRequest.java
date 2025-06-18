package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class AddLocationRequest {
    @Getter
    @Setter
    private String location_id;

    public AddLocationRequest(String location_id) {
        this.location_id = location_id;
    }

    public AddLocationRequest() {
        this.location_id = "";
    }
}