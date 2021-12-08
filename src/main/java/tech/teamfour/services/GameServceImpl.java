package tech.teamfour.services;

import org.springframework.stereotype.Service;
import tech.teamfour.GameContext;
import tech.teamfour.enums.GameStateEnum;
import tech.teamfour.model.LinePuzzle;

/**
 * The Class GameServceImpl.
 */
@Service
public class GameServceImpl implements GameServce {

    /** The game context. */
    private GameContext gameContext;

    /** The puzzle state. */
    private GameStateEnum puzzleState;

    /**
     * Gets the new puzzle.
     *
     * @param gridSize the grid size
     * @return the new puzzle
     */
    @Override
    public LinePuzzle getNewPuzzle(int gridSize) {
	gameContext = new GameContext(new LinePuzzle(gridSize));
	return gameContext.getGame();
    }

    /**
     * Gets the updated puzzle.
     *
     * @return the updated puzzle
     */
    @Override
    public LinePuzzle getUpdatedPuzzle() {
	return gameContext.getGame();
    }

    /**
     * Validate player move.
     *
     * @param keyPressed the key pressed
     * @return true, if successful
     */
    @Override
    public boolean validatePlayerMove(int keyPressed) {
	String stringMoveRequest;
	switch(keyPressed){
	case 38: stringMoveRequest = "UP";
	break;
	case 40: stringMoveRequest = "DOWN";
	break;
	case 37: stringMoveRequest = "LEFT";
	break;
	case 39: stringMoveRequest = "RIGHT";
	break;
	default: stringMoveRequest = "ERROR";
	break;
	}

	try {
	    boolean moveSuccessful = gameContext.Move(stringMoveRequest);
	    if (moveSuccessful) {
		if (gameContext.ChangeLocation()) puzzleState = GameStateEnum.FINISHED;
		else puzzleState = GameStateEnum.PLAYING;
		return true;
	    }
	} catch(Exception e) {
	    return false;
	}
	return false;
    }

    /**
     * Check game status.
     *
     * @return the game state enum
     */
    @Override
    public GameStateEnum checkGameStatus() {
	return puzzleState;
    }

    /**
     * Gets the game time.
     *
     * @return the game time
     */
    @Override
    public int getGameTime(){
	return this.gameContext.getTime();
    }

    /**
     * Checks if is game active.
     *
     * @return true, if is game active
     */
    @Override
    public boolean isGameActive(){
	if(this.gameContext == null){
	    return false;
	}
	return true;
    }
}
