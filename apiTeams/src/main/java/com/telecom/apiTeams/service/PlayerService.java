package com.telecom.apiTeams.service;

import com.telecom.apiTeams.models.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getPlayers(List<Integer> ids);
    List<Player> savePlayers(List<Player> players);
    void deletePlayersById(List<Integer> ids);
    List<Player> updatePlayers(List<Player> players);

}
