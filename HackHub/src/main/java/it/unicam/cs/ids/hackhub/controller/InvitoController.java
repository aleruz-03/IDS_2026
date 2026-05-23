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
    public ResponseEntity<Invito> creaInvito(@PathVariable Long idMittente, @RequestBody InvitoDTO invitoDTO){
        return ResponseEntity.ok(invitoService.invioInvito(idMittente, invitoDTO.idDestinatario(), invitoDTO.idTeam()));
    }

    @PostMapping("/accetta/{idInvito}")
    public ResponseEntity<Team> accettaInvito(@PathVariable Long idInvito){
        return ResponseEntity.ok(invitoService.accettaInvito(idInvito));
    }

    @DeleteMapping("/rifiuta/{idInvito}")
    public ResponseEntity<Invito> rifiutaInvito(@PathVariable Long idInvito){
        return ResponseEntity.ok(invitoService.rifiutaInvito(idInvito));
    }

    @GetMapping("/inviti")
    public ResponseEntity<List<Invito>> getAllInviti(){
        return  ResponseEntity.ok(invitoService.getAllInviti());
    }

}
