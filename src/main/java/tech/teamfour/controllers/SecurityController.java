package tech.teamfour.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class SecurityController {

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

}
