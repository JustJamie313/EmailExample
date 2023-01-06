package com.example.sendmail.dto;

import jakarta.validation.constraints.Email;

public class Form {
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
