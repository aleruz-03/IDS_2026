package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.controller.DTO.LoginDTO;
import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.repository.UtenteRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;

    @Autowired
    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }


    public Utente createUtente(Utente utente){
        return utenteRepository.save(new Utente(utente.getNome(), utente.getCognome(), utente.getEmail(), utente.getPassword()));

    }

    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    public boolean deleteUtente(Long id){
        if (utenteRepository.existsById(id)) {
            utenteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Utente login(LoginDTO loginDTO){
        Utente utente = utenteRepository.findByEmail(loginDTO.email());

        if(utente != null && utente.getPassword().equals(loginDTO.password())){
            return utente;
        }

        throw new IllegalArgumentException("Credenziali non valide: email o password errate.");
    }
}
