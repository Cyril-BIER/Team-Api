package com.telecom.apiTeams.service;

import com.telecom.apiTeams.models.Team;
import com.telecom.apiTeams.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TeamServiceImpl implements TeamService{
    @Autowired
    TeamRepository teamRepository;

    @Override
    public List<Team> getTeams(List<String> names) {
        List<Team> teams = new ArrayList<Team>();
        if(!names.isEmpty()){
            teams = teamRepository.findByNameIn(names);
        }else{
            teams = teamRepository.findAll();
        }
        return teams;
    }

    @Override
    public List<Team> saveTeams(List<Team> teams) {
        teamRepository.saveAll(teams);
        return teams;
    }
}
