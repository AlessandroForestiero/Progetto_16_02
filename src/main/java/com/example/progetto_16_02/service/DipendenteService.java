package com.example.progetto_16_02.service;
import com.example.progetto_16_02.exception.NotFoundException;
import com.example.progetto_16_02.model.Dipendente;
import com.example.progetto_16_02.model.DipendenteRequest;
import com.example.progetto_16_02.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Page<Dipendente> getAll(Pageable pageable) {

        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente getDipendenteById(int id) throws NotFoundException {
        return dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Dipendente con id=" + id + " non trovato"));
    }

    public Dipendente saveDipendente(DipendenteRequest dipendenteRequest) {
        Dipendente dipendente = new Dipendente();
        dipendente.setUserName(dipendenteRequest.getUserName());
        dipendente.setNome(dipendenteRequest.getNome());
        dipendente.setCognome(dipendente.getCognome());
        dipendente.setEmail(dipendenteRequest.getEmail());

        return dipendenteRepository.save(dipendente);
    }


    public Dipendente updateDipendente(int id, DipendenteRequest dipendenteRequest) throws NotFoundException {
        Dipendente d = getDipendenteById(id);
        d.setUserName(dipendenteRequest.getUserName());
        d.setNome(dipendenteRequest.getNome());
        d.setCognome(dipendenteRequest.getCognome());
        d.setEmail(dipendenteRequest.getEmail());

        return dipendenteRepository.save(d);
    }

    public void deleteDipendente(int id) throws NotFoundException {
        Dipendente dipendente = getDipendenteById(id);
        dipendenteRepository.delete(dipendente);
    }
    public Dipendente uploadImmagineProfilo(int id, String url) throws NotFoundException{
        Dipendente dipendente = getDipendenteById(id);

        dipendente.setImmagineProfilo(url);
        return dipendenteRepository.save(dipendente);
    }


}
