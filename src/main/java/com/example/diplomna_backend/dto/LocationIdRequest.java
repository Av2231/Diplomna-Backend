package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class LocationIdRequest {
    @Getter
    @Setter
    private String location_id;

    public LocationIdRequest(String location_id) {
        this.location_id = location_id;
    }

    public LocationIdRequest() {
        this.location_id = "";
    }
}
