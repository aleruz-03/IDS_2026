package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.model.Invito;
import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.model.Utente;
import it.unicam.cs.ids.hackhub.repository.InvitoRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import it.unicam.cs.ids.hackhub.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitoService {

    private final InvitoRepository invitoRepository;
    private final UtenteRepository utenteRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public InvitoService(InvitoRepository invitoRepository, UtenteRepository utenteRepository, TeamRepository teamRepository) {
        this.invitoRepository = invitoRepository;
        this.utenteRepository = utenteRepository;
        this.teamRepository = teamRepository;
    }



    public Invito invioInvito(Long idMittente,Long idDestinatario, Long idTeam) {
        Utente mittente = utenteRepository.getUtenteById(idMittente);
        Utente destinatario = utenteRepository.getUtenteById(idDestinatario);
        Team team = teamRepository.getTeamById(idTeam);
        Invito invito = new Invito(team,destinatario, mittente);
        return invitoRepository.save(invito);
    }


    public Invito rifiutaInvito(Long idInvito) {
        Invito invito = invitoRepository.getInvitoById(idInvito);
        invitoRepository.delete(invito);
        return invito;
    }


    public Team accettaInvito(Long idInvito) {
        Invito invito = invitoRepository.getInvitoById(idInvito);
        Team team = teamRepository.getTeamById(invito.getTeam().getId());
        team.getPartecipanti().add(invito.getDestinatario());
        invitoRepository.delete(invito);
        return teamRepository.save(team);
    }

    public List<Invito> getAllInviti() {
        return invitoRepository.findAll();
    }

    public List<Invito> getPropriInviti(Long idUtente){
        Utente utente= utenteRepository.getUtenteById(idUtente);
        return invitoRepository.getAllInvitoByMittente(utente);
    }


}
