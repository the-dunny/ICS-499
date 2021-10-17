package tech.dunny.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tech.dunny.model.Player;
import tech.dunny.repositories.PlayerRepository;
import tech.dunny.services.PlayerService;
import tech.dunny.services.PlayerServiceImpl;


@RestController
public class PlayerController {


    final
    PlayerService playerService;

    @Autowired
    public PlayerController(PlayerServiceImpl playerService) {
        this.playerService = playerService;
    }


    @GetMapping("player/{id}")
    public ResponseEntity getPlayer(@PathVariable("id") Long id){
        return new ResponseEntity(playerService.getPlayer(id), HttpStatus.OK);
        //return new ResponseEntity<>(playerService.getPlayer(id), HttpStatus.OK);
    }

    @PostMapping(path = "player/add",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> createPlayer(@RequestParam String username, @RequestParam String password){
        Player player = new Player(0L, username, password, 0);
        playerService.addPlayer(player);
        return new ResponseEntity(player, HttpStatus.CREATED);
    }
}
