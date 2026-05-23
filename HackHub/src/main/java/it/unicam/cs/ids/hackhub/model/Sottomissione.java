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

    public Sottomissione(Date dataCaricamento, String url, String descrizione) {
        this.dataCaricamento = dataCaricamento;
        this.url = url;
        this.descrizione = descrizione;
    }

    public Sottomissione() {
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
}
