package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;


public class LocationSearchRequest {
    @Getter
    @Setter
    private String category;
    @Getter
    @Setter
    private String region;
    @Getter
    @Setter
    private String fromDate;
    @Getter
    @Setter
    private String toDate;

    public LocationSearchRequest(String category, String region, String fromDate, String toDate) {
        this.category = category;
        this.region = region;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public LocationSearchRequest() {
        this.category = "";
        this.region = "";
        this.fromDate = "";
        this.toDate = "";
    }
}