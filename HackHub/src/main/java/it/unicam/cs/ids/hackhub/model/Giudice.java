package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

@Entity
public class Giudice extends MembroDelloStaff {
    public Giudice() {
        super();
    }

    public Giudice(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password);
    }
}
