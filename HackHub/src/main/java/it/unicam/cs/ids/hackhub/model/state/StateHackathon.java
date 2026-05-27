package it.unicam.cs.ids.hackhub.model.state;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Team;

public interface StateHackathon {
    void registraTeam(Hackathon hackathon, Team team);
    void aggiungiSottomissione(Hackathon hackathon, Sottomissione sottomissione);
    void aggiornaSottomissione(Hackathon hackathon, Sottomissione sottomissione);
    void valutaSottomissione(Hackathon hackathon);
    void proclamaVincitore(Hackathon hackathon, Team team);
}
