package it.unicam.cs.ids.hackhub.model;


import jakarta.persistence.*;

@Entity
public class Valutazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private int voto;


    private String descrizione;

    public Valutazione(int voto, String descrizione) {
        this.voto = voto;
        this.descrizione = descrizione;
    }

    public Valutazione() {
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
}
