package tech.dunny.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tech.dunny.repositories.PlayerRepository;
import tech.dunny.services.PlayerService;


@RestController
public class PlayerController {


    final
    PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @GetMapping("player/{id}")
    public String getPlayer(@PathVariable("id") Long id){
        return playerService.getPlayer(id).getUserName();
        //return new ResponseEntity<>(playerService.getPlayer(id), HttpStatus.OK);
    }

    @GetMapping("test")
    public String testing(){
        return "testing testing 123";
    }
}
