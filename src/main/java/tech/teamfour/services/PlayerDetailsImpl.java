package tech.teamfour.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.teamfour.model.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The Class PlayerDetailsImpl.
 */
public class PlayerDetailsImpl implements UserDetails {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The username. */
    private String username;
    
    /** The password. */
    private String password;
    
    /** The is active. */
    private boolean isActive;
    
    /** The authorities. */
    private List<GrantedAuthority> authorities;

    /**
     * Instantiates a new player details impl.
     *
     * @param player the player
     */
    public PlayerDetailsImpl(Player player){
        this.username = player.getUserName();
        this.password = player.getPassword();
        this.isActive = player.isActive();
        this.authorities = Arrays.stream(player.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * Instantiates a new player details impl.
     */
    public PlayerDetailsImpl(){}

    /**
     * Gets the authorities.
     *
     * @return the authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Checks if is account non expired.
     *
     * @return true, if is account non expired
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks if is account non locked.
     *
     * @return true, if is account non locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Checks if is credentials non expired.
     *
     * @return true, if is credentials non expired
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks if is enabled.
     *
     * @return true, if is enabled
     */
    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
