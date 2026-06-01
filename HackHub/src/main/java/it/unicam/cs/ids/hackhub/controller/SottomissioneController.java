package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.AllValutazioniDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.SottomissioneDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.ValutazioneDTO;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Valutazione;
import it.unicam.cs.ids.hackhub.service.SottomissioneService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Sottomissione>> visualizzaSottomissioni(){
        List<Sottomissione> sottomissioniList = sottomissioneService.getAllSottomissione();

        if (sottomissioniList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(sottomissioniList);
    }

    @PostMapping("/invia")
    public ResponseEntity<String> inviaSottomissione(@RequestBody SottomissioneDTO sottomissioneDTO){
        try {
            sottomissioneService.createSottomissione(sottomissioneDTO);
            return ResponseEntity.status(201).body("Sottomissione inviata con successo per il team selezionato!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore nell'invio: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body("Impossibile inviare: " + e.getMessage());
        }
    }


    @PutMapping("/aggiorna/{idSottomissione}")
    public ResponseEntity<String> aggiornaSottomissione(@PathVariable Long idSottomissione, @RequestBody SottomissioneDTO sottomissioneDTO){
        try {
            sottomissioneService.aggiornaSottomissione(idSottomissione, sottomissioneDTO);
            return ResponseEntity.ok("Sottomissione con ID " + idSottomissione + " aggiornata correttamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore nell'aggiornamento: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body("Impossibile modificare il progetto: " + e.getMessage());
        }
    }

    @PostMapping("/valuta/{idGiudice}")
    public ResponseEntity<String> valutaSottomissione(@PathVariable Long idGiudice, @RequestBody ValutazioneDTO valutazioneDTO){
        try {
            sottomissioneService.valutaSottomissione(idGiudice, valutazioneDTO);
            return ResponseEntity.status(201).body("Valutazione registrata con successo dal giudice con ID " + idGiudice + ".");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore nella valutazione: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body("Impossibile valutare: " + e.getMessage());
        }
    }

    @GetMapping("/valutazioni")
    public ResponseEntity<List<AllValutazioniDTO>> getAllValutazioni(){
        List<Valutazione> valutazioni = sottomissioneService.getAllValutazioni();

        return ResponseEntity.ok(valutazioni.stream()
                .map(AllValutazioniDTO::fromValutazione)
                .toList());
    }
}
