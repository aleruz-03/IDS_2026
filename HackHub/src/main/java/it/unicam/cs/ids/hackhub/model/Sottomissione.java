package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Sottomissione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    Long id;

    private Date dataCaricamento;
    private String url;
    private String descrizione;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name="hackathon_id")
    private Hackathon hackathon;

    public Sottomissione(Date dataCaricamento, String url, String descrizione,  Team team, Hackathon hackathon) {
        this.dataCaricamento = dataCaricamento;
        this.url = url;
        this.descrizione = descrizione;
        this.team = team;
        this.hackathon = hackathon;
    }

    public Sottomissione() {
    }

    public Long getId() {
        return id;
    }

    public Date getDataCaricamento() {
        return dataCaricamento;
    }

    public void setDataCaricamento(Date dataCaricamento) {
        this.dataCaricamento = dataCaricamento;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }

    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }
}
