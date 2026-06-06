package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.LoginDTO;
import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createUtente(@RequestBody Utente utente) {
        try {
            Utente nuovoUtente = utenteService.createUtente(utente);
            return ResponseEntity.status(201).body("Utente '" + nuovoUtente.getNome() + " " + nuovoUtente.getCognome() + " " + nuovoUtente.getId() + "' registrato con successo!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Errore durante la registrazione: " + e.getMessage());
        }
    }

    @GetMapping("/utenti")
    public ResponseEntity<List<Utente>> getUtenti() {
        return ResponseEntity.ok(utenteService.getAllUtenti());
    }

    @DeleteMapping("/elimina/{userId}")
    public ResponseEntity<String> deleteUtente(@PathVariable Long userId) {
        boolean eliminato = utenteService.deleteUtente(userId);
        if (eliminato) {
            return ResponseEntity.ok("Utente con ID " + userId + " eliminato con sucesso.");
        } else {
            return ResponseEntity.status(404).body("Impossibile eliminare: utente non trovato.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            Utente utente = utenteService.login(loginDTO);

            return ResponseEntity.ok("Login effettuato con successo! Benvenuto " + utente.getNome() + ".");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(401).body("Errore di autenticazione: " + e.getMessage());
        }
    }



}
