package com.telecom.apiTeams.service;

import com.telecom.apiTeams.models.Player;
import com.telecom.apiTeams.models.Team;
import com.telecom.apiTeams.repository.PlayerRepository;
import com.telecom.apiTeams.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public List<Player> getPlayersByIds(List<Integer> ids) {
        return playerRepository.findByIdIn(ids);
    }

    @Override
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public List<Player> savePlayers(List<Player> players) {
        for (Player player : players) {
            String teamName = player.getTeamName();
            Team team = player.getTeam();

            if (team == null && teamName != null) {
                team = teamRepository.findByName(teamName);

                if (team == null) {
                    team = teamRepository.save(new Team(teamName));
                }

                player.setTeam(team);
            }
        }
        playerRepository.saveAll(players);
        return players;
    }

    @Override
    public void deletePlayersById(List<Integer> ids) {
         playerRepository.deleteByIdIn(ids);
    }


}
