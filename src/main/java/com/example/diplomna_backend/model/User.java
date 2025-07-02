package com.example.diplomna_backend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;

@Document(collection = "users")
public class User {
    @Id
    @Setter
    @Getter
    private String id;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String password;
    @Setter
    @Getter
    private String first_name;
    @Getter
    @Setter
    private String last_name;
    @Setter
    @Getter
    private Date dob;
    @Getter
    @Setter
    private String role;
    @Setter
    @Getter
    private Picture picture;
    @Setter
    @Getter
    private ArrayList<Comment> comments;
    @Getter
    @Setter
    private ArrayList<Rating> ratings;
    @Getter
    @Setter
    private ArrayList<Reservation> reservations ;

    public User(String email, String password, String firstName, String lastName, Date dob, String role, boolean isDeleted, boolean isConfirmed, Picture picture, ArrayList<Comment> comments, ArrayList<Rating> ratings, ArrayList<Reservation> reservations) {
        this.email = email;
        this.password = password;
        first_name = firstName;
        last_name = lastName;
        this.dob = dob;
        this.role = role;

        this.picture = picture;
        this.comments = comments;
        this.ratings = ratings;
        this.reservations = reservations;
    }

    public User() {
        this.email = "";
        this.password = "";
        this.first_name = "";
        this.last_name = "";
        this.dob = new Date();
        this.role = "";
        this.picture = new Picture();
        this.comments = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }


    public static class Picture {
        @Setter
        @Getter
        private String data;
        @Setter
        @Getter
        private double size_kib;

        public Picture(String data, double sizeKib) {
            this.data = data;
            size_kib = sizeKib;
        }

        public Picture() {
            this.data = "";
            this.size_kib = 0;
        }

    }

    public static class Comment {
        @Setter
        @Getter
        private String location_id;
        @Setter
        @Getter
        private String comment;

        public Comment(String location_id, String comment) {
            this.location_id = location_id;
            this.comment = comment;
        }

        public Comment() {
            this.location_id = "";
            this.comment = "";
        }
    }

    public static class Rating {
        @Setter
        @Getter
        private String location_id;
        @Setter
        @Getter
        private double rating;

        public Rating(String location_id, double rating) {
            this.location_id = location_id;
            this.rating = rating;
        }

        public Rating() {
            this.location_id = "";
            this.rating = 0;
        }
    }

    public static class Reservation {
        @Getter
        @Setter
        private String locationName;

        @Getter
        @Setter
        private String category;

        @Getter
        @Setter
        private String title;

        @Getter
        @Setter
        private double x;

        @Getter
        @Setter
        private double y;

        @Getter
        @Setter
        private Date fromDate;

        @Getter
        @Setter
        private Date toDate;

        public Reservation() {
            this.locationName = "";
            this.category = "";
            this.title = "";
            this.fromDate = new Date();
            this.toDate = new Date();
            this.x = 0;
            this.y = 0;
        }

        public Reservation(String locationName, String category, String title,  Date fromDate, Date toDate, double x, double y) {
            this.locationName = locationName;
            this.category = category;
            this.title = title;
            this.fromDate = fromDate;
            this.toDate = toDate;
            this.x = x;
            this.y = y;
        }
    }

}
