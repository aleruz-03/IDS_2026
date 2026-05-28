package it.unicam.cs.ids.hackhub.controller.DTO;

import java.util.Date;

public record GestioneSupportoDTO(
        String rispostaMentore,
        String linkCall,
        Date dataCall
) {
}
