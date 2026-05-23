package it.unicam.cs.ids.hackhub.controller;


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
    public ResponseEntity<Organizzatore> createOrganizzatore(@RequestBody Organizzatore organizzatore) {
        return ResponseEntity.ok(membroStaffService.createOrganizzatore(organizzatore));
    }


    @PostMapping("/mentore")
    public ResponseEntity<Mentore> createMentore(@RequestBody Mentore mentore) {
        return ResponseEntity.ok(membroStaffService.createMentore(mentore));
    }

    @PostMapping("/giudice")
    public ResponseEntity<Giudice> createGiudice(@RequestBody Giudice giudice) {
        return ResponseEntity.ok(membroStaffService.createGiudice(giudice));
    }





}
