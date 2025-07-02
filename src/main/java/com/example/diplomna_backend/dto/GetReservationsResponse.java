package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class GetReservationsResponse {
    @Getter
    @Setter
    private String place;
    @Getter
    @Setter
    private String category;
    @Getter
    @Setter
    private String fromDate;
    @Getter
    @Setter
    private String toDate;

    public GetReservationsResponse(String place, String category, String fromDate, String toDate) {
        this.place = place;
        this.category = category;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public GetReservationsResponse() {
        this.place = "";
        this.category = "";
        this.fromDate = "";
        this.toDate = "";
    }
}
