package tech.teamfour.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.teamfour.model.Player;
import tech.teamfour.repositories.PlayerRepository;

import java.util.Optional;

@Service
public class PlayerDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PlayerService ps;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<Player> player = playerRepository.findByUserName(name);
        player.orElseThrow(() -> new UsernameNotFoundException(name + " not registered"));
        return player.map(PlayerDetailsImpl::new).get();
    }
}
