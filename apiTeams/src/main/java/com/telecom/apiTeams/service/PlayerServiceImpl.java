package com.telecom.apiTeams.service;

import com.telecom.apiTeams.models.Player;
import com.telecom.apiTeams.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    PlayerRepository playerRepository;
    @Override
    public List<Player> getPlayersByFirstName(List<String> firstNames) {
        return playerRepository.findByFirstNameIn(firstNames);
    }

    @Override
    public List<Player> getPlayersByLastName(List<String> lastNames) {
        return playerRepository.findByLastNameIn(lastNames);
    }

    @Override
    public List<Player> savePlayers(List<Player> players) {
        playerRepository.saveAll(players);
        return players;
    }

    @Override
    public long deletePlayersById(List<Integer> ids) {
        return playerRepository.deleteByIdIn(ids);
    }


}
