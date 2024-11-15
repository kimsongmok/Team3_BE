package com.splanet.splanet.team.repository;

import com.splanet.splanet.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
public interface TeamRepository extends JpaRepository<Team, Long> {
}
=======
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByTeamName(String teamName);
}
>>>>>>> weekly/11
