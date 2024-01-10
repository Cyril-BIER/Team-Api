package com.telecom.apiTeams.service;

import com.telecom.apiTeams.domain.Player;
import com.telecom.apiTeams.domain.Team;
import com.telecom.apiTeams.dao.PlayerRepository;
import com.telecom.apiTeams.dao.TeamRepository;
import com.telecom.apiTeams.dto.PlayerDTO;
import com.telecom.apiTeams.dto.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<Team> saveTeams(List<TeamDTO> teams) {
        List<Team> returnTeams= new ArrayList<>();
        for (TeamDTO teamDTO : teams) {
            Team team = new Team();
            team.setName(teamDTO.getName());

            List<PlayerDTO> players = teamDTO.getPlayers();
            List<Player> teamPlayers = new ArrayList<>();
            if (players != null) {
                for (PlayerDTO playerDto : players) {
                    Player player = playerDto.toPlayer();
                    teamPlayers.add(player);
                    player.setTeam(team);
                }
            }
            team.setPlayers(teamPlayers);
            returnTeams.add(team);
        }
        teamRepository.saveAll(returnTeams);
        return returnTeams;
    }

    @Transactional
    @Override
    public List<Team> deleteTeams(List<Integer> ids) {
        return teamRepository.deleteByIdIn(ids);
    }
}
