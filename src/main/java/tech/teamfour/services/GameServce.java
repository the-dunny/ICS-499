package tech.teamfour.services;

import tech.teamfour.GameContext;
import tech.teamfour.enums.GameStateEnum;
import tech.teamfour.model.LinePuzzle;

public interface GameServce {
    public abstract LinePuzzle getNewPuzzle(int gridSize);
    public abstract LinePuzzle getUpdatedPuzzle();
    public abstract boolean validatePlayerMove(int keyPressed);
    public abstract GameStateEnum checkGameStatus();
}
