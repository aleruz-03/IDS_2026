package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.controller.DTO.CreazioneMembroStaffDTO;
import it.unicam.cs.ids.hackhub.model.Giudice;
import it.unicam.cs.ids.hackhub.model.Mentore;
import it.unicam.cs.ids.hackhub.model.Organizzatore;
import it.unicam.cs.ids.hackhub.repository.MembroStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembroStaffService {

    private final MembroStaffRepository membroStaffRepository;

    @Autowired
    public MembroStaffService(MembroStaffRepository membroStaffRepository) {
        this.membroStaffRepository = membroStaffRepository;
    }


    public Organizzatore createOrganizzatore(CreazioneMembroStaffDTO organizzatoreDTO) {
        Organizzatore organizzatore = new Organizzatore(
                organizzatoreDTO.nome(),
                organizzatoreDTO.cognome(),
                organizzatoreDTO.email(),
                organizzatoreDTO.password()
        );

        return membroStaffRepository.save(organizzatore);
    }

    public Mentore createMentore(CreazioneMembroStaffDTO mentoreDTO){
        Mentore mentore = new Mentore(
                mentoreDTO.nome(),
                mentoreDTO.cognome(),
                mentoreDTO.email(),
                mentoreDTO.password()
        );

        return membroStaffRepository.save(mentore);
    }

    public Giudice createGiudice(CreazioneMembroStaffDTO giudiceDTO){
        Giudice giudice = new Giudice(
                giudiceDTO.nome(),
                giudiceDTO.cognome(),
                giudiceDTO.email(),
                giudiceDTO.password()
        );

        return membroStaffRepository.save(giudice);
    }


}
