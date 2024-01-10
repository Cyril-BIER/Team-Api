package com.telecom.apiTeams.dao;

import com.telecom.apiTeams.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    List<Team> findByIdIn(List<Integer>ids);
    List<Team> findByNameIn(List<String> names);
    Team findByName(String name);
    List<Team> deleteByIdIn(List<Integer> ids);
}