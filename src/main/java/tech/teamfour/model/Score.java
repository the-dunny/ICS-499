package tech.teamfour.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * The Class Score.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Score {

    /** The user name. */
    private String userName;
    
    /** The best score. */
    private int bestScore;

}