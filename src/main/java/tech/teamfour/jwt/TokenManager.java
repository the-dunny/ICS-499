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
import java.util.function.Function;


/**
 * The Class TokenManager.
 */
@Component
public class TokenManager implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2550185165626007488L;

    /** The Constant EXP. */
    public static final long EXP = 5 * 60 * 60;

    /** The secret. */
    @Value("${secret}") private String secret;

    /**
     * Gets the player from token.
     *
     * @param token the token
     * @return the player from token
     */
    public String getPlayerFromToken(String token) {
	return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Gets the issued at date from token.
     *
     * @param token the token
     * @return the issued at date from token
     */
    public Date getIssuedAtDateFromToken(String token) {
	return getClaimFromToken(token, Claims::getIssuedAt);
    }

    /**
     * Gets the token expiration.
     *
     * @param token the token
     * @return the token expiration
     */
    public Date getTokenExpiration(String token) {
	return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Gets the claim from token.
     *
     * @param <T> the generic type
     * @param token the token
     * @param claimsResolver the claims resolver
     * @return the claim from token
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
	final Claims claims = getAllClaimsFromToken(token);
	return claimsResolver.apply(claims);
    }

    /**
     * Gets the all claims from token.
     *
     * @param token the token
     * @return the all claims from token
     */
    private Claims getAllClaimsFromToken(String token) {
	return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Checks if is token expired.
     *
     * @param token the token
     * @return the boolean
     */
    private Boolean isTokenExpired(String token) {
	final Date expiration = getTokenExpiration(token);
	return expiration.before(new Date());
    }

    /**
     * Generate token.
     *
     * @param userDetails the user details
     * @return the string
     */
    public String generateToken(UserDetails userDetails) {
	Map<String, Object> claims = new HashMap<>();
	return doGenerateToken(claims, userDetails.getUsername());
    }

    /**
     * Do generate token.
     *
     * @param claims the claims
     * @param subject the subject
     * @return the string
     */
    private String doGenerateToken(Map<String, Object> claims, String subject) {
	return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + EXP * 1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * Validate token.
     *
     * @param token the token
     * @param userDetails the user details
     * @return the boolean
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
	final String username = getPlayerFromToken(token);
	return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}