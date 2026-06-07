package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.SegnalazioneDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.SegnalazioneResponseDTO;
import it.unicam.cs.ids.hackhub.model.Segnalazione;
import it.unicam.cs.ids.hackhub.service.SegnalazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<SegnalazioneResponseDTO> visualizzaDettaglioSegnalazione(@PathVariable Long idSegnalazione) {
        Segnalazione segnalazione = segnalazioneService.getSegnalazioneById(idSegnalazione);
        return ResponseEntity.ok(SegnalazioneResponseDTO.fromSegnalazione(segnalazione));
    }

    @PostMapping("/add/{idMentore}")
    public ResponseEntity<String> segnalaTeam(@PathVariable Long idMentore, @RequestBody SegnalazioneDTO segnalazioneDTO) {
        segnalazioneService.createSegnalazione(idMentore, segnalazioneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Segnalazione creata con successo dal mentore con ID " + idMentore + ".");
    }

    @PutMapping("/modifica/{idSegnalazione}")
    public ResponseEntity<String> modificaSegnalazione(@PathVariable Long idSegnalazione, @RequestBody String descrizione) {
        segnalazioneService.modificaSegnalazione(idSegnalazione, descrizione);
        return ResponseEntity.ok("Segnalazione con ID " + idSegnalazione + " modificata correttamente.");
    }

    @DeleteMapping("/elimina/{idSegnalazione}")
    public ResponseEntity<String> eliminaSegnalazione(@PathVariable Long idSegnalazione) {
        segnalazioneService.eliminaSegnalazione(idSegnalazione);
        return ResponseEntity.ok("Segnalazione con ID " + idSegnalazione + " eliminata con successo.");
    }

    @DeleteMapping("/segnalazione/{idSegnalazione}/nonFondata/{idOrganizzatore}")
    public ResponseEntity<String> segnalazioneNonFondata(@PathVariable Long idSegnalazione, @PathVariable Long idOrganizzatore) {
        segnalazioneService.segnalazioneNonFondata(idSegnalazione, idOrganizzatore);
        return ResponseEntity.ok("Segnalazione con ID " + idSegnalazione + " gestita con successo.");
    }

    @PutMapping("/segnalazione/{idSegnalazione}/bannaTeam/{idOrganizzatore}")
    public ResponseEntity<String> bannaTeam(@PathVariable Long idSegnalazione, @PathVariable Long idOrganizzatore, @RequestBody Long idHackathon) {
        segnalazioneService.bannaTeam(idSegnalazione, idOrganizzatore, idHackathon);
        return ResponseEntity.ok("Segnalazione con ID " + idSegnalazione + " gestita con successo.");
    }
}
