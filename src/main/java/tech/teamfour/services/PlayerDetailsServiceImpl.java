package tech.teamfour.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.teamfour.model.Player;
import tech.teamfour.repositories.PlayerRepository;

import static java.util.Collections.emptyList;


/**
 * The Class PlayerDetailsServiceImpl.
 */
@Service
public class PlayerDetailsServiceImpl implements UserDetailsService {

    /** The player repository. */
    private PlayerRepository playerRepository;

    /**
     * Instantiates a new player details service impl.
     *
     * @param pr the pr
     */
    @Autowired
    public PlayerDetailsServiceImpl(PlayerRepository pr){
        this.playerRepository = pr;
    }

    /**
     * Load user by username.
     *
     * @param name the name
     * @return the user details
     * @throws UsernameNotFoundException the username not found exception
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Player player = playerRepository.findByUserName(name);
        if(player == null){
            throw new UsernameNotFoundException(name);
        }
        return new User(player.getUserName(), player.getPassword(), emptyList());
    }
}
