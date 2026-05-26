package it.unicam.cs.ids.hackhub.controller.DTO;

import it.unicam.cs.ids.hackhub.model.MembroDelloStaff;

public record MembroStaffResponseDTO(
        Long id,
        String nome,
        String cognome,
        String email
) {
    public static MembroStaffResponseDTO fromMembroStaff(MembroDelloStaff membroDelloStaff) {
        if (membroDelloStaff == null) {
            return null;
        }

        return new MembroStaffResponseDTO(
                membroDelloStaff.getId(),
                membroDelloStaff.getNome(),
                membroDelloStaff.getCognome(),
                membroDelloStaff.getEmail()
        );
    }


}
