package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.controller.DTO.CreazioneMembroStaffDTO;
import it.unicam.cs.ids.hackhub.model.Giudice;
import it.unicam.cs.ids.hackhub.model.Mentore;
import it.unicam.cs.ids.hackhub.model.Organizzatore;
import it.unicam.cs.ids.hackhub.service.MembroStaffService;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {
            Organizzatore organizzatore = membroStaffService.createOrganizzatore(organizzatoreDTO);
            return ResponseEntity.status(201).body("Organizzatore creato con successo! ID assegnato: " + organizzatore.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore nella creazione dell'organizzatore: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Richiesta non valida: " + e.getMessage());
        }
    }

    @PostMapping("/mentore")
    public ResponseEntity<String> createMentore(@RequestBody CreazioneMembroStaffDTO mentoreDTO) {
        try {
            Mentore mentore = membroStaffService.createMentore(mentoreDTO);
            return ResponseEntity.status(201).body("Mentore creato con successo! ID assegnato: " + mentore.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore nella creazione del mentore: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Richiesta non valida: " + e.getMessage());
        }
    }

    @PostMapping("/giudice")
    public ResponseEntity<String> createGiudice(@RequestBody CreazioneMembroStaffDTO giudiceDTO) {
        try {
            Giudice giudice = membroStaffService.createGiudice(giudiceDTO);
            return ResponseEntity.status(201).body("Giudice creato con successo! ID assegnato: " + giudice.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Errore nella creazione del giudice: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Richiesta non valida: " + e.getMessage());
        }
    }

}
