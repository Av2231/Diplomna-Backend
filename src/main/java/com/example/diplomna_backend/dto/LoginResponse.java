package com.example.diplomna_backend.dto;

import com.example.diplomna_backend.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class LoginResponse {
    @Setter
    @Getter
    private String status;
    @Setter
    @Getter
    private String message;
    @Id
    @Setter
    @Getter
    private String id;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String first_name;
    @Setter
    @Getter
    private String last_name;
    @Setter
    @Getter
    private String dob;
    @Setter
    @Getter
    private String role;
    @Setter
    @Getter
    private String picture;

    public LoginResponse(String status, String message, String email, String firstName, String lastName, String role, String picture) {
        this.status = status;
        this.message = message;
        this.email = email;
        first_name = firstName;
        last_name = lastName;
        this.role = role;
        this.picture = picture;
    }


    public LoginResponse() {
        this.status = "";
        this.message = "";
        this.email = "";
        this.first_name = "";
        this.last_name = "";
        this.role = "";
        this.picture = null;
    }
}
