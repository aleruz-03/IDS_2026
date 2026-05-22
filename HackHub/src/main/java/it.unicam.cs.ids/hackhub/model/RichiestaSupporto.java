package it.unicam.cs.ids.hackhub.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class RichiestaSupporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String descrizione;

    private String linkCall;

    private Date dataCall;

    public RichiestaSupporto(String descrizione, String linkCall, Date dataCall) {
        this.descrizione = descrizione;
        this.linkCall = linkCall;
        this.dataCall = dataCall;
    }

    public RichiestaSupporto() {
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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
}
