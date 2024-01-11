package com.telecom.apiTeams.dto;

import com.telecom.apiTeams.domain.Player;
import lombok.Data;

@Data
public class PlayerDTO {
    private int id;

    private String firstName;

    private String lastName;

    private int jerseyNumber;

    private String role;

    private String teamName;

    public Player toPlayer(){
        Player player = new Player();
        player.setFirstName(this.firstName);
        player.setLastName(this.lastName);
        player.setJerseyNumber(this.jerseyNumber);
        player.setRole(this.role);
        return player;
    }
}
