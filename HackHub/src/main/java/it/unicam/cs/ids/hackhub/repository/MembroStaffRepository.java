package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Giudice;
import it.unicam.cs.ids.hackhub.model.MembroDelloStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembroStaffRepository extends JpaRepository<MembroDelloStaff, Long> {

    Giudice getGiudiceById(Long id);
}
