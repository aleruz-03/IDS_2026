package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.RichiestaSupporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RichiestaSupportoRepository extends JpaRepository<RichiestaSupporto, Long> {
}
