package com.telecom.apiTeams.service;

import com.telecom.apiTeams.domain.Player;
import com.telecom.apiTeams.domain.Team;
import com.telecom.apiTeams.dao.PlayerRepository;
import com.telecom.apiTeams.dao.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Team> getTeamsByName(List<String> names) {
        List<Team> teams;
        if(!names.isEmpty()){
            teams = teamRepository.findByNameIn(names);
        }else{
            teams = teamRepository.findAll();
        }
        return teams;
    }

    @Override
    public List<Team> getTeams(List<Integer> ids) {
        List<Team> teams;
        if(!ids.isEmpty()){
            teams = teamRepository.findByIdIn(ids);
        }else{
            teams = teamRepository.findAll();
        }
        return teams;
    }

    @Transactional
    @Override
    public List<Team> saveTeams(List<Team> teams) {
        for (Team team : teams) {
            Team savedTeam = teamRepository.save(new Team(team.getName()));

            List<Player> players = team.getPlayers();
            if (players != null) {
                for (Player player : players) {
                    player.setTeam(savedTeam);
                }
                playerRepository.saveAll(players);
            }
        }
        return teams;
    }

    @Transactional
    @Override
    public List<Team> deleteTeams(List<Integer> ids) {
        return teamRepository.deleteByIdIn(ids);
    }
}
