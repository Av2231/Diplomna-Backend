package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class SaveReservationRequest {
    @Getter
    @Setter
    private String user_id;
    @Getter
    @Setter
    private String from_time;
    @Getter
    @Setter
    private String to_time;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String category;

    public SaveReservationRequest() {
        this.from_time = "";
        this.to_time = "";
        this.user_id = "";
        this.title = "";
        this.category = "";
    }

    public SaveReservationRequest(String from_time, String to_time, String title, String category) {
        this.from_time = from_time;
        this.to_time = to_time;
        this.title = title;
        this.category = category;

    }
}
