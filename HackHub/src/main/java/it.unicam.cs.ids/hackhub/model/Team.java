package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private  String name;

    @ManyToMany
    private List<Utente> partecipanti;

    public Team(String name, List<Utente> partecipanti) {
        this.name = name;
        this.partecipanti = partecipanti;
    }

    public Team() {
    }
}
