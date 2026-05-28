package it.unicam.cs.ids.hackhub.controller.DTO;

import it.unicam.cs.ids.hackhub.model.Location;

import java.util.Date;

public record ModificaHackathonDTO(
        String name,
        String description,
        Date scadenzaIscrizioni,
        Date start,
        Date end,
        Location location,
        Double premioInDenaro
) {
}
