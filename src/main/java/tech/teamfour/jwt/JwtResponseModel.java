package tech.teamfour.jwt;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;


/**
 * The Class JwtResponseModel.
 */
@AllArgsConstructor
public class JwtResponseModel implements Serializable {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The token. */
    @Getter
    private final String token;
}
