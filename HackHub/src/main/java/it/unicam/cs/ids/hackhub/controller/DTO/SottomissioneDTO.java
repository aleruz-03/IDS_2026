package it.unicam.cs.ids.hackhub.controller.DTO;

public record SottomissioneDTO(
        String url,
        String descrizione,
        Long idTeam,
        Long idHackathon
) {
}
