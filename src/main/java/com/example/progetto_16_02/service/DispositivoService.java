package com.example.progetto_16_02.service;



import com.example.progetto_16_02.exception.NotFoundException;
import com.example.progetto_16_02.model.Dispositivo;
import com.example.progetto_16_02.model.DispositivoRequest;
import com.example.progetto_16_02.model.Dipendente;
import com.example.progetto_16_02.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;



    @Autowired
    private DipendenteService dipendenteService;

    public Page<Dispositivo> getAll(Pageable pageable) {

        return dispositivoRepository.findAll(pageable);
    }

    public Dispositivo getDispsotivoById(int id) throws NotFoundException {
        return dispositivoRepository.findById(id).orElseThrow(() -> new NotFoundException("Dispositivo con id=" + id + " non trovato"));
    }

    public Dispositivo saveDispositivo(DispositivoRequest dispositivoRequest) throws NotFoundException {

        Dipendente d = dipendenteService.getDipendenteById((dispositivoRequest.getIdDipendente()));

        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setTipologia(dispositivoRequest.getTipologia());
        dispositivo.setStato(dispositivoRequest.getStato());


        return dispositivoRepository.save(dispositivo);

    }

    public Dispositivo updateDispositivo(int id, DispositivoRequest dispositivoRequest) throws NotFoundException {

        Dipendente dipendente = dipendenteService.getDipendenteById(dispositivoRequest.getIdDipendente());
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setTipologia(dispositivoRequest.getTipologia());
        dispositivo.setStato(dispositivoRequest.getStato());


        return dispositivoRepository.save(dispositivo);

    }

    public void deleteDispositivo(int id) throws NotFoundException {
        Dispositivo dispositivo = getDispsotivoById(id);

        dispositivoRepository.delete(dispositivo);
    }

}
