package com.telecom.apiTeams.service;

import com.telecom.apiTeams.domain.Team;
import com.telecom.apiTeams.dto.TeamDTO;

import java.util.List;

public interface TeamService {
    List<Team> getTeamsByName(List<String> names);

    List<Team> getTeams(List<Integer> ids);

    List<Team> saveTeams(List<TeamDTO> teams);

    List<Team> deleteTeams(List<Integer> ids);
}
