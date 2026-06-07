package it.unicam.cs.ids.hackhub.controller.DTO;

import it.unicam.cs.ids.hackhub.model.Valutazione;

public record AllValutazioniDTO(
        Long id,
        int voto,
        String descrizione,
        SottomissioneResponseDTO sottomissione,
        MembroStaffResponseDTO giudice
) {

    public static AllValutazioniDTO fromValutazione(Valutazione valutazione) {
        SottomissioneResponseDTO sottomissione = valutazione.getSottomissione() == null
                ? null
                : SottomissioneResponseDTO.fromSottomissione(valutazione.getSottomissione());

        return new AllValutazioniDTO(
                valutazione.getId(),
                valutazione.getVoto(),
                valutazione.getDescrizione(),
                sottomissione,
                MembroStaffResponseDTO.fromMembroStaff(valutazione.getGiudice())
        );
    }
}
