package com.telecom.apiTeams.service;

import com.telecom.apiTeams.models.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getPlayersByIds(List<Integer> ids);

    List<Player> getPlayers();
    List<Player> savePlayers(List<Player> players);
    void deletePlayersById(List<Integer> ids);

}
