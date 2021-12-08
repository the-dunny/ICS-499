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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "password" })
@Proxy(lazy = false)
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long playerID;
    private String userName;
    private String password;
    private int bestScore;
    private boolean isActive;
    private String roles;
}