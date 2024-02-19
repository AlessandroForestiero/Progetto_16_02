package com.example.progetto_16_02.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
 @Data
public class Utente {
@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String nome;
private String cognome;
private String userName;
private String Password;
}
