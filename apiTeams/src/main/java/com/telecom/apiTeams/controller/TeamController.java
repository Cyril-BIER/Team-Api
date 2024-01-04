package com.telecom.apiTeams.controller;

import com.telecom.apiTeams.models.Team;
import com.telecom.apiTeams.service.TeamService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
            @RequestParam(name = "name", defaultValue ="") List<String> names
    ){
        try {
            return new ResponseEntity<>(teamService.getTeams(names),HttpStatus.OK);
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

    // TODO : Is Transactional really needed or at the good place?
    @Transactional
    @DeleteMapping("/team")
    public ResponseEntity<Object> deletePlayer(
            @RequestParam(name = "id") List<Integer> ids
    ){
        try {
            teamService.deleteTeams(ids);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
