package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class ReservationsResponse {
    @Getter
    @Setter
    private String placeName;
    @Getter
    @Setter
    private String category;
    @Getter
    @Setter
    private String fromTime;
    @Getter
    @Setter
    private String toTime;

    public ReservationsResponse(String placeName, String category, String fromTime, String toTime) {
        this.placeName = placeName;
        this.category = category;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public ReservationsResponse() {
        this.placeName = "";
        this.category = "";
        this.fromTime = "";
        this.toTime = "";
    }
}
