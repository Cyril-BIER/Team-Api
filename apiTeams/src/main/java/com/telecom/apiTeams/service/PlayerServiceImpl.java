package com.telecom.apiTeams.service;

import com.telecom.apiTeams.domain.Player;
import com.telecom.apiTeams.domain.Team;
import com.telecom.apiTeams.dao.PlayerRepository;
import com.telecom.apiTeams.dao.TeamRepository;
import com.telecom.apiTeams.dto.PlayerDTO;
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
    public List<Player> savePlayers(List<PlayerDTO> playerDTOS) {
        List<Player> players = new ArrayList<>();
        for (PlayerDTO playerDTO : playerDTOS) {
            String teamName = playerDTO.getTeamName();
            Team team = teamRepository.findByName(teamName);

            if (team == null) {
                team = new Team();
                team.setName(teamName);
                team = teamRepository.save(team);
            }

            Player player = playerDTO.toPlayer();
            player.setTeam(team);
            players.add(player);
        }
        players = playerRepository.saveAll(players);
        return players;
    }

    @Override
    @Transactional
    public void deletePlayersById(List<Integer> ids) {
         playerRepository.deleteByIdIn(ids);
    }

    @Override
    @Transactional
    public List<Player> updatePlayers(List<PlayerDTO> updatedPlayers) {
        List<Player> existingPlayers = new ArrayList<>();
        for (PlayerDTO updatedPlayer : updatedPlayers) {

            Player existingPlayer = playerRepository.findById(updatedPlayer.getId()).orElse(null);

            if (existingPlayer != null) {
                existingPlayer.setFirstName(updatedPlayer.getFirstName());
                existingPlayer.setLastName(updatedPlayer.getLastName());
                existingPlayer.setJerseyNumber(updatedPlayer.getJerseyNumber());
                existingPlayer.setRole(updatedPlayer.getRole());

                String teamName = updatedPlayer.getTeamName();

                if (teamName != null) {
                    Team team = teamRepository.findByName(teamName);

                    if (team == null) {
                        team = new Team();
                        team.setName(teamName);
                        team = teamRepository.save(team);
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
