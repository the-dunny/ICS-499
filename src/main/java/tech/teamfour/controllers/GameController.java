package tech.teamfour.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /** The game services. */
    Map<String, GameServce> gameServicesMap;

    /**
     * Instantiates a new game controller.
     *
     * @param gameServce the game servce
     */
    @Autowired
    public GameController() {
	this.gameServicesMap = new HashMap<String, GameServce>();
    }

    /**
     * Gets the new game.
     *
     * @param gridSize the grid size
     * @return the new game
     */
    @GetMapping("game/{id}/newGame")
    public ResponseEntity<?> getNewGame(@PathVariable("id") String id, @RequestParam(defaultValue = "5", required = false) int gridSize) {
	if (!this.gameServicesMap.containsKey(id)) this.gameServicesMap.put(id, new GameServceImpl());
	return new ResponseEntity<>(this.gameServicesMap.get(id).getNewPuzzle(gridSize), HttpStatus.OK);
    }

    /**
     * Gets the player move.
     *
     * @param keyPressed the key pressed
     * @return the player move
     */
    @GetMapping("game/{id}/currentGame")
    public ResponseEntity<?> getPlayerMove(@PathVariable("id") String id, @RequestParam int keyPressed) {
	boolean wasMoveSuccessful = this.gameServicesMap.get(id).validatePlayerMove(keyPressed);
	if(wasMoveSuccessful) {
	    if(this.gameServicesMap.get(id).checkGameStatus() == GameStateEnum.FINISHED) {
		return new ResponseEntity<>(this.gameServicesMap.get(id).getNewPuzzle(ThreadLocalRandom.current().nextInt(3, 10)), HttpStatus.OK);
	    }
	    return new ResponseEntity<>(this.gameServicesMap.get(id).getUpdatedPuzzle(), HttpStatus.OK);
	} else {
	    return new ResponseEntity<>("Invalid Move", HttpStatus.OK);
	}
    }
 
    /**
     * Gets the current time.
     *
     * @return the current time
     */
    @GetMapping("game/{id}/currentTime")
    public ResponseEntity<?> getCurrentTime(@PathVariable("id") String id) {
	if(this.gameServicesMap.get(id).isGameActive())
	    return new ResponseEntity<>(this.gameServicesMap.get(id).getGameTime(), HttpStatus.OK);
	return new ResponseEntity<>("No game running", HttpStatus.OK);
    }
}