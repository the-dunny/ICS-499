package tech.teamfour.controllers;


import org.springframework.beans.factory.annotation.Autowired;
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
import tech.teamfour.services.PlayerDetailsImpl;
import tech.teamfour.services.PlayerDetailsServiceImpl;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SecurityController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }

    @RequestMapping("/user/roles")
    public Map<String, Object> userRoles(Principal user){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("username", user.getName());
        map.put("roles", AuthorityUtils.authorityListToSet(((Authentication)user).getAuthorities()));
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

    @PostMapping("/logon")
    public ResponseEntity createToken(@RequestBody JwtRequestModel requestModel) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestModel.getUsername(), requestModel.getPassword())
            );
        }catch (BadCredentialsException bce){throw new BadCredentialsException("Bad Creds", bce);}
        catch (DisabledException de){throw new DisabledException("User not active", de);}

        final UserDetails userDetails = userDetailsService.loadUserByUsername(requestModel.getUsername());
        final String jwtToken = tokenManager.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }

}
