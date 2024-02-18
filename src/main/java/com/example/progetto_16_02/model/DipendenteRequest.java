package com.example.progetto_16_02.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DipendenteRequest {
    @NotNull(message = "username obbligatorio")
    @NotEmpty(message = "username non vuoto")
    private String userName;
    @NotNull(message = "nome obbligatorio")
    @NotEmpty(message = "nome non vuoto")
    private String nome;
    @NotNull(message = "cognome obbligatorio")
    @NotEmpty(message = "cognome non vuoto")
    private String cognome;
    @Email(message = "Inserire email valida")
    private String email;
}
