package tech.teamfour.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenManager implements Serializable {

    private static final long serialVersionUID = 7008375124389347049L;
    public static final long TOKEN_EXP = 600*60;
    @Value("${sec2314ret}") private String jwtSecret;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((System.currentTimeMillis() + TOKEN_EXP * 1_000)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public String getPlayerNameFromToken(String token){
        final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        String player = getPlayerNameFromToken(token);
        Claims claims =  Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        Boolean checkExpired = claims.getExpiration().before(new Date());
        if(!checkExpired)
            return (player.equalsIgnoreCase(userDetails.getUsername()));
        return false;
    }

}
