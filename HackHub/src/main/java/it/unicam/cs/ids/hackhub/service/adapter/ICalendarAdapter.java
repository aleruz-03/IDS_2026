package it.unicam.cs.ids.hackhub.service.adapter;

import java.util.Date;

public interface ICalendarAdapter {
    /**
     * Pianifica un evento su un calendario esterno e restituisce il link della call generato.
     *
     * @param titolo   il titolo dell'evento/call
     * @param data     la data e ora dell'incontro
     * @param emailMentore l'email del mentore che organizza
     * @return la stringa contenente l'URL telematico (es. Google Meet o Teams)
     */
    String pianificaEvento(String titolo, Date data, String emailMentore);
}
