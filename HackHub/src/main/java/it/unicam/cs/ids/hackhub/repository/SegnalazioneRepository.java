package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Segnalazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegnalazioneRepository extends JpaRepository<Segnalazione, Long> {
    List<Segnalazione> findByOrganizzatoreId(Long organizzatoreId);

    List<Segnalazione> findByMentoreId(Long mentoreId);
}
