package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.*;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Valutazione;
import it.unicam.cs.ids.hackhub.service.SottomissioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sottomissioni")
public class SottomissioneController {

    private final SottomissioneService sottomissioneService;

    @Autowired
    public SottomissioneController(SottomissioneService sottomissioneService) {
        this.sottomissioneService = sottomissioneService;
    }

    @GetMapping
    public ResponseEntity<List<SottomissioneResponseDTO>> visualizzaSottomissioni() {
        List<Sottomissione> sottomissioniList = sottomissioneService.getAllSottomissione();

        List<SottomissioneResponseDTO> response = sottomissioniList.stream()
                .map(SottomissioneResponseDTO::fromSottomissione)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/invia")
    public ResponseEntity<String> inviaSottomissione(@RequestBody SottomissioneDTO sottomissioneDTO) {
        sottomissioneService.createSottomissione(sottomissioneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Sottomissione inviata con successo per il team selezionato!");
    }

    @PutMapping("/aggiorna/{idSottomissione}")
    public ResponseEntity<String> aggiornaSottomissione(@PathVariable Long idSottomissione, @RequestBody SottomissioneDTO sottomissioneDTO) {
        sottomissioneService.aggiornaSottomissione(idSottomissione, sottomissioneDTO);
        return ResponseEntity.ok("Sottomissione con ID " + idSottomissione + " aggiornata correttamente.");
    }

    @PostMapping("/valuta/{idGiudice}")
    public ResponseEntity<String> valutaSottomissione(@PathVariable Long idGiudice, @RequestBody ValutazioneDTO valutazioneDTO) {
        sottomissioneService.valutaSottomissione(idGiudice, valutazioneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Valutazione registrata con successo dal giudice con ID " + idGiudice + ".");
    }

    @GetMapping("/valutazioni")
    public ResponseEntity<List<AllValutazioniDTO>> getAllValutazioni() {
        List<Valutazione> valutazioni = sottomissioneService.getAllValutazioni();

        return ResponseEntity.ok(valutazioni.stream()
                .map(AllValutazioniDTO::fromValutazione)
                .toList());
    }

    @GetMapping("sottomissioneTeam/{idTeam}")
    public ResponseEntity<SottomissioneResponseDTO> getSottomissioneTeam(@PathVariable Long idTeam) {
        Sottomissione sottomissione = sottomissioneService.getSottomissioneOfTeam(idTeam);
        return ResponseEntity.ok(SottomissioneResponseDTO.fromSottomissione(sottomissione));
    }
}
