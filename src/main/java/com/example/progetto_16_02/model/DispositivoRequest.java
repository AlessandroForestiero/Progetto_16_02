package com.example.progetto_16_02.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispositivoRequest {
    @NotNull(message = "Tipologia obbligatoria")
    @NotEmpty(message = "Tipologia non vuota")
    private Tipologia tipologia;
    @NotNull(message = "stato obbligatorio")
    @NotEmpty(message = "stato non vuoto")
    private Stato stato;

    @NotNull(message = "Dipendente obbligatorio")
    private Integer idDipendente;
}
