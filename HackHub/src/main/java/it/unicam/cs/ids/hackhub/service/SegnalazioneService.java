package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.controller.DTO.SegnalazioneDTO;
import it.unicam.cs.ids.hackhub.model.Segnalazione;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.repository.SegnalazioneRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SegnalazioneService {

    @Autowired
    private SegnalazioneRepository segnalazioneRepository;
    private TeamRepository teamRepository;

    public List<Segnalazione> getAllSegnalazioni(){
        return segnalazioneRepository.findAll();
    }


    public List<Segnalazione> getAllSegnalazioniOfOrganizzatore(Long idOrganizzatore){
        return segnalazioneRepository.findByOrganizzatoreId(idOrganizzatore);
    }

    public List<Segnalazione> getAllSegnalazioneOfMentore(Long idMentore){
        return segnalazioneRepository.findByMentoreId(idMentore);
    }

    public Segnalazione createSegnalazione(SegnalazioneDTO segnalazioneDTO) {
        Optional<Team> team = teamRepository.findById(segnalazioneDTO.idTeam());
        Segnalazione segnalazione = new Segnalazione();





        return segnalazioneRepository.save(segnalazione);
    }
}
