package com.telecom.apiTeams.service;

import com.telecom.apiTeams.models.Player;
import com.telecom.apiTeams.models.Team;
import com.telecom.apiTeams.repository.PlayerRepository;
import com.telecom.apiTeams.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public List<Player> getPlayers(List<Integer> ids) {
        if(!ids.isEmpty()){
            return playerRepository.findByIdIn(ids);
        }else{
            return playerRepository.findAll();
        }
    }



    @Override
    @Transactional
    public List<Player> savePlayers(List<Player> players) {
        for (Player player : players) {
            String teamName = player.getTeamName();
            Team team = player.getTeam();

            if (team != null && teamName != null) {
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
    @Transactional
    public void deletePlayersById(List<Integer> ids) {
         playerRepository.deleteByIdIn(ids);
    }

    @Override
    @Transactional
    public List<Player> updatePlayers(List<Player> updatedPlayers) {
        List<Player> existingPlayers = new ArrayList<>();
        for (Player updatedPlayer : updatedPlayers) {
            int playerId = updatedPlayer.getId();
            Player existingPlayer = playerRepository.findById(playerId).orElse(null);

            if (existingPlayer != null) {
                existingPlayer.setFirstName(updatedPlayer.getFirstName());
                existingPlayer.setLastName(updatedPlayer.getLastName());
                existingPlayer.setJerseyNumber(updatedPlayer.getJerseyNumber());
                existingPlayer.setRole(updatedPlayer.getRole());


                String teamName = updatedPlayer.getTeamName();


                if (teamName != null) {
                    Team team = teamRepository.findByName(teamName);

                    if (team == null) {
                        team = teamRepository.save(new Team(teamName));
                    }

                    existingPlayer.setTeam(team);

                }else{
                    existingPlayer.setTeam(null);
                }
                existingPlayers.add(existingPlayer);
            }
        }

        return playerRepository.saveAll(existingPlayers);
    }
}
