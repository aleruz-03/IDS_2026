package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.controller.DTO.SegnalazioneDTO;
import it.unicam.cs.ids.hackhub.model.*;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.repository.MembroStaffRepository;
import it.unicam.cs.ids.hackhub.repository.SegnalazioneRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SegnalazioneService {

    private final SegnalazioneRepository segnalazioneRepository;
    private final TeamRepository teamRepository;
    private final MembroStaffRepository membroStaffRepository;
    private final HackathonRepository hackathonRepository;

    @Autowired
    public SegnalazioneService(SegnalazioneRepository segnalazioneRepository, TeamRepository teamRepository, MembroStaffRepository membroStaffRepository, HackathonRepository hackathonRepository) {
        this.segnalazioneRepository = segnalazioneRepository;
        this.teamRepository = teamRepository;
        this.membroStaffRepository = membroStaffRepository;
        this.hackathonRepository = hackathonRepository;
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
    public void createSegnalazione(Long idMentore, SegnalazioneDTO segnalazioneDTO) {
        Team team = teamRepository.findById(segnalazioneDTO.idTeam())
                .orElseThrow(() -> new RuntimeException("Team non trovato con ID: " + segnalazioneDTO.idTeam()));

        Mentore mentore = membroStaffRepository.getMentoreById(idMentore);
        Organizzatore organizzatore = membroStaffRepository.getOrganizzatoreById(segnalazioneDTO.idOrganizzatore());

        Segnalazione segnalazione = new Segnalazione(
                segnalazioneDTO.descrizione(),
                team,
                organizzatore,
                mentore
        );

        segnalazioneRepository.save(segnalazione);
    }

    @Transactional
    public void modificaSegnalazione(Long idSegnalazione, String descrizione){
        Segnalazione segnalazione = getSegnalazioneById(idSegnalazione);

        segnalazione.setDescrizione(descrizione);

        segnalazioneRepository.save(segnalazione);
    }

    @Transactional
    public void eliminaSegnalazione(Long idSegnalazione){
        if (!segnalazioneRepository.existsById(idSegnalazione)) {
            throw new RuntimeException(("Impossibile eliminare: segnalazione non trovata con ID: " + idSegnalazione));
        }

        segnalazioneRepository.deleteById(idSegnalazione);
    }

    public void segnalazioneNonFondata(Long idSegnalazione, Long idOrganizzatore) {
        segnalazioneRepository.deleteById(idSegnalazione);
    }

    public void bannaTeam(Long idSegnalazione, Long idOrganizzatore, Long idHackathon) {
        Team team = segnalazioneRepository.getSegnalazioneById(idSegnalazione).getTeam();
        Hackathon hackathon = hackathonRepository.getHackathonById(idHackathon);

        hackathon.getTeams().remove(team);
        hackathonRepository.save(hackathon);
        team.getHackathon().remove(hackathon);
        teamRepository.save(team);

        segnalazioneRepository.deleteById(idSegnalazione);
    }
}
