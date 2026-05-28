package it.unicam.cs.ids.hackhub.service;

import it.unicam.cs.ids.hackhub.model.RichiestaSupporto;
import it.unicam.cs.ids.hackhub.service.adapter.ICalendarAdapter;
import org.springframework.stereotype.Service;

@Service
public class GestoreCallService {
    private final ICalendarAdapter calendarAdapter;

    public GestoreCallService(ICalendarAdapter calendarAdapter) {
        this.calendarAdapter = calendarAdapter;
    }

    public void inviaLinkCall(RichiestaSupporto richiesta) {
        String titoloEvent = "Supporto Tecnico HackHub - Richiesta #" + richiesta.getId();

        String linkGeneratoAutomaticamente = calendarAdapter.pianificaEvento(
                titoloEvent,
                richiesta.getDataCall(),
                richiesta.getMentore().getEmail()
        );

        richiesta.setLinkCall((linkGeneratoAutomaticamente));

        System.out.println("[SYSTEM - GESTORE CALL] Link ottenuto dall'Adapter: " + linkGeneratoAutomaticamente);
        System.out.println("[SYSTEM] Invio notifica del link completato al Team!");
    }
}
