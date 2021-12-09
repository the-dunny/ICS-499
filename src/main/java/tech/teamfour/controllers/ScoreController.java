package tech.teamfour.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.teamfour.services.PlayerService;


/**
 * The Class ScoreController.
 */
@RestController
@CrossOrigin(origins = "*")
public class ScoreController {

    /** The ps. */
    PlayerService ps;

    /**
     * Instantiates a new score controller.
     *
     * @param ps the ps
     */
    @Autowired
    public ScoreController(PlayerService ps){
        this.ps = ps;
    }

    /**
     * Gets the sorted scores.
     *
     * @return the sorted scores
     */
    @GetMapping("score/highscores")
    public ResponseEntity<List<?>> getSortedScores(){ return new ResponseEntity<List<?>>(ps.getScores(), HttpStatus.OK);}
}
