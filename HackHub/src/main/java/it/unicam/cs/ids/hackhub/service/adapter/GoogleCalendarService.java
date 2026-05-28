package it.unicam.cs.ids.hackhub.service.adapter;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GoogleCalendarService {
    public String creaGoogleMeetEvent(String summary, Date start, String creatorEmail){
        System.out.println("=========================================================");
        System.out.println("[API GOOGLE CALENDAR EXTERNAL] Ricevuta richiesta di sincronizzazione!");
        System.out.println("-> Titolo Evento: " + summary);
        System.out.println("-> Data e Ora Incontro: " + start);
        System.out.println("-> Organizzatore (Mentore): " + creatorEmail);
        System.out.println("=========================================================");

        return "https://meet.google.com/abc-defg-" + (int)(Math.random() * 900 + 100);
    }
}
