package com.telecom.apiTeams.controller;

import com.telecom.apiTeams.models.Player;
import com.telecom.apiTeams.service.PlayerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @GetMapping("/player")
    public ResponseEntity<List<Player>> getPlayerFirstName(
            @RequestParam(name = "id", defaultValue ="") List<Integer> ids
    ){
        try{
            List<Player> players = new ArrayList<>();
            if(!ids.isEmpty()){
                 players = playerService.getPlayersByIds(ids);
            }else{
                players= playerService.getPlayers();
            }
            return new ResponseEntity<>(players, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/player")
    public ResponseEntity<List<Player>> postPlayer(
            @RequestBody List<Player> players
    ){
        try {
            return new ResponseEntity<>(playerService.savePlayers(players),HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO : Is Transactional really needed or at the good place?
    @Transactional
    @DeleteMapping("/player")
    public ResponseEntity<Object> deletePlayer(
            @RequestParam(name = "id") List<Integer> ids
    ){
        try {
            playerService.deletePlayersById(ids);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
