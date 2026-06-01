package it.unicam.cs.ids.hackhub.controller;


import it.unicam.cs.ids.hackhub.controller.DTO.InvitoDTO;
import it.unicam.cs.ids.hackhub.model.Invito;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.service.InvitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invito")
public class InvitoController {

    private final InvitoService invitoService;

    @Autowired
    public InvitoController(InvitoService invitoService) {
        this.invitoService = invitoService;
    }


    @PostMapping("/crea/{idMittente}")
    public ResponseEntity<String> creaInvito(@PathVariable Long idMittente, @RequestBody InvitoDTO invitoDTO){
        try {
            Invito nuovoInvito = invitoService.invioInvito(idMittente, invitoDTO.idDestinatario(), invitoDTO.idTeam());
            return ResponseEntity.status(201).body("Invito inviato con successo! ID Invito: " + nuovoInvito.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore nella creazione dell'invito: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body("Impossibile inviare l'invito: " + e.getMessage());
        }
    }

    @PostMapping("/accetta/{idInvito}")
    public ResponseEntity<String> accettaInvito(@PathVariable Long idInvito){
        try {
            Team teamAggiornato = invitoService.accettaInvito(idInvito);
            return ResponseEntity.ok("Invito accettato con successo! L'utente è entrato a far parte del team '" + teamAggiornato.getName() + "'.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore: Invito non trovato.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body("Impossibile accettare l'invito: " + e.getMessage());
        }
    }

    @DeleteMapping("/rifiuta/{idInvito}")
    public ResponseEntity<String> rifiutaInvito(@PathVariable Long idInvito){
        try {
            invitoService.rifiutaInvito(idInvito);
            return ResponseEntity.ok("Invito con ID " + idInvito + " rifiutato e rimosso correttamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Impossibile rifiutare: invito non trovato.");
        }
    }

    @GetMapping("/inviti")
    public ResponseEntity<List<Invito>> getAllInviti(){
        return  ResponseEntity.ok(invitoService.getAllInviti());
    }

}
