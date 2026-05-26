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

    @ManyToOne
    private Mentore mentore;

    public Segnalazione() {}

    public Segnalazione(String descrizione, Team team, Organizzatore organizzatore, Mentore mentore) {
        this.descrizione = descrizione;
        this.team = team;
        this.organizzatore = organizzatore;
        this.mentore = mentore;
    }

    public Long getId() {return id;}

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Team getTeam() {
        return team;
    }

    public Organizzatore getOrganizzatore() {
        return organizzatore;
    }

    public Mentore getMentore() {
        return mentore;
    }
}
