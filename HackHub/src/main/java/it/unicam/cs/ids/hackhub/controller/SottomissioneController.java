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
    public ResponseEntity<Sottomissione> inviaSottomissione(@RequestBody SottomissioneDTO sottomissioneDTO){
        Sottomissione nuovaSottomissione = sottomissioneService.createSottomissione(sottomissioneDTO);

        return ResponseEntity.ok(nuovaSottomissione);
    }

    @PutMapping("/aggiorna/{idSottomissione}")
    public ResponseEntity<Sottomissione> aggiornaSottomissione(@PathVariable Long idSottomissione, @RequestBody SottomissioneDTO sottomissioneDTO){
        Sottomissione sottomissioneAggiornata = sottomissioneService.aggiornaSottomissione(idSottomissione, sottomissioneDTO);
        return ResponseEntity.ok(sottomissioneAggiornata);
    }

    @PostMapping("/valuta/{idGiudice}")
    public ResponseEntity<Valutazione> valutaSottomissione(@PathVariable Long idGiudice, @RequestBody ValutazioneDTO valutazioneDTO){
        return ResponseEntity.ok(sottomissioneService.valutaSottomissione(idGiudice, valutazioneDTO));
    }

    @GetMapping("/valutazioni")
    public ResponseEntity<List<AllValutazioniDTO>> getAllValutazioni(){
        List<Valutazione> valutazioni = sottomissioneService.getAllValutazioni();

        return ResponseEntity.ok(valutazioni.stream()
                .map(AllValutazioniDTO::fromValutazione)
                .toList());
    }
}
