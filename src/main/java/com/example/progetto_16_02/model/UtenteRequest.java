package com.example.progetto_16_02.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UtenteRequest {
    @NotBlank(message = "nome obbligatorio")
    private String nome;
    @NotBlank(message = "cognome obbligatorio")
    private String cognome;
    @NotBlank(message = "userName obbligatorio")
    private String userName;
    @NotBlank(message = "password obbligatorio")
    private String Password;
}
