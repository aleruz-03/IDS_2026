package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

@Entity
public class Organizzatore extends MembroDelloStaff {
    public Organizzatore() {
        super();
    }

    public Organizzatore(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password);
    }
}
