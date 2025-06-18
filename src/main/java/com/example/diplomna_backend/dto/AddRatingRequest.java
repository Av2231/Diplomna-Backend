package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class AddRatingRequest {
    @Getter
    @Setter
    private String user_id;
    @Getter
    @Setter
    private String location_id;
    @Getter
    @Setter
    private double rated;

    public AddRatingRequest(String user_id, String location_id, double rated) {
        this.user_id = user_id;
        this.location_id = location_id;
        this.rated = rated;
    }

    public AddRatingRequest() {
        super();
        this.rated = 0;
        this.user_id = "";
        this.location_id = "";
    }
}
