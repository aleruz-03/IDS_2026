package it.unicam.cs.ids.hackhub.model.state;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Team;

import java.util.Date;

public class InValutazione implements StateHackathon {
    @Override
    public void registraTeam(Hackathon hackathon, Team team) {
        throw new RuntimeException("Iscrizioni chiuse!");
    }

    @Override
    public void aggiungiSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        throw new RuntimeException("Consegne bloccate! È iniziata la valutazione.");
    }

    @Override
    public void aggiornaSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        throw new RuntimeException("Consegne bloccate! È iniziata la valutazione.");
    }

    @Override
    public void valutaSottomissione(Hackathon hackathon) {
    }

    @Override
    public void proclamaVincitore(Hackathon hackathon, Team team) {
    }
}
