package it.unicam.cs.ids.hackhub.controller.DTO;

import it.unicam.cs.ids.hackhub.model.Sottomissione;

import java.util.Date;

public record SottomissioneResponseDTO(
        Long id,
        Date dataCaricamento,
        String url,
        String descrizione,
        TeamResponseDTO team,
        String hackathon
) {
    public static SottomissioneResponseDTO fromSottomissione(Sottomissione sottomissione) {
        return new SottomissioneResponseDTO(
                sottomissione.getId(),
                sottomissione.getDataCaricamento(),
                sottomissione.getUrl(),
                sottomissione.getDescrizione(),
                sottomissione.getTeam() == null ? null : TeamResponseDTO.fromTeam(sottomissione.getTeam()),
                sottomissione.getHackathon() == null ? null : sottomissione.getHackathon().getName()
        );
    }
}
