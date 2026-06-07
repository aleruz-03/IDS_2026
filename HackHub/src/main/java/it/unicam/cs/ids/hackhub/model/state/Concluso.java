package it.unicam.cs.ids.hackhub.model.state;

import it.unicam.cs.ids.hackhub.exception.ConflictException;
import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Team;

public class Concluso implements StateHackathon {
    @Override
    public void registraTeam(Hackathon hackathon, Team team) {
        throw new ConflictException("Hackathon concluso.");
    }

    @Override
    public void aggiungiSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        throw new ConflictException("Hackathon concluso.");
    }

    @Override
    public void aggiornaSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        throw new ConflictException("Hackathon concluso.");
    }

    @Override
    public void valutaSottomissione(Hackathon hackathon) {
        throw new ConflictException("Hackathon concluso.");
    }

    @Override
    public void proclamaVincitore(Hackathon hackathon, Team team) {
        throw new ConflictException("Hackathon concluso.");
    }
}
