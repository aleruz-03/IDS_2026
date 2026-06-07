package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.GestioneSupportoDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.RichiestaSupportoDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.RichiestaSupportoResponseDTO;
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

    @PostMapping("/crea/{idTeam}")
    public ResponseEntity<String> creaRichiesta(@PathVariable Long idTeam, @RequestBody RichiestaSupportoDTO richiestaSupportoDTO) {
        richiestaSupportoService.creaRichiestaSupporto(idTeam, richiestaSupportoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Richiesta di supporto creata con successo per il team con ID " + idTeam + ".");
    }

    @GetMapping("/mentore/{idMentore}/tutte")
    public ResponseEntity<?> visualizzaTutteLeRichieste(@PathVariable Long idMentore) {
        List<RichiestaSupporto> richieste = richiestaSupportoService.getRichiesteTotaliMentore(idMentore);

        if (richieste.isEmpty()) {
            return ResponseEntity.ok().body("Notifica: Non sono presenti richieste...");
        }

        return ResponseEntity.ok(richieste.stream()
                .map(RichiestaSupportoResponseDTO::fromRichiestaSupporto)
                .toList());
    }

    @GetMapping("/mentore/{idMentore}/da-evadere")
    public ResponseEntity<?> visualizzaRichiesteDaEvadere(@PathVariable Long idMentore) {
        List<RichiestaSupporto> richieste = richiestaSupportoService.getRichiestePerMentore(idMentore);
        if (richieste.isEmpty()) {
            return ResponseEntity.ok().body("Notifica: Ottimo lavoro! Non hai richieste di supporto in attesa.");
        }

        return ResponseEntity.ok(richieste.stream()
                .map(RichiestaSupportoResponseDTO::fromRichiestaSupporto)
                .toList());
    }

    @GetMapping("/team/{idTeam}/tutte")
    public ResponseEntity<List<RichiestaSupportoResponseDTO>> visualizzaRichiesteTeam(@PathVariable Long idTeam) {
        List<RichiestaSupporto> richieste = richiestaSupportoService.getRichiesteTeam(idTeam);
        return ResponseEntity.ok(richieste.stream()
                .map(RichiestaSupportoResponseDTO::fromRichiestaSupporto)
                .toList());
    }

    @GetMapping("/dettaglio/{idRichiesta}")
    public ResponseEntity<RichiestaSupportoResponseDTO> visualizzaDettaglioRichiesta(@PathVariable Long idRichiesta) {
        RichiestaSupporto dettaglio = richiestaSupportoService.getDettaglioRichiesta(idRichiesta);
        return ResponseEntity.ok(RichiestaSupportoResponseDTO.fromRichiestaSupporto(dettaglio));
    }

    @PutMapping("/mentore/{idMentore}/risolvi/{idRichiesta}")
    public ResponseEntity<String> risolviRichiesta(
            @PathVariable Long idMentore,
            @PathVariable Long idRichiesta,
            @RequestBody GestioneSupportoDTO gestioneSupportoDTO
    ) {
        richiestaSupportoService.rispondiERisolviRichiesta(idRichiesta, idMentore, gestioneSupportoDTO);
        return ResponseEntity.ok("La richiesta di supporto con ID " + idRichiesta + " e' stata contrassegnata come RISOLTA dal mentore.");
    }
}
