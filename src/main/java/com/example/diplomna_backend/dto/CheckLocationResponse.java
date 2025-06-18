package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class CheckLocationResponse {
    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private String base_id;

    public CheckLocationResponse(String status, String message, String base_id) {
        this.status = status;
        this.message = message;
        this.base_id = base_id;
    }

    public CheckLocationResponse() {
        super();
        this.status = "";
        this.message = "";
        this.base_id = "";
    }
 }
