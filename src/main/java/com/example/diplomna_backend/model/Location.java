package com.example.diplomna_backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document(collection = "locations")

public class Location {
    @Id
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String category;
    @Getter
    @Setter
    private String region;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    private double x;
    @Getter
    @Setter
    private double y;
    @Getter
    @Setter
    private ArrayList<Availability> available_time;


    public Location() {
        this.category = "";
        this.region = "";
        this.name = "";
        this.address = "";
        this.x = 0;
        this.y = 0;
        this.available_time = new ArrayList<>();

    }

    public Location(String name, String category,String address, String region, double x, double y, ArrayList<Availability> available_time) {
        this.name = name;
        this.category = category;
        this.region = region;
        this.address = address;
        this.x = x;
        this.y = y;
        this.available_time = available_time;

    }



    public static class Availability {
        @Getter @Setter
        private String from;
        @Getter @Setter
        private String to;

        public Availability() {
            this.from = "";
            this.to = "";
        }

        public Availability(String from, String to) {
            this.from = from;
            this.to = to;
        }
    }
}