package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.model.Segnalazione;
import it.unicam.cs.ids.hackhub.repository.SegnalazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegnalazioneService {

    @Autowired
    private SegnalazioneRepository segnalazioneRepository;

    public List<Segnalazione> getAllSegnalazioni(){
        return segnalazioneRepository.findAll();
    }
}
