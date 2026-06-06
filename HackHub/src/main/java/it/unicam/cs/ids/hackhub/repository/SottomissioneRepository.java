package it.unicam.cs.ids.hackhub.repository;

import it.unicam.cs.ids.hackhub.model.Hackathon;
import it.unicam.cs.ids.hackhub.model.Sottomissione;
import it.unicam.cs.ids.hackhub.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SottomissioneRepository extends JpaRepository<Sottomissione, Long> {
    Sottomissione getSottomissioneById(long id);

    Sottomissione getSottomissioneByTeam(Team team);

    List<Sottomissione> findByHackathon(Hackathon hackathon);
}
