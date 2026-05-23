package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.service.HackathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hackathons")
public class HackathonController {
    @Autowired
    private HackathonService hackathonService;

    @GetMapping
    public ResponseEntity<List<Hackathon>> visualizzaHackathon(){
        List<Hackathon> hackathonList = hackathonService.getAllHackathons();

        if (hackathonList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(hackathonList);
    }
}
