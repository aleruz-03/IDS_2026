package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findById(long id);

    Team getTeamById(Long id);
}
