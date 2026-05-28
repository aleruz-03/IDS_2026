package it.unicam.cs.ids.hackhub.model;

import it.unicam.cs.ids.hackhub.model.state.*;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Hackathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Date scandezaIscrizioni;

    private Date data_start;
    private Date data_end;

    @Enumerated(EnumType.STRING)
    private StatoHackathon statoRiferimento;

    @Transient
    private StateHackathon statoAttuale;

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


    @OneToOne
    private Team teamVincitore;

    @PostLoad
    public void inizializzaStatoClasse() {
        if (this.statoRiferimento == null) this.statoRiferimento = StatoHackathon.ISCRIZIONE;
        switch (this.statoRiferimento) {
            case ISCRIZIONE -> this.statoAttuale = new InIscrizione();
            case IN_CORSO -> this.statoAttuale = new InCorso();
            case IN_VALUTAZIONE -> this.statoAttuale = new InValutazione();
            case CONCLUSO -> this.statoAttuale = new Concluso();
        }
    }


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
        this.statoRiferimento = StatoHackathon.ISCRIZIONE;
        this.statoAttuale = new InIscrizione();
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
        return this.statoRiferimento;
    }

    public void setStato(StatoHackathon stato) {
        this.statoRiferimento = stato;
        inizializzaStatoClasse();
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

    public Team getTeamVincitore() {
        return teamVincitore;
    }

    public void setTeamVincitore(Team teamVincitore) {
        this.teamVincitore = teamVincitore;
    }

    public void cambiaStato(StateHackathon nuovoStato){
        this.statoAttuale = nuovoStato;

        if (nuovoStato instanceof InIscrizione) this.statoRiferimento = StatoHackathon.ISCRIZIONE;
        else if (nuovoStato instanceof InCorso) this.statoRiferimento = StatoHackathon.IN_CORSO;
        else if (nuovoStato instanceof InValutazione) this.statoRiferimento = StatoHackathon.IN_VALUTAZIONE;
        else if (nuovoStato instanceof Concluso) this.statoRiferimento = StatoHackathon.CONCLUSO;
    }

    public void iscriviTeam(Team team){
        if (this.statoAttuale == null) inizializzaStatoClasse();
        this.statoAttuale.registraTeam(this, team);
    }

    public void aggiungiSottomissione(Sottomissione sottomissione) {
        if (this.statoAttuale == null) inizializzaStatoClasse();
        this.statoAttuale.aggiungiSottomissione(this, sottomissione);
    }

    public void aggiornaSottomissione(Sottomissione sottomissione) {
        if (this.statoAttuale == null) inizializzaStatoClasse();
        this.statoAttuale.aggiornaSottomissione(this, sottomissione);
    }

    public void valutaSottomissione() {
        if (this.statoAttuale == null) inizializzaStatoClasse();
        this.statoAttuale.valutaSottomissione(this);
    }
}
