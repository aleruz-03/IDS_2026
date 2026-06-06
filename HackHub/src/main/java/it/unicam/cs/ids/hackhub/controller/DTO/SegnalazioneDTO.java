package it.unicam.cs.ids.hackhub.controller.DTO;

public record SegnalazioneDTO(
        String descrizione,
        Long idTeam,
        Long idOrganizzatore
) {}
