package it.unicam.cs.ids.hackhub.controller.DTO;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.StatoHackathon;
import it.unicam.cs.ids.hackhub.model.Team;

import java.util.Date;
import java.util.List;

public record HackathonResponseDTO(
        Long id,
        String name,
        String description,
        Date scadenzaIscrizioni,
        Date start,
        Date end,
        LocationResponseDTO location,
        double premioInDenaro,
        List<Team> teams,
        MembroStaffResponseDTO organizzatore,
        MembroStaffResponseDTO giudice,
        List<MembroStaffResponseDTO> mentori,
        StatoHackathon stato
) {
    public static HackathonResponseDTO fromHackathon(Hackathon hackathon) {
        List<MembroStaffResponseDTO> mentori = hackathon.getMentori() == null
                ? List.of()
                : hackathon.getMentori().stream()
                .map(MembroStaffResponseDTO::fromMembroStaff)
                .toList();

        List<Team> teams = hackathon.getTeams() == null
                ? List.of()
                :hackathon.getTeams().stream()
                 .toList();

        return new HackathonResponseDTO(
                hackathon.getId(),
                hackathon.getName(),
                hackathon.getDescription(),
                hackathon.getScandezaIscrizioni(),
                hackathon.getData_Start(),
                hackathon.getData_End(),
                LocationResponseDTO.fromLocation(hackathon.getLocation()),
                hackathon.getPremioInDenaro(),
                teams,
                MembroStaffResponseDTO.fromMembroStaff(hackathon.getOrganizzatore()),
                MembroStaffResponseDTO.fromMembroStaff(hackathon.getGiudice()),
                mentori,
                hackathon.getStato()
        );
    }
}
