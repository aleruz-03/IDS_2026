package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.controller.DTO.AggiungiMentoreDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.ModificaHackathonDTO;
import it.unicam.cs.ids.hackhub.controller.DTO.creazioneHackathonDTO;
import it.unicam.cs.ids.hackhub.model.Giudice;
import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.MembroDelloStaff;
import it.unicam.cs.ids.hackhub.model.Mentore;
import it.unicam.cs.ids.hackhub.model.Organizzatore;
import it.unicam.cs.ids.hackhub.repository.HackathonRepository;
import it.unicam.cs.ids.hackhub.repository.MembroStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HackathonService {

    @Autowired
    private HackathonRepository hackathonRepository;
    private MembroStaffRepository membroStaffRepository;

    public HackathonService(HackathonRepository hackathonRepository, MembroStaffRepository membroStaffRepository) {
        this.hackathonRepository = hackathonRepository;
        this.membroStaffRepository = membroStaffRepository;
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
}
