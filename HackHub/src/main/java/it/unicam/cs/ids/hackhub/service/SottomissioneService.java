package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.controller.DTO.SottomissioneDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.ValutazioneDTO;
import it.unicam.cs.ids.hackhub.model.*;
import it.unicam.cs.ids.hackhub.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SottomissioneService {

    @Autowired
    private SottomissioneRepository sottomissioneRepository;
    private TeamRepository teamRepository;
    private HackathonRepository hackathonRepository;
    private ValutazioneRepository valutazioneRepository;
    private MembroStaffRepository membroStaffRepository;


    public List<Sottomissione> getAllSottomissione(){
        return sottomissioneRepository.findAll();
    }

    @Transactional
    public Sottomissione createSottomissione(SottomissioneDTO sottomissioneDTO){
        Team team = teamRepository.findById(sottomissioneDTO.idTeam())
                .orElseThrow(() -> new RuntimeException("Team non trovato con ID: " + sottomissioneDTO.idTeam()));

        Hackathon hackathon = hackathonRepository.findById(sottomissioneDTO.idHackathon())
                .orElseThrow(() -> new RuntimeException("Hackathon non trovato con ID: " +  sottomissioneDTO.idHackathon()));

        Sottomissione sottomissione = new Sottomissione(
                new Date(),
                sottomissioneDTO.url(),
                sottomissioneDTO.descrizione(),
                team,
                hackathon
        );

        hackathon.aggiungiSottomissione(sottomissione);

        return sottomissioneRepository.save(sottomissione);
    }

    @Transactional
    public Sottomissione aggiornaSottomissione(Long idSottomissione, SottomissioneDTO dto){
        Sottomissione sottomissione = sottomissioneRepository.findById(idSottomissione)
                .orElseThrow(() -> new RuntimeException("Sottomissione non trovata con ID: " + idSottomissione));

        Hackathon hackathon = sottomissione.getHackathon();

        sottomissione.setUrl(dto.url() != null ? dto.url() : sottomissione.getUrl());
        sottomissione.setDescrizione(dto.descrizione() != null ? dto.descrizione() : sottomissione.getDescrizione());
        sottomissione.setDataCaricamento(new Date());

        hackathon.aggiornaSottomissione(sottomissione);

        return sottomissioneRepository.save(sottomissione);
    }

    public Valutazione valutaSottomissione(Long idGiudice, ValutazioneDTO valutazioneDTO) {
        Sottomissione sottomissione = sottomissioneRepository.findById(valutazioneDTO.idSottomissione())
                .orElseThrow(() -> new RuntimeException("Sottomissione non trovata"));

        Hackathon hackathon = sottomissione.getHackathon();

        if (!hackathon.getGiudice().getId().equals(idGiudice)) {
            throw new RuntimeException("Giudice non assegnato all'Hackathon corrispondente alla sottomissione");
        }

        hackathon.valutaSottomissione();

        Giudice giudice = membroStaffRepository.getGiudiceById(idGiudice);

        Valutazione valutazione = new Valutazione(valutazioneDTO.voto(), valutazioneDTO.descrizione());
        valutazione.setSottomissione(sottomissione);
        valutazione.setGiudice(giudice);


        return valutazioneRepository.save(valutazione);
    }

}
