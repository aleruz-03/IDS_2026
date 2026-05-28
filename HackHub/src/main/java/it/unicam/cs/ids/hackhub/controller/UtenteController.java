package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

    private final UtenteService utenteService;

    @Autowired
    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @PostMapping("/crea")
    public ResponseEntity<Utente> createUtente(@RequestBody Utente utente) {
        return ResponseEntity.ok(utenteService.createUtente(utente));
    }

    @GetMapping("/utenti")
    public ResponseEntity<List<Utente>> getUtenti() {
        return ResponseEntity.ok(utenteService.getAllUtenti());
    }


}
