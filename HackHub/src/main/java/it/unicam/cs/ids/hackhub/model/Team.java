package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

    @ManyToMany
    private List<Utente> partecipanti;

    @ManyToMany
    private List<Hackathon> hackathon;

    @OneToOne
    private Sottomissione sottomissione;

    public Team(String name) {
        this.name = name;
        this.hackathon = new ArrayList<>();
    }

    public Team() {
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Utente> getPartecipanti() {
        return partecipanti;
    }

    public void setPartecipanti(List<Utente> partecipanti) {
        this.partecipanti = partecipanti;
    }

    public List<Hackathon> getHackathon() {
        return hackathon;
    }

    public void setHackathon(List<Hackathon> hackathon) {
        this.hackathon = hackathon;
    }

    public Sottomissione getSottomissione() {
        return sottomissione;
    }

    public void setSottomissione(Sottomissione sottomissione) {
        this.sottomissione = sottomissione;
    }
}
