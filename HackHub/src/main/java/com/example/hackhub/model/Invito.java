package com.example.hackhub.model;

import jakarta.persistence.*;

@Entity
public class Invito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany
    private Team team;

    @OneToMany
    private Utente mittente;

    @OneToMany
    private Utente destinatario;

    public Invito(Team team, Utente mittente, Utente destinatario) {
        this.team = team;
        this.mittente = mittente;
        this.destinatario = destinatario;
    }

    public Invito() {

    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Utente getMittente() {
        return mittente;
    }

    public void setMittente(Utente mittente) {
        this.mittente = mittente;
    }

    public Utente getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Utente destinatario) {
        this.destinatario = destinatario;
    }
}
