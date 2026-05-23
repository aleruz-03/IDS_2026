package it.unicam.cs.ids.hackhub.service;

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


    public Organizzatore createOrganizzatore(Organizzatore organizzatore) {
        return membroStaffRepository.save(new Organizzatore(organizzatore.getNome(), organizzatore.getCognome(), organizzatore.getEmail(), organizzatore.getPassword()));
    }

    public Mentore createMentore(Mentore mentore){
        return membroStaffRepository.save(new Mentore(mentore.getNome(), mentore.getCognome(), mentore.getEmail(), mentore.getPassword()));
    }

    public Giudice createGiudice(Giudice giudice){
        return  membroStaffRepository.save(new Giudice(giudice.getNome(), giudice.getCognome(), giudice.getEmail(), giudice.getPassword()));
    }


}
