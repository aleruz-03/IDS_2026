package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.controller.DTO.HackathonResponseDTO;
import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.StatoHackathon;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import it.unicam.cs.ids.hackhub.repository.UtenteRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final UtenteRepository utenteRepository;
    private final HackathonRepository hackathonRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, UtenteRepository utenteRepository, HackathonRepository hackathonRepository) {
        this.teamRepository = teamRepository;
        this.utenteRepository = utenteRepository;
        this.hackathonRepository = hackathonRepository;
    }


    public Team creaTeam(String name, Long idCreatore){
        Team newTeam = new Team(name);
        List<Utente> partecipanti = new ArrayList<>();
        partecipanti.add(utenteRepository.getUtenteById(idCreatore));
        newTeam.setPartecipanti(partecipanti);
        return teamRepository.save(newTeam);
    }

    public  List<Team> getAllTeams() {
        return teamRepository.findAll();
    }


    public Hackathon iscriviTeam(Long idUtente, Long idTeam, Long idHackathon){
        Utente utente = utenteRepository.getUtenteById(idUtente);
        Team team = teamRepository.getTeamById(idTeam);
        Hackathon hackathon = hackathonRepository.getHackathonById(idHackathon);

        if(hackathon.getStato() != StatoHackathon.ISCRIZIONE){
            throw new RuntimeException("Le iscrizioni all'hackathon selezionato sono concluse");
        }

        if(!team.getPartecipanti().contains(utente)){
            throw new RuntimeException("Operazione non consentita: non fai parte di questo team!");
        }

        hackathon.iscriviTeam(team);

        if(!team.getHackathon().contains(hackathon)){
            team.getHackathon().add(hackathon);
        }

        team.getHackathon().add(hackathon);
        
        return hackathonRepository.save(hackathon);

    }

    public List<Team> getAllTeamsOfHackathon(Long idHackathon) {
        Hackathon hackathon = hackathonRepository.getHackathonById(idHackathon);
        return hackathon.getTeams();
    }
}
