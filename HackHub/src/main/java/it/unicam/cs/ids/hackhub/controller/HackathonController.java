package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.*;
import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.service.HackathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hackathon")
public class HackathonController {


    private final HackathonService hackathonService;

    @Autowired
    public HackathonController(HackathonService hackathonService) {
        this.hackathonService = hackathonService;
    }

    @GetMapping
    public ResponseEntity<List<HackathonResponseDTO>> visualizzaHackathon(){
        List<Hackathon> hackathonList = hackathonService.getAllHackathons();

        if (hackathonList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<HackathonResponseDTO> response = hackathonList.stream()
                .map(HackathonResponseDTO::fromHackathon)
                .toList();

        return ResponseEntity.ok(response);
    }


    @PostMapping("/crea/{idOrganizzatore}")
    public ResponseEntity<String> createHackathon(@PathVariable Long idOrganizzatore, @RequestBody creazioneHackathonDTO hackathonDTO){
        try {
            Hackathon hackathon = hackathonService.createHackathon(idOrganizzatore, hackathonDTO);
            return ResponseEntity.status(201).body("Hackathon '" + hackathon.getName() + "' creato con successo! ID: " + hackathon.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore nella creazione: " + e.getMessage());
        }
    }

    @PostMapping("/aggiungiMentore/{idOrganizzatore}")
    public ResponseEntity<String> aggiungiMentore(@PathVariable Long idOrganizzatore, @RequestBody AggiungiMentoreDTO aggiungiMentoreDTO ){
        try {
            hackathonService.aggiungiMentoreAllHackthon(idOrganizzatore, aggiungiMentoreDTO);
            return ResponseEntity.ok("Mentore aggiunto con successo all'hackathon.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body("Azione non consentita: " + e.getMessage());
        }
    }

    @PutMapping("/organizzatore/{idOrganizzatore}/modifica/{idHackathon}")
    public ResponseEntity<String> modificaHackathon(@PathVariable Long idOrganizzatore,@PathVariable Long idHackathon,@RequestBody ModificaHackathonDTO dto){
        try {
            hackathonService.modificaHackathon(idOrganizzatore, idHackathon, dto);
            return ResponseEntity.ok("Hackathon con ID " + idHackathon + " modificato correttamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore di modifica: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body("Impossibile modificare l'hackathon nel suo stato attuale: " + e.getMessage());
        }
    }

    @PostMapping("/vincitore/{idOrganizzatore}")
    public ResponseEntity<String> proclamaVincitore(@PathVariable Long idOrganizzatore, @RequestBody ProclamaVincitoreDTO proclamaVincitoreDTO){
        try {
            hackathonService.proclamaVincitore(idOrganizzatore, proclamaVincitoreDTO.idTeam(), proclamaVincitoreDTO.idHackathon());
            return ResponseEntity.ok("Complimenti! Il vincitore dell'hackathon è stato proclamato con successo.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore nella proclamazione: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body("Impossibile proclamare un vincitore ora: " + e.getMessage());
        }
    }

    //test nel body { "stato": "NOME_STATO"}
    @PutMapping("/stato/{idHackathon}")
    public ResponseEntity<String> cambiaStato(@PathVariable Long idHackathon, @RequestBody StatoDTO stato){
        try {
            Hackathon hackathon = hackathonService.cambiaStato(idHackathon, stato.stato());
            return ResponseEntity.ok("Stato dell'hackathon aggiornato con successo a: " + hackathon.getStato());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Hackathon non trovato.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body("Transizione di stato non valida: " + e.getMessage());
        }    }
}
