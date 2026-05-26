package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Hackathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;

    private String description;

    private Date scandezaIscrizioni;

    private Date data_start;
    private Date data_end;

    @Enumerated(EnumType.STRING)
    private StatoHackathon stato;

    @Embedded
    private Location location;

    private double premioInDenaro;

    @ManyToOne
    private Organizzatore organizzatore;

    @ManyToOne
    private Giudice giudice;

    @ManyToMany
    private List<Mentore> mentori;

    @ManyToMany
    private List<Team> teams;


    public Hackathon(String name, String description, Date scandezaIscrizioni, Date data_start, Date data_end, Location location, double premioInDenaro, Organizzatore organizzatore, Giudice giudice, List<Mentore> mentori) {
        this.name = name;
        this.description = description;
        this.scandezaIscrizioni = scandezaIscrizioni;
        this.data_start = data_start;
        this.data_end = data_end;
        this.location = location;
        this.premioInDenaro = premioInDenaro;
        this.organizzatore = organizzatore;
        this.giudice = giudice;
        this.mentori = mentori;
        this.stato = StatoHackathon.ISCRIZIONE;
    }

    public Hackathon() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getScandezaIscrizioni() {
        return scandezaIscrizioni;
    }

    public void setScandezaIscrizioni(Date scandezaIscrizioni) {
        this.scandezaIscrizioni = scandezaIscrizioni;
    }

    public Date getData_Start() {
        return data_start;
    }

    public void setData_Start(Date start) {
        this.data_start = start;
    }

    public Date getData_End() {
        return data_end;
    }

    public void setEnd(Date end) {
        this.data_end = end;
    }

    public double getPremioInDenaro() {
        return premioInDenaro;
    }

    public void setPremioInDenaro(double premioInDenaro) {
        this.premioInDenaro = premioInDenaro;
    }

    public StatoHackathon getStato() {
        return stato;
    }

    public void setStato(StatoHackathon stato) {
        this.stato = stato;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Organizzatore getOrganizzatore() {
        return organizzatore;
    }

    public void setOrganizzatore(Organizzatore organizzatore) {
        this.organizzatore = organizzatore;
    }

    public Giudice getGiudice() {
        return giudice;
    }

    public void setGiudice(Giudice giudice) {
        this.giudice = giudice;
    }

    public List<Mentore> getMentori() {
        return mentori;
    }

    public void setMentori(List<Mentore> mentori) {
        this.mentori = mentori;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
