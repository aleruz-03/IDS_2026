package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.SegnalazioneDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.SegnalazioneResponseDTO;
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
    public ResponseEntity<List<SegnalazioneResponseDTO>> visualizzaSegnalazioniOrganizzatore(@PathVariable Long idOrganizzatore) {
        return ResponseEntity.ok(segnalazioneService.getAllSegnalazioniOfOrganizzatore(idOrganizzatore).stream()
                .map(SegnalazioneResponseDTO::fromSegnalazione)
                .toList());
    }

    @GetMapping("/mentore/{idMentore}")
    public ResponseEntity<List<SegnalazioneResponseDTO>> visualizzaSegnalazioniMentore(@PathVariable Long idMentore) {
        return ResponseEntity.ok(segnalazioneService.getAllSegnalazioneOfMentore(idMentore).stream()
                .map(SegnalazioneResponseDTO::fromSegnalazione)
                .toList());
    }

    @GetMapping("/{idSegnalazione}")
    public ResponseEntity<SegnalazioneResponseDTO> visualizzaDettaglioSegnalazione(@PathVariable Long idSegnalazione){
        Segnalazione segnalazione = segnalazioneService.getSegnalazioneById(idSegnalazione);
        return ResponseEntity.ok(SegnalazioneResponseDTO.fromSegnalazione(segnalazione));
    }

    @PostMapping("/add/{idMentore}")
    public ResponseEntity<String> segnalaTeam(@PathVariable Long idMentore, @RequestBody SegnalazioneDTO segnalazioneDTO) {
        try {
            segnalazioneService.createSegnalazione(idMentore, segnalazioneDTO);
            return ResponseEntity.status(201).body("Segnalazione creata con successo dal mentore con ID " + idMentore + ".");
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


    @DeleteMapping("/segnalazione/{idSegnalazione}/nonFondata/{idOrganizzatore}")
    public ResponseEntity<String> segnalazioneNonFondata(@PathVariable Long idSegnalazione, @PathVariable Long idOrganizzatore){
        try {
            segnalazioneService.segnalazioneNonFondata(idSegnalazione, idOrganizzatore);
            return ResponseEntity.ok("Segnalazione con ID " + idSegnalazione + " gestita con successo.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Impossibile eliminare: segnalazione non trovata.");
        }
    }

    @PutMapping("/segnalazione/{idSegnalazione}/bannaTeam/{idOrganizzatore}")
    public ResponseEntity<String> bannaTeam(@PathVariable Long idSegnalazione, @PathVariable Long idOrganizzatore, @RequestBody Long idHackathon){
        try {
            segnalazioneService.bannaTeam(idSegnalazione, idOrganizzatore, idHackathon);
            return ResponseEntity.ok("Segnalazione con ID " + idSegnalazione + " gestita con successo.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Impossibile eliminare: segnalazione non trovata.");
        }
    }



}
