package tech.teamfour.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import tech.teamfour.jwt.JwtRequestModel;
import tech.teamfour.jwt.JwtResponseModel;
import tech.teamfour.jwt.TokenManager;
import tech.teamfour.model.AuthenticationBean;
import tech.teamfour.model.Player;
import tech.teamfour.services.PlayerDetailsImpl;
import tech.teamfour.services.PlayerDetailsServiceImpl;
import tech.teamfour.services.PlayerService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SecurityController {

    @Autowired
    private PlayerDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private PlayerService playerService;

    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }

    @RequestMapping("/user/roles")
    public Map<String, Object> userRoles(Principal user){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Player p = playerService.getPlayerByName(user.getName());
        String role = p.getRoles();
        String[] roles = new String[1];
        roles[0] = role;
        map.put("username", user.getName());
        map.put("roles", roles);
        return map;
    }

    @RequestMapping("/token")
    public Map<String,String> token(HttpSession session) {
        return Collections.singletonMap("token", session.getId());
    }

    @GetMapping(path = "/basic_auth")
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("You are authenticated");
    }
    

    @PostMapping("/authenticate")
    public ResponseEntity createToken(@RequestBody JwtRequestModel request) throws Exception{
        authenticateHelper(request.getUsername(), request.getPassword());
        final UserDetails usrDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String token = tokenManager.generateToken(usrDetails);
        return ResponseEntity.ok(new JwtResponseModel(token));
    }

    private void authenticateHelper(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}