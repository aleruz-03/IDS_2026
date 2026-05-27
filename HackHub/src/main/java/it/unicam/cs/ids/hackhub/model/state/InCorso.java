package it.unicam.cs.ids.hackhub.model.state;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Team;

import java.util.Date;

public class InCorso implements StateHackathon {
    @Override
    public void registraTeam(Hackathon hackathon, Team team) {
        throw new RuntimeException("Iscrizioni chiuse! L'evento è in corso.");
    }

    @Override
    public void aggiungiSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        if (hackathon.getData_End() != null && new Date().after(hackathon.getData_End())) {
            throw new RuntimeException("Tempo scaduto per consegnare!");
        }    }

    @Override
    public void aggiornaSottomissione(Hackathon hackathon, Sottomissione sottomissione) {
        if (hackathon.getData_End() != null && new Date().after(hackathon.getData_End())) {
            throw new RuntimeException("Tempo scaduto per modificare!");
        }    }

    @Override
    public void valutaSottomissione(Hackathon hackathon) {
        throw new RuntimeException("L'Hackathon non è ancora in fase di valutazione!");
    }

    @Override
    public void proclamaVincitore(Hackathon hackathon, Team team) {
        throw new RuntimeException("L'evento è ancora in corso!");    }
}
