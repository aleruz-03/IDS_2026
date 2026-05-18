package com.example.hackhub.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
public class RichiestaSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String descrizione;

    private String linkCall;

    private Date dataCall;

    public RichiestaSupport(String descrizione, String linkCall, Date dataCall) {
        this.descrizione = descrizione;
        this.linkCall = linkCall;
        this.dataCall = dataCall;
    }

    public RichiestaSupport() {
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
