package com.example.diplomna_backend.dto;

import lombok.Getter;
import lombok.Setter;

public class RegisterRequest {
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String dob;
    @Getter
    @Setter
    private String first_name;
    @Getter
    @Setter
    private String last_name;
    @Getter
    @Setter
    private String isDeleted;
    @Getter
    @Setter
    private String isConfirmed;
    @Getter
    @Setter
    private String role;
    @Getter
    @Setter
    private String user_picture;

}