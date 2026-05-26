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

    @PostMapping("/add/{idMentore}")
    public ResponseEntity<Segnalazione> segnalaTeam(@RequestBody SegnalazioneDTO segnalazioneDTO) {
        return ResponseEntity.ok(segnalazioneService.createSegnalazione(segnalazioneDTO));
    }

    @GetMapping("/{idSegnalazione}")
    public ResponseEntity<Segnalazione> visualizzaDettaglioSegnalazione(@PathVariable Long idSegnalazione){
        return ResponseEntity.ok(segnalazioneService.getSegnalazioneById(idSegnalazione));
    }

    @PutMapping("/modifica/{idSegnalazione}")
    public ResponseEntity<Segnalazione> modificaSegnalazione(@PathVariable Long idSegnalazione, @RequestBody String descrizione){
        Segnalazione segnalazioneAggiornata = segnalazioneService.modificaSegnalazione(idSegnalazione, descrizione);

        return ResponseEntity.ok(segnalazioneAggiornata);
    }

    @DeleteMapping("/elimina/{idSegnalazione}")
    public ResponseEntity<String> eliminaSegnalazione(@PathVariable Long idSegnalazione){
        segnalazioneService.eliminaSegnalazione(idSegnalazione);

        return ResponseEntity.ok("Segnalazione eliminata con successo.");
    }

}
