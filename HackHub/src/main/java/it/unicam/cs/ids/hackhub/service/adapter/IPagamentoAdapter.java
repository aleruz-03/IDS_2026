package it.unicam.cs.ids.hackhub.service.adapter;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Team;

public interface IPagamentoAdapter {

    boolean erogaPremio(Hackathon hackathon, Team teamVincitore);
}
