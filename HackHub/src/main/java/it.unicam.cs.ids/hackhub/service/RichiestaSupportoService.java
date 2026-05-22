package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Mentore;
import it.unicam.cs.ids.hackhub.model.StatoHackathon;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.model.RichiestaSupporto;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.repository.RichiestaSupportoRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RichiestaSupportoService {

    @Autowired
    private RichiestaSupportoRepository richiestaSupportoRepository;

    @Autowired
    private HackathonRepository hackathonRepository;

    @Autowired
    private TeamRepository teamRepository;

    public List<Mentore> ottieniMentoriDisponibili(Long idHackathon) {
        Hackathon hackathon = hackathonRepository.findById(idHackathon)
                .orElseThrow(() -> new RuntimeException(("Hackathon non trovato")));

        if (hackathon.getStato() != StatoHackathon.IN_CORSO) {
            throw new IllegalStateException("Impossibile richiedere supporto: l'hackathon non è in corso");
        }

        return hackathon.getMentori();
    }

    @Transactional
    public RichiestaSupporto creaRichiestaSupporto(Long idTeam, String descrizione){
        Team team = teamRepository.findById(idTeam)
                .orElseThrow(() -> new RuntimeException(("Team non trovato")));

        RichiestaSupporto nuovaRichiesta = new RichiestaSupporto(descrizione, null, null);

        return richiestaSupportoRepository.save(nuovaRichiesta);
    }

}
