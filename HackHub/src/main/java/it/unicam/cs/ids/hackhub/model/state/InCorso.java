package it.unicam.cs.ids.hackhub.model.state;

import it.unicam.cs.ids.hackhub.exception.ConflictException;
import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Team;

import java.util.Date;

public class InCorso implements StateHackathon {
    @Override
    public void registraTeam(Hackathon hackathon, Team team) {
        throw new ConflictException("Iscrizioni chiuse! L'evento e' in corso.");
    }

    @Override
    public void aggiungiSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        if (hackathon.getData_End() != null && new Date().after(hackathon.getData_End())) {
            throw new ConflictException("Tempo scaduto per consegnare!");
        }
    }

    @Override
    public void aggiornaSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        if (hackathon.getData_End() != null && new Date().after(hackathon.getData_End())) {
            throw new ConflictException("Tempo scaduto per modificare!");
        }
    }

    @Override
    public void valutaSottomissione(Hackathon hackathon) {
        throw new ConflictException("L'Hackathon non e' ancora in fase di valutazione!");
    }

    @Override
    public void proclamaVincitore(Hackathon hackathon, Team team) {
        throw new ConflictException("L'evento e' ancora in corso!");
    }
}
