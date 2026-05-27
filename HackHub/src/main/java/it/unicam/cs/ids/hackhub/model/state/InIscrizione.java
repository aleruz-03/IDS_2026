package it.unicam.cs.ids.hackhub.model.state;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Team;

import java.util.Date;

public class InIscrizione implements StateHackathon {
    @Override
    public void registraTeam(Hackathon hackathon, Team team) {
        if (hackathon.getScandezaIscrizioni() != null && new Date().after(hackathon.getScandezaIscrizioni())) {
            throw new RuntimeException("Termine ultimo per l'iscrizione scaduto!");
        }
        if (hackathon.getTeams().contains(team)) {
            throw new RuntimeException("Team già iscritto!");
        }
        hackathon.getTeams().add(team);
    }

    @Override
    public void aggiungiSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        throw new RuntimeException("L'Hackathon non è ancora iniziato!");
    }

    @Override
    public void aggiornaSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        throw new RuntimeException("L'Hackathon non è ancora iniziato!");
    }

    @Override
    public void valutaSottomissione(Hackathon hackathon) {
        throw new RuntimeException("L'Hackathon non è in fase di valutazione!");
    }

    @Override
    public void proclamaVincitore(Hackathon hackathon, Team team) {
        throw new RuntimeException("Impossibile proclamare un vincitore ora!");
    }
}
