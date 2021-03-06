package tech.teamfour.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * The Class Player.
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "password" })
@Proxy(lazy = false)
public class Player {
    
    /** The player ID. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long playerID;
    
    /** The user name. */
    private String userName;
    
    /** The password. */
    private String password;
    
    /** The best score. */
    private int bestScore;
    
    /** The is active. */
    private boolean isActive;
    
    /** The roles. */
    private String roles;
}