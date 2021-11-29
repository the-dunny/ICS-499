package tech.teamfour.jwt;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
public class JwtResponseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private final String token;
}
