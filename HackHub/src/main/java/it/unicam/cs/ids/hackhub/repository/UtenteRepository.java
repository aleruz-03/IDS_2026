package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    Utente getUtenteById(Long id);
}
