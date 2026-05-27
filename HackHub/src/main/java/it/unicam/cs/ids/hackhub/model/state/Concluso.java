package it.unicam.cs.ids.hackhub.model.state;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Team;

import java.util.Date;

public class Concluso implements StateHackathon {
    @Override
    public void registraTeam(Hackathon hackathon, Team team) {
        throw new RuntimeException("Hackathon concluso.");    }

    @Override
    public void aggiungiSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        throw new RuntimeException("Hackathon concluso.");
    }

    @Override
    public void aggiornaSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        throw new RuntimeException("Hackathon concluso.");
    }

    @Override
    public void valutaSottomissione(Hackathon hackathon) {
        throw new RuntimeException("Hackathon concluso.");
    }

    @Override
    public void proclamaVincitore(Hackathon hackathon, Team team) {
        throw new RuntimeException("Hackathon concluso.");
    }
}
