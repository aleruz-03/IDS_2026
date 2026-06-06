package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Invito;
import it.unicam.cs.ids.hackhub.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitoRepository extends JpaRepository<Invito, Long> {

    Invito getInvitoById(Long idInvito);

    List<Invito> getAllInvitoByMittente(Utente utente);
}
