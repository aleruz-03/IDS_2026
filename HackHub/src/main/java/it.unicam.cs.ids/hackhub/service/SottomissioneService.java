package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.repository.SottomissioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SottomissioneService {

    @Autowired
    private SottomissioneRepository sottomissioneRepository;

    public List<Sottomissione> getAllSottomissione(){
        return sottomissioneRepository.findAll();
    }

}
