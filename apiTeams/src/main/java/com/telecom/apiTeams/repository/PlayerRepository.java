package com.telecom.apiTeams.repository;

import com.telecom.apiTeams.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findByIdIn(List<Integer> ids);
    void deleteByIdIn(List<Integer> ids);
}
