package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.SegnalazioneDTO;
import it.unicam.cs.ids.hackhub.model.Segnalazione;
import it.unicam.cs.ids.hackhub.service.SegnalazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/segnalazioni")
public class SegnalazioneController {

    private final SegnalazioneService segnalazioneService;

    @Autowired
    public SegnalazioneController(SegnalazioneService segnalazioneService) {
        this.segnalazioneService = segnalazioneService;
    }

    @GetMapping("/organizzatore/{idOrganizzatore}")
    public ResponseEntity<List<Segnalazione>> visualizzaSegnalazioniOrganizzatore(@PathVariable Long idOrganizzatore) {
        return ResponseEntity.ok(segnalazioneService.getAllSegnalazioniOfOrganizzatore(idOrganizzatore));
    }

    @GetMapping("/mentore/{idMentore}")
    public ResponseEntity<List<Segnalazione>> visualizzaSegnalazioniMentore(@PathVariable Long idMentore) {
        return ResponseEntity.ok(segnalazioneService.getAllSegnalazioneOfMentore(idMentore));
    }

    @GetMapping("/{idSegnalazione}")
    public ResponseEntity<Segnalazione> visualizzaDettaglioSegnalazione(@PathVariable Long idSegnalazione){
        return ResponseEntity.ok(segnalazioneService.getSegnalazioneById(idSegnalazione));
    }

    @PostMapping("/add/{idMentore}")
    public ResponseEntity<String> segnalaTeam(@RequestBody SegnalazioneDTO segnalazioneDTO) {
        try {
            segnalazioneService.createSegnalazione(segnalazioneDTO);
            return ResponseEntity.status(201).body("Segnalazione creata con successo dal mentore con ID " + segnalazioneDTO.idMentore() + ".");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore nella creazione della segnalazione: " + e.getMessage());
        }
    }

    @PutMapping("/modifica/{idSegnalazione}")
    public ResponseEntity<String> modificaSegnalazione(@PathVariable Long idSegnalazione, @RequestBody String descrizione){
        try {
            segnalazioneService.modificaSegnalazione(idSegnalazione, descrizione);
            return ResponseEntity.ok("Segnalazione con ID " + idSegnalazione + " modificata correttamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Impossibile modificare: " + e.getMessage());
        }
    }

    @DeleteMapping("/elimina/{idSegnalazione}")
    public ResponseEntity<String> eliminaSegnalazione(@PathVariable Long idSegnalazione){
        try {
            segnalazioneService.eliminaSegnalazione(idSegnalazione);
            return ResponseEntity.ok("Segnalazione con ID " + idSegnalazione + " eliminata con successo.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Impossibile eliminare: segnalazione non trovata.");
        }
    }

}
