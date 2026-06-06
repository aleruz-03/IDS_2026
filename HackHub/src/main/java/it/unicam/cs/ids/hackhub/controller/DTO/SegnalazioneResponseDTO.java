package it.unicam.cs.ids.hackhub.controller.DTO;

import it.unicam.cs.ids.hackhub.model.Segnalazione;

public record SegnalazioneResponseDTO(
        Long id,
        String descrizione,
        TeamResponseDTO team,
        MembroStaffResponseDTO organizzatore,
        MembroStaffResponseDTO mentore
) {
    public static SegnalazioneResponseDTO fromSegnalazione(Segnalazione segnalazione) {
        return new SegnalazioneResponseDTO(
                segnalazione.getId(),
                segnalazione.getDescrizione(),
                segnalazione.getTeam() == null ? null : TeamResponseDTO.fromTeam(segnalazione.getTeam()),
                MembroStaffResponseDTO.fromMembroStaff(segnalazione.getOrganizzatore()),
                MembroStaffResponseDTO.fromMembroStaff(segnalazione.getMentore())
        );
    }
}
