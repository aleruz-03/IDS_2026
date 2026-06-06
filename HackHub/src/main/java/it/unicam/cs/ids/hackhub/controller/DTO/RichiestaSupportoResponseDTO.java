package it.unicam.cs.ids.hackhub.controller.DTO;

import it.unicam.cs.ids.hackhub.model.RichiestaSupporto;

import java.util.Date;

public record RichiestaSupportoResponseDTO(
        Long id,
        String descrizione,
        String rispostaMentore,
        boolean evasa,
        String linkCall,
        Date dataCall,
        TeamResponseDTO team,
        MembroStaffResponseDTO mentore
) {
    public static RichiestaSupportoResponseDTO fromRichiestaSupporto(RichiestaSupporto richiestaSupporto) {
        return new RichiestaSupportoResponseDTO(
                richiestaSupporto.getId(),
                richiestaSupporto.getDescrizione(),
                richiestaSupporto.getRispostaMentore(),
                richiestaSupporto.isEvasa(),
                richiestaSupporto.getLinkCall(),
                richiestaSupporto.getDataCall(),
                richiestaSupporto.getTeam() == null ? null : TeamResponseDTO.fromTeam(richiestaSupporto.getTeam()),
                MembroStaffResponseDTO.fromMembroStaff(richiestaSupporto.getMentore())
        );
    }
}
