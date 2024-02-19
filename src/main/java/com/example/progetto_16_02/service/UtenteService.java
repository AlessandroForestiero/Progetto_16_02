package com.example.progetto_16_02.service;

import com.example.progetto_16_02.model.Utente;
import com.example.progetto_16_02.model.UtenteRequest;
import com.example.progetto_16_02.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Utente save(UtenteRequest utenteRequest){
        Utente utente = new Utente();
        utente.setNome(utenteRequest.getNome());
        utente.setCognome(utenteRequest.getCognome());
        utente.setUserName(utenteRequest.getUserName());
        utente.setPassword(utente.getPassword());
        return utenteRepository.save(utente);


    }
    public Utente getUtenteByUsername(String username){
        return utenteRepository.findByUsername(username).orElseThrow
    }
}
