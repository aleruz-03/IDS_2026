package it.unicam.cs.ids.hackhub.controller.DTO;

import it.unicam.cs.ids.hackhub.model.Valutazione;

public record AllValutazioniDTO(
        Long id,
        int voto,
        String descrizione,
        SottomissioneResponseDTO sottomissione,
        TeamResponseDTO team,
        MembroStaffResponseDTO giudice
) {

    public static AllValutazioniDTO fromValutazione(Valutazione valutazione) {
        SottomissioneResponseDTO sottomissione = valutazione.getSottomissione() == null
                ? null
                : SottomissioneResponseDTO.fromSottomissione(valutazione.getSottomissione());

        TeamResponseDTO team = valutazione.getSottomissione() == null || valutazione.getSottomissione().getTeam() == null
                ? null
                : TeamResponseDTO.fromTeam(valutazione.getSottomissione().getTeam());

        return new AllValutazioniDTO(
                valutazione.getId(),
                valutazione.getVoto(),
                valutazione.getDescrizione(),
                sottomissione,
                team,
                MembroStaffResponseDTO.fromMembroStaff(valutazione.getGiudice())
        );
    }
}
