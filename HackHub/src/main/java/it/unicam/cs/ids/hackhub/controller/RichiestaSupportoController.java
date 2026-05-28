package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.GestioneSupportoDTO;
import it.unicam.cs.ids.hackhub.model.Mentore;
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
    public ResponseEntity<RichiestaSupporto> creaRichiesta(@PathVariable Long idTeam, @RequestParam String descrizione){
        RichiestaSupporto richiesta = richiestaSupportoService.creaRichiestaSupporto(idTeam, descrizione);
        return ResponseEntity.status(HttpStatus.CREATED).body(richiesta);
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
    public ResponseEntity<RichiestaSupporto> risolviRichiesta(
            @PathVariable Long idMentore,
            @PathVariable Long idRichiesta,
            @RequestBody GestioneSupportoDTO dto
    ){
        RichiestaSupporto aggiornata = richiestaSupportoService.rispondiERisolviRichiesta(idRichiesta, idMentore, dto);
        return ResponseEntity.ok(aggiornata);
    }
}
