package com.telecom.apiTeams.service;

import com.telecom.apiTeams.models.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getPlayersByFirstName(List<String> firstNames);
    List<Player> getPlayersByLastName(List<String> lastNames);
    List<Player> savePlayers(List<Player> players);
    long deletePlayersById(List<Integer> ids);

}
