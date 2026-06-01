package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.IscrizioneDTO;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/crea/{idCreatore}")
    public ResponseEntity<String> creaTeam(@PathVariable Long idCreatore, @RequestBody Team team){
        try {
            Team nuovoTeam = teamService.creaTeam(team, idCreatore);
            return ResponseEntity.status(201).body("Team '" + nuovoTeam.getName() + "' creato con successo!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore nella creazione: " + e.getMessage());
        }
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAllTeams(){
        return ResponseEntity.ok(teamService.getAllTeams());
    }


    @PostMapping("/iscrizione/{idUtente}")
    public ResponseEntity<String> iscriviTeam(@PathVariable Long idUtente, @RequestBody IscrizioneDTO iscrizioneDTO){
        try {
            teamService.iscriviTeam(idUtente, iscrizioneDTO.idTeam(), iscrizioneDTO.idHackathon());
            return ResponseEntity.ok("Iscrizione completata! Il team è stato registrato all'hackathon.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore di iscrizione: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body("Impossibile iscriversi: " + e.getMessage());
        }
    }



}
