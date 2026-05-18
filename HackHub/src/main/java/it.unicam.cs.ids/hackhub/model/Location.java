package com.example.hackhub.model;

import jakarta.persistence.*;

@Embeddable
public class Location {

    private String via;
    private String CAP;
    private String civico;
    private String citta;
    private String provincia;

    public Location() {

    }

    public Location(String via,String CAP, String civico, String citta, String provincia) {
        this.via = via;
        this.CAP = CAP;
        this.civico = civico;
        this.citta = citta;
        this.provincia = provincia;
    }
    public String getVia() {
        return this.via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCAP() {
        return this.CAP;
    }

    public void setCap(String CAP) {
        this.CAP = CAP;
    }

    public String getCivico() {
        return this.civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getCitta() {
        return this.citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return this.provincia + "";
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }




}
