package com.example.diplomna_backend.dto;

import com.example.diplomna_backend.model.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class LocationSearchResponse {
    @Getter
    @Setter
    private ArrayList<Location> locations;


    public LocationSearchResponse(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public LocationSearchResponse() {
        this.locations = new ArrayList<>();
    }
}