package com.example.progetto_16_02.model;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "userName obbligatorio")
    private String userName;
    @NotBlank(message = "password obbligatorio")
    private String Password;
}
