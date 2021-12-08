package tech.teamfour.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * The Class JwtRequestModel.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JwtRequestModel implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2636936156391265891L;
    
    /** The username. */
    private String username;
    
    /** The password. */
    private String password;

}
