package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import it.unicam.cs.ids.hackhub.repository.UtenteRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final UtenteRepository utenteRepository;
    public TeamService(TeamRepository teamRepository, UtenteRepository utenteRepository) {
        this.teamRepository = teamRepository;
        this.utenteRepository = utenteRepository;
    }


    public Team creaTeam(Team team, Long idCreatore){
        Team newTeam = new Team(team.getName());
        List<Utente> partecipanti = new ArrayList<>();
        partecipanti.add(utenteRepository.getUtenteById(idCreatore));
        newTeam.setPartecipanti(partecipanti);
        return teamRepository.save(newTeam);
    }

    public  List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
