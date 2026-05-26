package it.unicam.cs.ids.hackhub.controller.DTO;

import it.unicam.cs.ids.hackhub.model.Location;

public record LocationResponseDTO(
        String via,
        String cap,
        String civico,
        String citta,
        String provincia
) {
    public static LocationResponseDTO fromLocation(Location location) {
        if (location == null) {
            return null;
        }

        return new LocationResponseDTO(
                location.getVia(),
                location.getCAP(),
                location.getCivico(),
                location.getCitta(),
                location.getProvincia()
        );
    }
}
