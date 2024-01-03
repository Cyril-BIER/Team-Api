package com.telecom.apiTeams.repository;

import com.telecom.apiTeams.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findByFirstNameIn(List<String> name);
    List<Player> findByLastNameIn(List<String> name);
    long deleteByIdIn(List<Integer> ids);
}
