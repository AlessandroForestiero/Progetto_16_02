package com.example.progetto_16_02.repository;

import com.example.progetto_16_02.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente,Integer> {
    public Optional<Utente> findByUsername(){
}
