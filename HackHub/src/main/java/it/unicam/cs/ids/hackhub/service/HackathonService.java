package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.controller.DTO.AggiungiMentoreDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.ModificaHackathonDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.creazioneHackathonDTO;
import it.unicam.cs.ids.hackhub.model.*;
import it.unicam.cs.ids.hackhub.model.state.Concluso;
import it.unicam.cs.ids.hackhub.model.state.InCorso;
import it.unicam.cs.ids.hackhub.model.state.InIscrizione;
import it.unicam.cs.ids.hackhub.model.state.InValutazione;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.repository.MembroStaffRepository;
import it.unicam.cs.ids.hackhub.repository.SottomissioneRepository;
import it.unicam.cs.ids.hackhub.repository.TeamRepository;
import it.unicam.cs.ids.hackhub.repository.ValutazioneRepository;
import it.unicam.cs.ids.hackhub.service.adapter.SistemaPagamentoAdapter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HackathonService {


    private final HackathonRepository hackathonRepository;
    private final MembroStaffRepository membroStaffRepository;
    private final TeamRepository teamRepository;
    private final SottomissioneRepository sottomissioneRepository;
    private final ValutazioneRepository valutazioneRepository;
    private final SistemaPagamentoAdapter sistemaPagamentoAdapter;

    @Autowired
    public HackathonService(HackathonRepository hackathonRepository, MembroStaffRepository membroStaffRepository, TeamRepository teamRepository, SottomissioneRepository sottomissioneRepository, ValutazioneRepository valutazioneRepository, SistemaPagamentoAdapter sistemaPagamentoAdapter) {
        this.hackathonRepository = hackathonRepository;
        this.membroStaffRepository = membroStaffRepository;
        this.teamRepository = teamRepository;
        this.sottomissioneRepository = sottomissioneRepository;
        this.valutazioneRepository = valutazioneRepository;
        this.sistemaPagamentoAdapter = sistemaPagamentoAdapter;
    }

    public List<Hackathon> getAllHackathons(){
        return hackathonRepository.findAll();
    }

    public Hackathon createHackathon(Long idOrganizzatore, creazioneHackathonDTO hackathonDTO){
        Organizzatore organizzatore = getMembroStaff(idOrganizzatore, Organizzatore.class);
        Giudice giudice = getMembroStaff(hackathonDTO.idGiudice(), Giudice.class);
        List<Long> idMentori = hackathonDTO.idMentori() == null ? List.of() : hackathonDTO.idMentori();
        List<Mentore> mentori = idMentori.stream()
                .map(idMentore -> getMembroStaff(idMentore, Mentore.class))
                .toList();

        Hackathon hackathon = new Hackathon(
                hackathonDTO.name(),
                hackathonDTO.description(),
                hackathonDTO.scadenzaIscrizioni(),
                hackathonDTO.start(),
                hackathonDTO.end(),
                hackathonDTO.location(),
                hackathonDTO.premioInDenaro(),
                organizzatore,
                giudice,
                mentori
        );

        organizzatore.setHackathon(hackathon);
        giudice.setHackathon(hackathon);
        for(Mentore mentore : mentori){
            mentore.setHackathon(hackathon);
        }

        return hackathonRepository.save(hackathon);
    }

    public Hackathon aggiungiMentoreAllHackthon(Long idOrganizzatore, AggiungiMentoreDTO amdto){
        Hackathon hackathon = hackathonRepository.findById(amdto.idHackathon())
                .orElseThrow(() -> new RuntimeException("Hackathon non trovato con ID: "+ amdto.idHackathon()));

        Mentore mentore = (Mentore) membroStaffRepository.findById(amdto.idMentore())
                .orElseThrow(() -> new RuntimeException("Mentore inesistente con ID: "+ amdto.idMentore()));

        if(hackathon.getMentori().contains(mentore)){
            throw new RuntimeException("Il mentore è già stato inserito in questo hackathon");
        }

        if(!hackathon.getOrganizzatore().getId().equals(idOrganizzatore)){
            throw new RuntimeException("Non è l'organizzatore dell'hackathon");
        }

        hackathon.getMentori().add(mentore);
        return hackathonRepository.save(hackathon);
    }

    private <T extends MembroDelloStaff> T getMembroStaff(Long id, Class<T> tipoMembroStaff) {
        MembroDelloStaff membroDelloStaff = membroStaffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Membro dello staff non trovato con id: " + id));

        if (!tipoMembroStaff.isInstance(membroDelloStaff)) {
            throw new IllegalArgumentException("Il membro dello staff con id " + id + " non e' un " + tipoMembroStaff.getSimpleName());
        }

        return tipoMembroStaff.cast(membroDelloStaff);
    }

    public Hackathon modificaHackathon(Long idOrganizzatore, Long idHackathon, ModificaHackathonDTO dto){
        Hackathon hackathon = hackathonRepository.findById(idHackathon)
                .orElseThrow(() -> new RuntimeException("Hackathon non trovato con ID: " + idHackathon));
        if(!hackathon.getOrganizzatore().getId().equals(idOrganizzatore)){
            throw new RuntimeException("Non è l'organizzatore dell'hackathon");
        }

        hackathon.setName(dto.name() != null ? dto.name() : hackathon.getName());
        hackathon.setDescription(dto.description() != null ? dto.description() : hackathon.getDescription());
        hackathon.setScandezaIscrizioni(dto.scadenzaIscrizioni() != null ? dto.scadenzaIscrizioni() : hackathon.getScandezaIscrizioni());
        hackathon.setData_Start(dto.start() != null ? dto.start() : hackathon.getData_Start());
        hackathon.setEnd(dto.end() != null ? dto.end() : hackathon.getData_End());
        hackathon.setLocation(dto.location() != null ? dto.location() : hackathon.getLocation());
        hackathon.setPremioInDenaro(dto.premioInDenaro() != null ? dto.premioInDenaro() : hackathon.getPremioInDenaro());

        return hackathonRepository.save(hackathon);
    }


    public void proclamaVincitore(Long idOrganizzatore, Long idTeam, Long idHackathon){
        Hackathon hackathon = hackathonRepository.getHackathonById(idHackathon);
        Team team = teamRepository.getTeamById(idTeam);

        if(!hackathon.getOrganizzatore().getId().equals(idOrganizzatore)){
            throw new RuntimeException("Non è l'organizzatore dell'hackathon selezionato");
        }

        if(hackathon.getStato() != StatoHackathon.IN_VALUTAZIONE){
            throw  new  RuntimeException("Hackathon non in fase di valutazione");
        }
        if(!hackathon.getTeams().contains(team)){
            throw  new  RuntimeException("Team non iscritto all'hackathon selezionato");
        }

        if(!checkSottomissioniValutate(hackathon)){
            throw new RuntimeException("Non tutte le sottomissioni sono state valuate dal giudice");
        }

        boolean pagamentoRiuscito = sistemaPagamentoAdapter.erogaPremio(hackathon,team);

        if (!pagamentoRiuscito) {
            throw new RuntimeException("Errore durante l'erogazione del premio");
        }

        hackathon.setTeamVincitore(team);
        hackathonRepository.save(hackathon);

    }


    private Boolean checkSottomissioniValutate(Hackathon hackathon){
        for(Team team  : hackathon.getTeams()){
            if(team.getSottomissione().getValutazione() == null){
                return false;
            }
        }
        return true;
    }

    public Hackathon cambiaStato(Long idHackathon, StatoHackathon stato){
        Hackathon hackathon = hackathonRepository.getHackathonById(idHackathon);

        switch (stato) {
            case ISCRIZIONE -> hackathon.cambiaStato(new InIscrizione());
            case IN_CORSO -> hackathon.cambiaStato(new InCorso());
            case IN_VALUTAZIONE -> hackathon.cambiaStato(new InValutazione());
            case CONCLUSO -> hackathon.cambiaStato(new Concluso());
        }

        return hackathonRepository.save(hackathon);
    }


    @Transactional
    public boolean deleteHackathon(Long idHackathon) {
        Hackathon hackathon = hackathonRepository.findById(idHackathon).orElse(null);
        if (hackathon == null) {
            return false;
        }

        List<Team> teams = hackathon.getTeams() == null ? new ArrayList<>() : new ArrayList<>(hackathon.getTeams());
        for (Team team : teamRepository.findByHackathonContaining(hackathon)) {
            boolean giaPresente = teams.stream()
                    .anyMatch(t -> t.getId() != null && t.getId().equals(team.getId()));
            if (!giaPresente) {
                teams.add(team);
            }
        }
        List<Sottomissione> sottomissioni = sottomissioneRepository.findByHackathon(hackathon);

        hackathon.setTeamVincitore(null);

        List<MembroDelloStaff> membriStaff = membroStaffRepository.findByHackathon(hackathon);
        for (MembroDelloStaff membroStaff : membriStaff) {
            membroStaff.setHackathon(null);
        }
        membroStaffRepository.saveAll(membriStaff);

        for (Team team : teams) {
            if (team.getHackathon() != null) {
                team.getHackathon().removeIf(h -> h != null && idHackathon.equals(h.getId()));
            }
            if (team.getSottomissione() != null && sottomissioni.stream()
                    .anyMatch(s -> s.getId() != null && s.getId().equals(team.getSottomissione().getId()))) {
                team.setSottomissione(null);
            }
        }
        teamRepository.saveAll(teams);
        teamRepository.flush();

        if (hackathon.getMentori() != null) {
            hackathon.getMentori().clear();
        }
        if (hackathon.getTeams() != null) {
            hackathon.getTeams().clear();
        }
        hackathonRepository.save(hackathon);
        hackathonRepository.flush();

        for (Sottomissione sottomissione : sottomissioni) {
            sottomissione.setValutazione(null);
        }
        sottomissioneRepository.saveAll(sottomissioni);
        sottomissioneRepository.flush();

        if (!sottomissioni.isEmpty()) {
            valutazioneRepository.deleteAll(valutazioneRepository.findBySottomissioneIn(sottomissioni));
            sottomissioneRepository.deleteAll(sottomissioni);
        }

        hackathonRepository.delete(hackathon);
        return true;
    }
}
