package it.unicam.cs.ids.hackhub.model;

import jakarta.persistence.*;

@Entity
public class Mentore extends MembroDelloStaff{
    public Mentore() {
        super();
    }

    public Mentore(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password);
    }


}
