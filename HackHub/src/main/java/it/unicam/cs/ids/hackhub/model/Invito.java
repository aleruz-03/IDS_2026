package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

@Entity
public class Invito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Utente mittente;

    @ManyToOne
    private Utente destinatario;

    public Invito(Team team, Utente destinatario, Utente mittente) {
        this.team = team;
        this.destinatario = destinatario;
        this.mittente = mittente;
    }

    public Invito() {

    }

    public Long getId() {return id;}

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
