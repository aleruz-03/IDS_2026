package it.unicam.cs.ids.hackhub.service.adapter;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GoogleCalendarAdapter implements ICalendarAdapter{
    private final GoogleCalendarService googleCalendarService;

    public GoogleCalendarAdapter(GoogleCalendarService googleCalendarService){
        this.googleCalendarService = googleCalendarService;
    }

    @Override
    public String pianificaEvento(String titolo, Date data, String emailMentore){
        return googleCalendarService.creaGoogleMeetEvent(titolo, data, emailMentore);
    }
}
