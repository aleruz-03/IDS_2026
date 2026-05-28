package it.unicam.cs.ids.hackhub.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class RichiestaSupporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descrizione;

    private String rispostaMentore;

    private boolean evasa = false;

    private String linkCall;

    private Date dataCall;

    @ManyToOne
    private Mentore mentore;

    public RichiestaSupporto(String descrizione, String linkCall, Date dataCall, Mentore mentore) {
        this.descrizione = descrizione;
        this.linkCall = linkCall;
        this.dataCall = dataCall;
        this.mentore = mentore;
        this.evasa = false;
    }

    public RichiestaSupporto() {
    }

    public Long getId() {
        return id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getRispostaMentore() {
        return rispostaMentore;
    }

    public void setRispostaMentore(String rispostaMentore) {
        this.rispostaMentore = rispostaMentore;
    }

    public boolean isEvasa() {
        return evasa;
    }

    public void setEvasa(boolean evasa) {
        this.evasa = evasa;
    }

    public String getLinkCall() {
        return linkCall;
    }

    public void setLinkCall(String linkCall) {
        this.linkCall = linkCall;
    }

    public Date getDataCall() {
        return dataCall;
    }

    public void setDataCall(Date dataCall) {
        this.dataCall = dataCall;
    }

    public Mentore getMentore() {
        return mentore;
    }

    public void setMentore(Mentore mentore) {
        this.mentore = mentore;
    }
}
