package it.unicam.cs.ids.hackhub.controller.DTO;

import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.model.Valutazione;

public record AllValutazioniDTO(
        Long id,
        int voto,
        String descrizione,
        Sottomissione sottomissione,
        Team team
) {

    public static AllValutazioniDTO fromValutazione(Valutazione valutazione) {
        return new AllValutazioniDTO(
                valutazione.getId(),
                valutazione.getVoto(),
                valutazione.getDescrizione(),
                valutazione.getSottomissione(),
                valutazione.getSottomissione().getTeam()
        );
    }
}
