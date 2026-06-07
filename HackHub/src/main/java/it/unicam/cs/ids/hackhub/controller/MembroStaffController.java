package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.CreazioneMembroStaffDTO;
import it.unicam.cs.ids.hackhub.model.Giudice;
import it.unicam.cs.ids.hackhub.model.Mentore;
import it.unicam.cs.ids.hackhub.model.Organizzatore;
import it.unicam.cs.ids.hackhub.service.MembroStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/membroStaff")
public class MembroStaffController {
    private final MembroStaffService membroStaffService;

    @Autowired
    public MembroStaffController(MembroStaffService membroStaffService) {
        this.membroStaffService = membroStaffService;
    }

    @PostMapping("/organizzatore")
    public ResponseEntity<String> createOrganizzatore(@RequestBody CreazioneMembroStaffDTO organizzatoreDTO) {
        Organizzatore organizzatore = membroStaffService.createOrganizzatore(organizzatoreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Organizzatore creato con successo! ID assegnato: " + organizzatore.getId());
    }

    @PostMapping("/mentore")
    public ResponseEntity<String> createMentore(@RequestBody CreazioneMembroStaffDTO mentoreDTO) {
        Mentore mentore = membroStaffService.createMentore(mentoreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Mentore creato con successo! ID assegnato: " + mentore.getId());
    }

    @PostMapping("/giudice")
    public ResponseEntity<String> createGiudice(@RequestBody CreazioneMembroStaffDTO giudiceDTO) {
        Giudice giudice = membroStaffService.createGiudice(giudiceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Giudice creato con successo! ID assegnato: " + giudice.getId());
    }
}
