package com.telecom.apiTeams.controller;

import com.telecom.apiTeams.models.Team;
import com.telecom.apiTeams.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeamController {
    @Autowired
    TeamService teamService;

    @GetMapping("/team")
    public ResponseEntity<List<Team>> getTeams(
            @RequestParam(name = "id", defaultValue ="") List<Integer> ids,
            @RequestParam(name = "name", defaultValue ="") List<String> names
    ){
        try {
            List<Team> teams;
            if(!ids.isEmpty()){
                teams = teamService.getTeams(ids);
            }else{
                teams = teamService.getTeamsByName(names);
            }
            return new ResponseEntity<>(teams,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/team")
    public ResponseEntity<List<Team>> postTeams(
            @RequestBody List<Team> teams
    ){
        try {
            return new ResponseEntity<>(teamService.saveTeams(teams),HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/team")
    public ResponseEntity<Object> deletePlayer(
            @RequestParam(name = "id") List<Integer> ids
    ){
        try {
            List<Team> team = teamService.deleteTeams(ids);
            return new ResponseEntity<>(team, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
