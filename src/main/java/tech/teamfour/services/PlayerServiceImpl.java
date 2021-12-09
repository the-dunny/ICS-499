package tech.teamfour.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.teamfour.model.Player;
import tech.teamfour.model.Score;
import tech.teamfour.repositories.PlayerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;


/**
 * The Class PlayerServiceImpl.
 */
@Service
public class PlayerServiceImpl implements PlayerService{

    /** The player repo. */
    private PlayerRepository playerRepo;

    /**
     * Instantiates a new player service impl.
     *
     * @param pr the pr
     */
    @Autowired
    public PlayerServiceImpl(PlayerRepository pr){
	this.playerRepo = pr;
    }

    /**
     * Adds the player.
     *
     * @param player the player
     */
    @Override
    public void addPlayer(Player player) {
	if(player.playerID != null && player.getUserName() != null){
	    playerRepo.save(player);
	}
    }

    /**
     * Update player password.
     *
     * @param newPassword the new password
     * @param player the player
     */
    @Override
    public void updatePlayerPassword(String newPassword, Player player) {
	Player updatedPlayer = playerRepo.getById(player.getPlayerID());
	updatedPlayer.setPassword(newPassword);
	playerRepo.deleteById(player.playerID);
	addPlayer(updatedPlayer);
    }

    /**
     * Deleter player.
     *
     * @param id the id
     */
    @Override
    public void deleterPlayer(long id) {
	if(checkExistance(id)) playerRepo.deleteById(id);
    }

    /**
     * Gets the players.
     *
     * @return the players
     */
    @Override
    public List<Player> getPlayers() {
	return playerRepo.findAll();
    }

    /**
     * Gets the player.
     *
     * @param id the id
     * @return the player
     */
    @Override
    public Player getPlayer(Long id) {
	return playerRepo.getById(id);
    }

    /**
     * Gets the player scores.
     *
     * @return the player scores
     */
    @Override
    public List<Score> getScores() {
	List<Player> sortedPlayers = getPlayers();
	sortedPlayers.sort((p1, p2)
		-> ((Integer)p1.getBestScore()).compareTo((Integer)p2.getBestScore()));

	List<Score> sortedScores = new ArrayList<Score>();
	ListIterator<Player> iter = sortedPlayers.listIterator();

	while (iter.hasNext()) {	
	    Player nextPlayer = iter.next();
	    Score nextScore = new Score(nextPlayer.getUserName(),nextPlayer.getBestScore());
	    sortedScores.add(nextScore);
	}
	Collections.reverse(sortedScores);
	return sortedScores;
    }

    /**
     * Sets the high score.
     *
     * @param score the score
     * @param id the id
     */
    @Override
    public void setScore(int score, Long id) {
	if(checkExistance(id)) {
	    Player p = getPlayer(id);
	    p.setBestScore(p.getBestScore() + score);
	    playerRepo.save(p);
	}
    }

    /**
     * Gets the player by name.
     *
     * @param name the name
     * @return the player by name
     */
    @Override
    public Player getPlayerByName(String name) {
	return this.playerRepo.findByUserName(name);
    }

    /**
     * Check existance.
     *
     * @param id the id
     * @return true, if successful
     */
    private boolean checkExistance(long id){
	if(playerRepo.existsById(id)){
	    return true;
	}
	return false;
    }

    /**
     * Check existance by name.
     *
     * @param name the name
     * @return true, if successful
     */
    @Override
    public boolean checkExistanceByName(String name){
	if(this.playerRepo.findByUserName(name) != null){
	    return true;
	}
	return false;
    }

    /**
     * Change player role.
     *
     * @param id the id
     * @param newRole the new role
     */
    @Override
    public void changePlayerRole(long id, String newRole) {
	if(checkExistance(id)) {
	    Player p = getPlayer(id);
	    p.setRoles(newRole);
	    playerRepo.save(p);
	}

    }
}
