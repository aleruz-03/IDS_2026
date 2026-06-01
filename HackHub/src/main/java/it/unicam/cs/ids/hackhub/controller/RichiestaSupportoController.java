package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.GestioneSupportoDTO;
import it.unicam.cs.ids.hackhub.model.RichiestaSupporto;
import it.unicam.cs.ids.hackhub.service.RichiestaSupportoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supporto")
public class RichiestaSupportoController {

    private final RichiestaSupportoService richiestaSupportoService;

    @Autowired
    public RichiestaSupportoController(RichiestaSupportoService richiestaSupportoService) {
        this.richiestaSupportoService = richiestaSupportoService;
    }

    /**@GetMapping("/mentori/{idHackathon}")
    public ResponseEntity<?> richiediMentori(@PathVariable Long idHackathon) {
        try {
            List<Mentore> mentori = richiestaSupportoService.ottieniMentoriDisponibili(idHackathon);
            return ResponseEntity.ok(mentori);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }**/

    @PostMapping("/crea/{idTeam}")
    public ResponseEntity<String> creaRichiesta(@PathVariable Long idTeam, @RequestParam String descrizione){
        try {
            richiestaSupportoService.creaRichiestaSupporto(idTeam, descrizione);
            return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di supporto creata con successo per il team con ID " + idTeam + ".");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Errore nella creazione: " + e.getMessage());
        }
    }

    @GetMapping("/mentore/{idMentore}/tutte")
    public ResponseEntity<?> visualizzaTutteLeRichieste(@PathVariable Long idMentore) {
        List<RichiestaSupporto> richieste = richiestaSupportoService.getRichiesteTotaliMentore(idMentore);

        if (richieste.isEmpty()){
            return ResponseEntity.ok().body("Notifica: Non sono presenti richieste...");
        }

        return ResponseEntity.ok(richieste);
    }

    @GetMapping("/mentore/{idMentore}/da-evadere")
    public ResponseEntity<?> visualizzaRichiesteDaEvadere(@PathVariable Long idMentore){
        List<RichiestaSupporto> richieste = richiestaSupportoService.getRichiestePerMentore(idMentore);
        if (richieste.isEmpty()){
            return ResponseEntity.ok().body("Notifica: Ottimo lavoro! Non hai richieste di supporto in attesa.");
        }

        return ResponseEntity.ok(richieste);
    }

    @GetMapping("/dettaglio/{idRichiesta}")
    public ResponseEntity<RichiestaSupporto> visualizzaDettaglioRichiesta(@PathVariable Long idRichiesta){
        RichiestaSupporto dettaglio = richiestaSupportoService.getDettaglioRichiesta(idRichiesta);
        return ResponseEntity.ok(dettaglio);
    }

    @PutMapping("/mentore/{idMentore}/risolvi/{idRichiesta}")
    public ResponseEntity<String> risolviRichiesta(
            @PathVariable Long idMentore,
            @PathVariable Long idRichiesta,
            @RequestBody GestioneSupportoDTO dto
    ){
        try {
            richiestaSupportoService.rispondiERisolviRichiesta(idRichiesta, idMentore, dto);
            return ResponseEntity.ok("La richiesta di supporto con ID " + idRichiesta + " è stata contrassegnata come RISOLTA dal mentore.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Impossibile risolvere la richiesta: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore nella gestione della richiesta: " + e.getMessage());
        }
    }
}
