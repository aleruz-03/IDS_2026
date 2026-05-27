package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.AggiungiMentoreDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.HackathonResponseDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.ModificaHackathonDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.creazioneHackathonDTO;
import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.StatoHackathon;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.service.HackathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hackathon")
public class HackathonController {

    @Autowired
    private HackathonService hackathonService;
    private HackathonRepository hr;

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
    public ResponseEntity<HackathonResponseDTO> createHackathon(@PathVariable Long idOrganizzatore, @RequestBody creazioneHackathonDTO hackathonDTO){
        Hackathon hackathon = hackathonService.createHackathon(idOrganizzatore, hackathonDTO);
        return ResponseEntity.ok(HackathonResponseDTO.fromHackathon(hackathon));
    }

    @PostMapping("/aggiungiMentore/{idOrganizzatore}")
    public ResponseEntity<HackathonResponseDTO> aggiungiMentore(@PathVariable Long idOrganizzatore, @RequestBody AggiungiMentoreDTO aggiungiMentoreDTO ){
        Hackathon hackathon = hackathonService.aggiungiMentoreAllHackthon(idOrganizzatore,aggiungiMentoreDTO);
        return ResponseEntity.ok(HackathonResponseDTO.fromHackathon(hackathon));
    }

    @PutMapping("/organizzatore/{idOrganizzatore}/modifica/{idHackathon}")
    public ResponseEntity<HackathonResponseDTO> modificaHackathon(@PathVariable Long idOrganizzatore,@PathVariable Long idHackathon,@RequestBody ModificaHackathonDTO dto){
        Hackathon hackathonAggiornato = hackathonService.modificaHackathon(idOrganizzatore,idHackathon,dto);
        return ResponseEntity.ok(HackathonResponseDTO.fromHackathon(hackathonAggiornato));
    }

    //test nel body "NOME_STATO"
    @PutMapping("/stato/{idHackathon}")
    public ResponseEntity<Hackathon> cambiaStato(@PathVariable Long idHackathon, @RequestBody StatoHackathon stato){
        Hackathon hack = hr.getHackathonById(idHackathon);
        hack.setStato(stato);
        hr.save(hack);
        return ResponseEntity.ok(hack);
    }
}
