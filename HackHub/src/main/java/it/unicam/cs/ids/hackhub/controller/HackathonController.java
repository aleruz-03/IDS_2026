package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.*;
import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.service.HackathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<HackathonResponseDTO>> visualizzaHackathon() {
        List<Hackathon> hackathonList = hackathonService.getAllHackathons();

        if (hackathonList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<HackathonResponseDTO> response = hackathonList.stream()
                .map(HackathonResponseDTO::fromHackathon)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/crea/{idOrganizzatore}")
    public ResponseEntity<String> createHackathon(@PathVariable Long idOrganizzatore, @RequestBody creazioneHackathonDTO hackathonDTO) {
        Hackathon hackathon = hackathonService.createHackathon(idOrganizzatore, hackathonDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Hackathon '" + hackathon.getName() + "' creato con successo! ID: " + hackathon.getId());
    }

    @PostMapping("/aggiungiMentore/{idOrganizzatore}")
    public ResponseEntity<String> aggiungiMentore(@PathVariable Long idOrganizzatore, @RequestBody AggiungiMentoreDTO aggiungiMentoreDTO) {
        hackathonService.aggiungiMentoreAllHackathon(idOrganizzatore, aggiungiMentoreDTO);
        return ResponseEntity.ok("Mentore aggiunto con successo all'hackathon.");
    }

    @PutMapping("/organizzatore/{idOrganizzatore}/modifica/{idHackathon}")
    public ResponseEntity<String> modificaHackathon(@PathVariable Long idOrganizzatore, @PathVariable Long idHackathon, @RequestBody ModificaHackathonDTO dto) {
        hackathonService.modificaHackathon(idOrganizzatore, idHackathon, dto);
        return ResponseEntity.ok("Hackathon con ID " + idHackathon + " modificato correttamente.");
    }

    @PostMapping("/vincitore/{idOrganizzatore}")
    public ResponseEntity<String> proclamaVincitore(@PathVariable Long idOrganizzatore, @RequestBody ProclamaVincitoreDTO proclamaVincitoreDTO) {
        hackathonService.proclamaVincitore(idOrganizzatore, proclamaVincitoreDTO.idTeam(), proclamaVincitoreDTO.idHackathon());
        return ResponseEntity.ok("Il vincitore dell'hackathon e' stato proclamato con successo.");
    }

    @PutMapping("/stato/{idHackathon}")
    public ResponseEntity<String> cambiaStato(@PathVariable Long idHackathon, @RequestBody StatoDTO stato) {
        Hackathon hackathon = hackathonService.cambiaStato(idHackathon, stato.stato());
        return ResponseEntity.ok("Stato dell'hackathon aggiornato con successo a: " + hackathon.getStato());
    }

    @DeleteMapping("/elimina/{idHackathon}")
    public ResponseEntity<String> eliminaHackathon(@PathVariable Long idHackathon, @RequestBody Long idOrganizzatore) {
        hackathonService.deleteHackathon(idHackathon, idOrganizzatore);
        return ResponseEntity.ok("Hackathon con ID " + idHackathon + " eliminato con successo.");
    }
}
