package com.example.hackhub.model;

import jakarta.persistence.*;


@Entity
public class Segnalazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String descrizione;

    @OneToMany
    private Team team;

    public Segnalazione() {}

    public Segnalazione(String descrizione, Team team) {
        this.descrizione = descrizione;
        this.team = team;
    }

}
