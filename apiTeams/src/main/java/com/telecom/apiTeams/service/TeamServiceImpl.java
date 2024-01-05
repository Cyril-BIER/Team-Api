package com.telecom.apiTeams.service;

import com.telecom.apiTeams.models.Player;
import com.telecom.apiTeams.models.Team;
import com.telecom.apiTeams.repository.PlayerRepository;
import com.telecom.apiTeams.repository.TeamRepository;
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
    public List<Team> getTeams(List<String> names) {
        List<Team> teams;
        if(!names.isEmpty()){
            teams = teamRepository.findByNameIn(names);
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
            teamRepository.flush();

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
