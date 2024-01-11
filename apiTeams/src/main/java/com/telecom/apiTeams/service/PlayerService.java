package com.telecom.apiTeams.service;

import com.telecom.apiTeams.domain.Player;
import com.telecom.apiTeams.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {
    List<Player> getPlayers(List<Integer> ids);
    List<Player> savePlayers(List<PlayerDTO> players);
    void deletePlayersById(List<Integer> ids);
    List<Player> updatePlayers(List<PlayerDTO> players);

}
