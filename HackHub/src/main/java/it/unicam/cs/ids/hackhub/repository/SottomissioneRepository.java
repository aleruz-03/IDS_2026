package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Sottomissione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SottomissioneRepository extends JpaRepository<Sottomissione, Long> {
    Sottomissione getSottomissioneById(long id);
}
