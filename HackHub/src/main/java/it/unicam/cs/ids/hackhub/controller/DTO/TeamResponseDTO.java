package it.unicam.cs.ids.hackhub.controller.DTO;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.model.Utente;

import java.util.List;

public record TeamResponseDTO(
        Long id,
        String name,
        List<Utente> partecipanti,
        List<String> hackathons
) {
    public static TeamResponseDTO fromTeam(Team team) {
        List<String> hackathonNames = team.getHackathon() == null
                ? List.of()
                : team.getHackathon().stream()
                .map(Hackathon::getName)
                .toList();

        return new TeamResponseDTO(
                team.getId(),
                team.getName(),
                team.getPartecipanti(),
                hackathonNames
        );
    }
}
