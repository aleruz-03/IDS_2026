package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.IscrizioneDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.TeamResponseDTO;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> creaTeam(@PathVariable Long idCreatore, @RequestBody String name) {
        Team nuovoTeam = teamService.creaTeam(name, idCreatore);
        return ResponseEntity.status(HttpStatus.CREATED).body("Team '" + nuovoTeam.getName() + "' creato con successo!");
    }

    @GetMapping("/teams")
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
        List<Team> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams.stream().map(TeamResponseDTO::fromTeam).toList());
    }

    @PostMapping("/iscrizione/{idUtente}")
    public ResponseEntity<String> iscriviTeam(@PathVariable Long idUtente, @RequestBody IscrizioneDTO iscrizioneDTO) {
        teamService.iscriviTeam(idUtente, iscrizioneDTO.idTeam(), iscrizioneDTO.idHackathon());
        return ResponseEntity.ok("Iscrizione completata! Il team e' stato registrato all'hackathon.");
    }

    @GetMapping("teams/hackathon/{idHackathon}")
    public ResponseEntity<List<TeamResponseDTO>> getAllTeamsOfHackathon(@PathVariable Long idHackathon) {
        List<Team> teams = teamService.getAllTeamsOfHackathon(idHackathon);
        return ResponseEntity.ok(teams.stream().map(TeamResponseDTO::fromTeam).toList());
    }
}
