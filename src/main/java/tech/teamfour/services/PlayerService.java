package tech.teamfour.services;

import tech.teamfour.model.Player;
import tech.teamfour.model.Score;

import java.util.List;


/**
 * The Interface PlayerService.
 */
public interface PlayerService {
    
    /**
     * Adds the player.
     *
     * @param player the player
     */
    public abstract void addPlayer(Player player);
    
    /**
     * Update player password.
     *
     * @param newPassword the new password
     * @param player the player
     */
    public void updatePlayerPassword(String newPassword, Player player);
    
    /**
     * Deleter player.
     *
     * @param id the id
     */
    public void deleterPlayer(long id);
    
    /**
     * Change player role.
     *
     * @param id the id
     * @param newRole the new role
     */
    public void changePlayerRole(long id, String newRole);
    
    /**
     * Gets the players.
     *
     * @return the players
     */
    public List<Player> getPlayers();
    
    /**
     * Gets the player.
     *
     * @param id the id
     * @return the player
     */
    public Player getPlayer(Long id);
    
    /**
     * Gets the player scores.
     *
     * @return the player scores
     */
    public List<Score> getScores();
    
    /**
     * Sets the player score.
     *
     * @param score the score
     * @param id the id
     */
    public void setScore(int score, Long id);
    
    /**
     * Gets the player by name.
     *
     * @param name the name
     * @return the player by name
     */
    public Player getPlayerByName(String name);
    
    /**
     * Check existance by name.
     *
     * @param name the name
     * @return true, if successful
     */
    public boolean checkExistanceByName(String name);
    
}
