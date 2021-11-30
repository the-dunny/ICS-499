package tech.teamfour.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.teamfour.services.PlayerService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token", "Authorization"})
public class ScoreController {

    PlayerService ps;

    @Autowired
    public ScoreController(PlayerService ps){
        this.ps = ps;
    }

    @GetMapping("score/highscores")
    public ResponseEntity getSortedScores(){ return new ResponseEntity(ps.getHighScores(), HttpStatus.OK);}
}
