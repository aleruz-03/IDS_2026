package it.unicam.cs.ids.hackhub.model;


import jakarta.persistence.*;

@Entity
public class Valutazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private int voto;

    private String descrizione;

    @OneToOne
    private Sottomissione sottomissione;

    @ManyToOne
    private Giudice giudice;

    public Valutazione(int voto, String descrizione) {
        this.voto = voto;
        this.descrizione = descrizione;
    }

    public Valutazione() {
    }

    public Long getId() {
        return id;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Giudice getGiudice() {
        return giudice;
    }

    public void setGiudice(Giudice giudice) {
        this.giudice = giudice;
    }

    public Sottomissione getSottomissione() {
        return sottomissione;
    }

    public void setSottomissione(Sottomissione sottomissione) {
        this.sottomissione = sottomissione;
    }
}
