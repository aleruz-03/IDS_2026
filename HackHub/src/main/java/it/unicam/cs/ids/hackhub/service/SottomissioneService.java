package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.controller.DTO.SottomissioneDTO;
import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.repository.SottomissioneRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SottomissioneService {

    @Autowired
    private SottomissioneRepository sottomissioneRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private HackathonRepository hackathonRepository;

    public List<Sottomissione> getAllSottomissione(){
        return sottomissioneRepository.findAll();
    }

    @Transactional
    public Sottomissione createSottomissione(SottomissioneDTO sottomissioneDTO){
        Team team = teamRepository.findById(sottomissioneDTO.idTeam())
                .orElseThrow(() -> new RuntimeException("Team non trovato con ID: " + sottomissioneDTO.idTeam()));

        Hackathon hackathon = hackathonRepository.findById(sottomissioneDTO.idHackathon())
                .orElseThrow(() -> new RuntimeException("Hackathon non trovato con ID: " +  sottomissioneDTO.idHackathon()));

        checkInvio(hackathon);

        Sottomissione sottomissione = new Sottomissione(
                new Date(),
                sottomissioneDTO.url(),
                sottomissioneDTO.descrizione(),
                team,
                hackathon
        );

        return sottomissioneRepository.save(sottomissione);
    }

    @Transactional
    public Sottomissione aggiornaSottomissione(Long idSottomissione, SottomissioneDTO sottomissioneDTO){
        Sottomissione sottomissione = sottomissioneRepository.findById(idSottomissione)
                .orElseThrow(() -> new RuntimeException("Sottomissione non trovata con ID: " + idSottomissione));

        Hackathon hackathon = sottomissione.getHackathon();

        checkInvio(hackathon);

        sottomissione.setUrl(sottomissioneDTO.url());
        sottomissione.setDescrizione(sottomissioneDTO.descrizione());
        sottomissione.setDataCaricamento(new Date());

        return sottomissioneRepository.save(sottomissione);
    }

    private void checkInvio(Hackathon hackathon){
        Date dataAttuale = new Date();
        if (hackathon.getData_Start() != null && dataAttuale.before(hackathon.getData_Start())){
            throw new RuntimeException("Impossibile completare l'operazione: l'Hackathon non è ancora iniziato!");
        }

        if (hackathon.getData_End() != null && dataAttuale.after(hackathon.getData_End())){
            throw new RuntimeException("Impossibile completare l'operazione: il tempo a disposizione per questo Hackathon è scaduto!");
        }
    }
}
