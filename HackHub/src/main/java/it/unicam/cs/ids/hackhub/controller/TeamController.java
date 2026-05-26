package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.HackathonResponseDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.IscrizioneDTO;
import it.unicam.cs.ids.hackhub.model.Hackathon;
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
    public ResponseEntity<Team> creaTeam(@PathVariable Long idCreatore, @RequestBody Team team){
        return ResponseEntity.ok(teamService.creaTeam(team, idCreatore));
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAllTeams(){
        return ResponseEntity.ok(teamService.getAllTeams());
    }


    @PostMapping("/iscrizione/{idUtente}")
    public ResponseEntity<HackathonResponseDTO> iscriviTeam(@PathVariable Long idUtente, @RequestBody IscrizioneDTO iscrizioneDTO){
        Hackathon hackathon = teamService.iscriviTeam(idUtente, iscrizioneDTO.idTeam(), iscrizioneDTO.idHackathon());
        return ResponseEntity.ok(HackathonResponseDTO.fromHackathon(hackathon));
    }



}
