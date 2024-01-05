package com.telecom.apiTeams.repository;

import com.telecom.apiTeams.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    List<Team> findByNameIn(List<String> names);
    Team findByName(String name);
    List<Team> deleteByIdIn(List<Integer> ids);
}