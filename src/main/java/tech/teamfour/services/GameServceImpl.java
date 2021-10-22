package tech.teamfour.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.teamfour.GameContext;
import tech.teamfour.enums.GameStateEnum;
import tech.teamfour.model.Line;
import tech.teamfour.model.LinePuzzle;

@Service
public class GameServceImpl implements GameServce{


    private GameContext gameContext;

    @Override
    public LinePuzzle getNewPuzzle(int gridSize) {
        gameContext = new GameContext(new LinePuzzle(gridSize), new Line());
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
            case 38: stringMoveRequest = "Up";
                    break;
            case 40: stringMoveRequest = "Down";
                    break;
            case 37: stringMoveRequest = "Left";
                    break;
            case 39: stringMoveRequest = "Right";
                    break;
            default: stringMoveRequest = "ERROR";
                    break;

        }

        try {
            boolean moveSuccessful = gameContext.move(stringMoveRequest);
            if(moveSuccessful) return true;
        }catch(Exception e){
            return false;
        }
        return false;
    }

    @Override
    public GameStateEnum checkGameStatus() {
        return null;
    }
}
