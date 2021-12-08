package tech.teamfour.controllers;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.teamfour.enums.GameStateEnum;
import tech.teamfour.services.GameServce;
import tech.teamfour.services.GameServceImpl;


/**
 * The Class GameController.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders={"x-auth-token", "x-requested-with", "x-xsrf-token"})
public class GameController {
    
    /** The game servce. */
    final GameServce gameServce;

    /**
     * Instantiates a new game controller.
     *
     * @param gameServce the game servce
     */
    @Autowired
    public GameController(GameServceImpl gameServce) {
	this.gameServce = gameServce;
    }

    /**
     * Gets the new game.
     *
     * @param gridSize the grid size
     * @return the new game
     */
    @GetMapping("game/newGame")
    public ResponseEntity getNewGame(@RequestParam(defaultValue = "5", required = false) int gridSize) {
	return new ResponseEntity<>(gameServce.getNewPuzzle(gridSize), HttpStatus.OK);
    }

    /**
     * Gets the player move.
     *
     * @param keyPressed the key pressed
     * @return the player move
     */
    @GetMapping("game/currentGame")
    public ResponseEntity getPlayerMove(@RequestParam int keyPressed) {
	boolean wasMoveSuccessful = gameServce.validatePlayerMove(keyPressed);
	if(wasMoveSuccessful) {
	    if(gameServce.checkGameStatus() == GameStateEnum.FINISHED) {
		return new ResponseEntity<>(gameServce.getNewPuzzle(ThreadLocalRandom.current().nextInt(3, 10)), HttpStatus.OK);
	    }
	    return new ResponseEntity<>(gameServce.getUpdatedPuzzle(), HttpStatus.OK);
	} else {
	    return new ResponseEntity<>("Invalid Move", HttpStatus.OK);
	}
    }

    /**
     * Gets the current time.
     *
     * @return the current time
     */
    @GetMapping("game/currentTime")
    public ResponseEntity getCurrentTime() {
	if(this.gameServce.isGameActive())
	    return new ResponseEntity<>(this.gameServce.getGameTime(), HttpStatus.OK);
	return new ResponseEntity<>("No game running", HttpStatus.OK);
    }
}