package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.model.Segnalazione;
import it.unicam.cs.ids.hackhub.service.SegnalazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/segnalazioni")
public class SegnalazioneController {

    @Autowired
    private SegnalazioneService segnalazioneService;

    @GetMapping
    public ResponseEntity<List<Segnalazione>> visualizzaSegnalazioni() {
        List<Segnalazione> segnalazioniList = segnalazioneService.getAllSegnalazioni();

        if (segnalazioniList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(segnalazioniList);
    }
}
