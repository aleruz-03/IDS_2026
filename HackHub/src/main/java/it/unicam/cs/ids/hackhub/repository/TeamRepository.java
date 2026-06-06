package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Team;
import it.unicam.cs.ids.hackhub.model.Hackathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findById(long id);

    Team getTeamById(Long id);

    List<Team> findByHackathonContaining(Hackathon hackathon);
}
