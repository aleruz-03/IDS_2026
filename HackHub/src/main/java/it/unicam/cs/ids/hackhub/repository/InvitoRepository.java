package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Invito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitoRepository extends JpaRepository<Invito, Long> {

    Invito getInvitoById(Long idInvito);
}
