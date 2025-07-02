package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class ReservationsRequest {
    @Getter
    @Setter
    private double x;
    @Getter
    @Setter
    private double y;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String location;
    @Getter
    @Setter
    private String type;
    @Getter
    @Setter
    private String from;
    @Getter
    @Setter
    private String to;
    @Getter
    @Setter
    private String userId;

    public ReservationsRequest() {
        this.x = 0;
        this.y = 0;
        this.title = "";
        this.location = "";
        this.type = "";
        this.from = "";
        this.to = "";
        this.userId = "";
    }

    public ReservationsRequest(double x, double y, String title, String location, String type, String from, String to, String userId) {
        this.x = x;
        this.y = y;
        this.title = title;
        this.location = location;
        this.type = type;
        this.from = from;
        this.to = to;
        this.userId = userId;
    }
}