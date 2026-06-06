package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Valutazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValutazioneRepository extends JpaRepository<Valutazione, Long> {
    List<Valutazione> findBySottomissioneIn(List<Sottomissione> sottomissioni);
}
