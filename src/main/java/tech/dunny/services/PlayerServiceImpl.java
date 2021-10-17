package tech.dunny.services;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.dunny.model.Player;
import tech.dunny.repositories.PlayerRepository;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepo;

    @Override
    public void addPlayer(Player player) {
        if(player.playerID != null && player.getUserName() != null
        && player.getPassword() != null){
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
    public void deleterPlayer(Player player) {
        if(playerRepo.existsById(player.playerID)) playerRepo.deleteById(player.playerID);
    }

    @Override
    public List<Player> getPlayers() {
        return playerRepo.findAll();
    }

    @Override
    public Player getPlayer(Long id) {
        return playerRepo.getById(id);
    }
}
