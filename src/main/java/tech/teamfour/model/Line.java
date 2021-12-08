package tech.teamfour.model;

import lombok.Data;

import java.util.Stack;


/**
 * The Class Line.
 */
@Data
public class Line {

    /** The line. */
    private Stack<Point> line;

    /**
     * Instantiates a new line.
     */
    public Line() {
	this.setLine(new Stack<Point>());
    }
}