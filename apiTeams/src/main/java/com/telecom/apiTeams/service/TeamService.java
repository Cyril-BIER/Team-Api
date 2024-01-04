package com.telecom.apiTeams.service;

import com.telecom.apiTeams.models.Team;

import java.util.List;

public interface TeamService {
    List<Team> getTeams(List<String> names);

    List<Team> saveTeams(List<Team> teams);
}
