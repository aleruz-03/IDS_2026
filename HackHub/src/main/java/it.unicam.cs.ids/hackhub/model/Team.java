package com.example.hackhub.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private  String name;

    @OneToMany
    private List<Utente> partecipanti;

    public Team(String name, List<Utente> partecipanti) {
        this.name = name;
        this.partecipanti = partecipanti;
    }

    public Team() {
    }
}
