package it.unicam.cs.ids.hackhub.controller.DTO;

public record InvitoDTO(
        Long idTeam,
        Long idDestinatario
) {
    @Override
    public Long idTeam() {
        return idTeam;
    }

    @Override
    public Long idDestinatario() {
        return idDestinatario;
    }
}
