package com.telecom.apiTeams.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamDTO {
    private String name;
    private List<PlayerDTO> players;


}
