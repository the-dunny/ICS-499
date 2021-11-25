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

import java.util.ArrayList;
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
        if ("randomuser123".equals(name)) {
            return new User("randomuser123",
                    "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + name);
        }
    }
}
