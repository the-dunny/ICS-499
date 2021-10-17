package tech.dunny.services;

import org.springframework.http.ResponseEntity;
import tech.dunny.model.Player;

import java.util.List;


public interface PlayerService {
    public abstract void addPlayer(Player player);
    public void updatePlayerPassword(String newPassword, Player player);
    public void deleterPlayer(Player player);
    public List<Player> getPlayers();
    public Player getPlayer(Long id);
}
