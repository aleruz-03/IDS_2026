package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.controller.DTO.GestioneSupportoDTO;
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

    private final RichiestaSupportoRepository richiestaSupportoRepository;
    private final GestoreCallService gestoreCallService;
    private final HackathonRepository hackathonRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public RichiestaSupportoService(RichiestaSupportoRepository richiestaSupportoRepository, GestoreCallService gestoreCallService, HackathonRepository hackathonRepository, TeamRepository teamRepository) {
        this.richiestaSupportoRepository = richiestaSupportoRepository;
        this.gestoreCallService = gestoreCallService;
        this.hackathonRepository = hackathonRepository;
        this.teamRepository = teamRepository;
    }

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

        RichiestaSupporto nuovaRichiesta = new RichiestaSupporto(descrizione, null, null, null);

        return richiestaSupportoRepository.save(nuovaRichiesta);
    }

    /**
     * Recupera lo storico totale delle richieste di supporto assegnate a un determinato mentore.
     * Risponde al caso d'uso: VisualizzaRichiesteSupporto.
     *
     * @param idMentore l'ID del mentore di cui si vogliono recuperare le richieste
     * @return una lista contenente tutte le richieste di supporto associate al mentore
     */
    public List<RichiestaSupporto> getRichiesteTotaliMentore(Long idMentore){
        return richiestaSupportoRepository.findByMentoreId(idMentore);
    }

    /**
     * Recupera l'elenco delle sole richieste di supporto non ancora evase per un mentore.
     * Risponde al punto 2 del flusso del caso d'uso: GestisceRichiestaSupporto.
     *
     * @param idMentore l'ID del mentore incaricato della gestione
     * @return una lista delle richieste di supporto attualmente aperte e non evase
     */
    public List<RichiestaSupporto> getRichiestePerMentore(Long idMentore) {
        return richiestaSupportoRepository.findByMentoreIdAndEvasaFalse(idMentore);
    }

    /**
     * Mostra nel dettaglio i dati di una specifica richiesta di supporto tramite il suo ID.
     * Risponde al punto 4 del flusso del caso d'uso: GestisceRichiestaSupporto.
     *
     * @param idRichiesta l'ID della richiesta di supporto da visualizzare
     * @return l'entità RichiestaSupporto corrispondente all'ID fornito
     * @throws RuntimeException se non viene trovata alcuna richiesta con l'ID specificato
     */
    public RichiestaSupporto getDettaglioRichiesta(Long idRichiesta){
        return richiestaSupportoRepository.findById(idRichiesta)
                .orElseThrow(() -> new RuntimeException(("Richiesta di supporto non trovata con ID: " + idRichiesta)));
    }

    @Transactional
    public RichiestaSupporto rispondiERisolviRichiesta(Long idRichiesta, Long idMentore, GestioneSupportoDTO dto) {
        RichiestaSupporto richiesta = richiestaSupportoRepository.findById(idRichiesta)
                .orElseThrow(() -> new RuntimeException("Richiesta non trovata"));

        if(!richiesta.getMentore().getId().equals(idMentore)){
            throw  new RuntimeException("Operazione negata: non sei il mentore incaricato di questa richiesta!");
        }

        if (richiesta.isEvasa()) {
            throw new RuntimeException("Questa richiesta è già stata evasa.");
        }

        if (dto.dataCall() != null) {
            richiesta.setDataCall(dto.dataCall());

            this.gestoreCallService.inviaLinkCall(richiesta);
        }

        richiesta.setRispostaMentore((dto.rispostaMentore()));
        richiesta.setEvasa(true);

        return richiestaSupportoRepository.save(richiesta);
    }
}
