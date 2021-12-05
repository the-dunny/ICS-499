package tech.teamfour.services;

import org.springframework.stereotype.Service;
import tech.teamfour.GameContext;
import tech.teamfour.enums.GameStateEnum;
import tech.teamfour.model.LinePuzzle;
import tech.teamfour.model.Point;

@Service
public class GameServceImpl implements GameServce {


    private GameContext gameContext;
    private GameStateEnum puzzleState;

    @Override
    public LinePuzzle getNewPuzzle(int gridSize) {
	gameContext = new GameContext(new LinePuzzle(gridSize));
	return gameContext.getGame();
    }

    @Override
    public LinePuzzle getUpdatedPuzzle() {
	return gameContext.getGame();
    }

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

    @Override
    public GameStateEnum checkGameStatus() {
	return puzzleState;
    }

    @Override
    public int getGameTime(){
	return this.gameContext.getTime();
    }

    @Override
    public boolean isGameActive(){
	if(this.gameContext == null){
	    return false;
	}
	return true;
    }
}
