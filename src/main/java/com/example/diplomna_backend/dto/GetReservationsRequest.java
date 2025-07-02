package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class GetReservationsRequest {
    @Getter
    @Setter
    private String userId;

    public GetReservationsRequest(String userId) {
        this.userId = userId;
    }

    public GetReservationsRequest() {
        this.userId = "";
    }
}