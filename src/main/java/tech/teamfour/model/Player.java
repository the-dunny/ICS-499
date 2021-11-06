package tech.teamfour.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long playerID;
    private String userName;
    private String password;
    private int bestScore;

 
}