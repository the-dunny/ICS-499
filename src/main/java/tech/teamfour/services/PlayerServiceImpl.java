package tech.teamfour.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.teamfour.model.Player;
import tech.teamfour.repositories.PlayerRepository;

import java.util.List;
import java.util.ListIterator;

@Service
public class PlayerServiceImpl implements PlayerService{

    private PlayerRepository playerRepo;

    @Autowired
    public PlayerServiceImpl(PlayerRepository pr){
        this.playerRepo = pr;
    }

    @Override
    public void addPlayer(Player player) {
        if(player.playerID != null && player.getUserName() != null){
        	   List<Player> sortedPlayers = getPlayers();
               sortedPlayers.sort((p1, p2)
                       -> ((Long)p1.getPlayerID()).compareTo((Long)p2.getPlayerID()));
               Long nextId = (long) (sortedPlayers.size() + 1); 
               player.setPlayerID(nextId); 
               
            playerRepo.save(player);
        }
    }

    @Override
    public void updatePlayerPassword(String newPassword, Player player) {
        Player updatedPlayer = playerRepo.getById(player.getPlayerID());
        updatedPlayer.setPassword(newPassword);
        playerRepo.deleteById(player.playerID);
        addPlayer(updatedPlayer);
    }

    @Override
    public void deleterPlayer(long id) {
        if(checkExistance(id)) playerRepo.deleteById(id);
    }

    @Override
    public List<Player> getPlayers() {
        return playerRepo.findAll();
    }

    @Override
    public Player getPlayer(Long id) {
        return playerRepo.getById(id);
    }

    @Override
    public List<Player> getHighScores(){
        List<Player> sortedPlayers = getPlayers();
        sortedPlayers.sort((p1, p2)
                -> ((Integer)p1.getBestScore()).compareTo((Integer)p2.getBestScore()));
     
        for (ListIterator<Player> iter = sortedPlayers.listIterator(); iter.hasNext(); ) {
 
            Player element = iter.next();
            Player updatedPlayer = playerRepo.getById(element.getPlayerID());
            updatedPlayer.setPlayerRank(iter.nextIndex()+1);
            playerRepo.deleteById(element.playerID);
            addPlayer(updatedPlayer);
          
        }
        
        sortedPlayers.sort((p1, p2)
                -> ((Integer)p1.getPlayerRank()).compareTo((Integer)p2.getPlayerRank()));
        
        return sortedPlayers;
    }

    private boolean checkExistance(long id){
        if(playerRepo.existsById(id)){
            return true;
        }
        return false;
    }
    
    
    
    
    

}
