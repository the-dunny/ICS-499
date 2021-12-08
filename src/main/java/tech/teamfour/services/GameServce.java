package tech.teamfour.services;

import tech.teamfour.enums.GameStateEnum;
import tech.teamfour.model.LinePuzzle;


/**
 * The Interface GameServce.
 */
public interface GameServce {

    /**
     * Gets the new puzzle.
     *
     * @param gridSize the grid size
     * @return the new puzzle
     */
    public abstract LinePuzzle getNewPuzzle(int gridSize);

    /**
     * Gets the updated puzzle.
     *
     * @return the updated puzzle
     */
    public abstract LinePuzzle getUpdatedPuzzle();

    /**
     * Validate player move.
     *
     * @param keyPressed the key pressed
     * @return true, if successful
     */
    public abstract boolean validatePlayerMove(int keyPressed);

    /**
     * Check game status.
     *
     * @return the game state enum
     */
    public abstract GameStateEnum checkGameStatus();

    /**
     * Gets the game time.
     *
     * @return the game time
     */
    public abstract int getGameTime();

    /**
     * Checks if is game active.
     *
     * @return true, if is game active
     */
    public boolean isGameActive();
}
