package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.RichiestaSupporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RichiestaSupportoRepository extends JpaRepository<RichiestaSupporto, Long> {
    List<RichiestaSupporto> findByMentoreId(Long idMentore);

    List<RichiestaSupporto> findByMentoreIdAndEvasaFalse(Long idMentore);
}
