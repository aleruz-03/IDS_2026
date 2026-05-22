package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;


@Entity
public class Segnalazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String descrizione;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Organizzatore organizzatore;

    public Segnalazione() {}

    public Segnalazione(String descrizione, Team team) {
        this.descrizione = descrizione;
        this.team = team;
    }

}
