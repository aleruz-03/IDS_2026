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
    public MembroStaffController(MembroStaffService membroStaffService) {this.membroStaffService = membroStaffService;}

    @PostMapping("/organizzatore")
    public ResponseEntity<Organizzatore> createOrganizzatore(@RequestBody CreazioneMembroStaffDTO organizzatoreDTO) {
        return ResponseEntity.ok(membroStaffService.createOrganizzatore(organizzatoreDTO));
    }


    @PostMapping("/mentore")
    public ResponseEntity<Mentore> createMentore(@RequestBody CreazioneMembroStaffDTO mentoreDTO) {
        return ResponseEntity.ok(membroStaffService.createMentore(mentoreDTO));
    }

    @PostMapping("/giudice")
    public ResponseEntity<Giudice> createGiudice(@RequestBody CreazioneMembroStaffDTO giudiceDTO) {
        return ResponseEntity.ok(membroStaffService.createGiudice(giudiceDTO));
    }





}
