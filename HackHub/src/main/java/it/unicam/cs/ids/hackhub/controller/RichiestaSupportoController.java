package it.unicam.cs.ids.hackhub.controller;

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

    @Autowired
    private RichiestaSupportoService richiestaSupportoService;

    @GetMapping("/mentori/{idHackathon}")
    public ResponseEntity<?> richiediMentori(@PathVariable Long idHackathon) {
        try {
            List<Mentore> mentori = richiestaSupportoService.ottieniMentoriDisponibili(idHackathon);
            return ResponseEntity.ok(mentori);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/crea/{idTeam}")
    public ResponseEntity<RichiestaSupporto> creaRichiesta(@PathVariable Long idTeam, @RequestParam String descrizione){
        RichiestaSupporto richiesta = richiestaSupportoService.creaRichiestaSupporto(idTeam, descrizione);
        return ResponseEntity.status(HttpStatus.CREATED).body(richiesta);
    }
}
