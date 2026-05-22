package it.unicam.cs.ids.hackhub.model;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy =  InheritanceType.JOINED)
public class MembroDelloStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Hackathon hackathon;

public MembroDelloStaff(){}
}
