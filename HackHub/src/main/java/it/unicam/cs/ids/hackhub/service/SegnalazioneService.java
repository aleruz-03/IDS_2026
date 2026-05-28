package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.controller.DTO.SegnalazioneDTO;
import it.unicam.cs.ids.hackhub.model.Mentore;
import it.unicam.cs.ids.hackhub.model.Organizzatore;
import it.unicam.cs.ids.hackhub.model.Segnalazione;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.repository.MembroStaffRepository;
import it.unicam.cs.ids.hackhub.repository.SegnalazioneRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SegnalazioneService {

    private final SegnalazioneRepository segnalazioneRepository;
    private final TeamRepository teamRepository;
    private final MembroStaffRepository membroStaffRepository;

    @Autowired
    public SegnalazioneService(SegnalazioneRepository segnalazioneRepository, TeamRepository teamRepository, MembroStaffRepository membroStaffRepository) {
        this.segnalazioneRepository = segnalazioneRepository;
        this.teamRepository = teamRepository;
        this.membroStaffRepository = membroStaffRepository;
    }

    public List<Segnalazione> getAllSegnalazioni(){
        return segnalazioneRepository.findAll();
    }


    public List<Segnalazione> getAllSegnalazioniOfOrganizzatore(Long idOrganizzatore){
        return segnalazioneRepository.findByOrganizzatoreId(idOrganizzatore);
    }

    public List<Segnalazione> getAllSegnalazioneOfMentore(Long idMentore){
        return segnalazioneRepository.findByMentoreId(idMentore);
    }

    public Segnalazione getSegnalazioneById(Long idSegnalazione){
        return segnalazioneRepository.findById(idSegnalazione).orElseThrow(()-> new RuntimeException((
                "Segnalazione non trovata con ID: " + idSegnalazione
                )));
    }

    @Transactional
    public Segnalazione createSegnalazione(SegnalazioneDTO segnalazioneDTO) {
        Team team = teamRepository.findById(segnalazioneDTO.idTeam())
                .orElseThrow(() -> new RuntimeException("Team non trovato con ID: " + segnalazioneDTO.idTeam()));

        Mentore mentore = (Mentore) membroStaffRepository.findById(segnalazioneDTO.idMentore())
                .orElseThrow(() -> new RuntimeException("Mentore non trovato con ID: " + segnalazioneDTO.idMentore()));;
        Organizzatore organizzatore = (Organizzatore) membroStaffRepository.findById(segnalazioneDTO.idOrganizzatore())
                .orElseThrow(() -> new RuntimeException("Organizzatore non trovato con ID: " + segnalazioneDTO.idOrganizzatore()));

        Segnalazione segnalazione = new Segnalazione(
                segnalazioneDTO.descrizione(),
                team,
                organizzatore,
                mentore
        );

        return segnalazioneRepository.save(segnalazione);
    }

    @Transactional
    public Segnalazione modificaSegnalazione(Long idSegnalazione, String descrizione){
        Segnalazione segnalazione = getSegnalazioneById(idSegnalazione);

        segnalazione.setDescrizione(descrizione);

        return segnalazioneRepository.save(segnalazione);
    }

    @Transactional
    public void eliminaSegnalazione(Long idSegnalazione){
        if (!segnalazioneRepository.existsById(idSegnalazione)) {
            throw new RuntimeException(("Impossibile eliminare: segnalazione non trovata con ID: " + idSegnalazione));
        }

        segnalazioneRepository.deleteById(idSegnalazione);
    }
}
