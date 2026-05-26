package it.unicam.cs.ids.hackhub.controller.DTO;

import it.unicam.cs.ids.hackhub.model.Location;

import java.util.Date;
import java.util.List;

public record creazioneHackathonDTO(
        String name,
        String description,
        Date scadenzaIscrizioni,
        Date start,
        Date end,
        Location location,
        double premioInDenaro,
        Long idGiudice,
        List<Long> idMentori
) {
}
