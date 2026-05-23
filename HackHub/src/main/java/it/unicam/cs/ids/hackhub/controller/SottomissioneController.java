package it.unicam.cs.ids.hackhub.controller;

import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.service.SottomissioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sottomissioni")
public class SottomissioneController {

    @Autowired
    private SottomissioneService sottomissioneService;

    @GetMapping
    public ResponseEntity<List<Sottomissione>> visualizzaSottomissioni(){
        List<Sottomissione> sottomissioniList = sottomissioneService.getAllSottomissione();

        if (sottomissioniList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(sottomissioniList);
    }
}
