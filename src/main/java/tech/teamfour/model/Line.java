package tech.teamfour.model;

import java.util.Stack;

import lombok.Data;

@Data
public class Line {
    private Stack<Point> line;

    public Line() {
	this.setLine(new Stack<Point>());
    }

}