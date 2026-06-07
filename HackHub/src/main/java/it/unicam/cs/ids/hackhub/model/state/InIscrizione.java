package it.unicam.cs.ids.hackhub.model.state;

import it.unicam.cs.ids.hackhub.exception.ConflictException;
import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Team;

import java.util.Date;

public class InIscrizione implements StateHackathon {
    @Override
    public void registraTeam(Hackathon hackathon, Team team) {
        if (hackathon.getScandezaIscrizioni() != null && new Date().after(hackathon.getScandezaIscrizioni())) {
            throw new ConflictException("Termine ultimo per l'iscrizione scaduto!");
        }
        if (hackathon.getTeams().contains(team)) {
            throw new ConflictException("Team gia' iscritto!");
        }
        hackathon.getTeams().add(team);
    }

    @Override
    public void aggiungiSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        throw new ConflictException("L'Hackathon non e' ancora iniziato!");
    }

    @Override
    public void aggiornaSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        throw new ConflictException("L'Hackathon non e' ancora iniziato!");
    }

    @Override
    public void valutaSottomissione(Hackathon hackathon) {
        throw new ConflictException("L'Hackathon non e' in fase di valutazione!");
    }

    @Override
    public void proclamaVincitore(Hackathon hackathon, Team team) {
        throw new ConflictException("Impossibile proclamare un vincitore ora!");
    }
}
