package application.model;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private List<List<Point>> vertexes;
    private List<Line> playerLines;
    private Point start;
    private Point end;

    public Grid() {
	this.vertexes = new ArrayList<List<Point>>();
	this.playerLines = new ArrayList<Line>();
	this.start = new Point();
	this.end = new Point();
    }

    public Grid(int size, boolean inner) {
	if (inner) size = size - 1;
	this.vertexes = new ArrayList<List<Point>>(size);
	for (int i = 0; i < size; i++)
	{
	    List<Point> row = new ArrayList<Point>(size);
	    for (int j = 0; j < size; j++) {
		if (!inner) {
		    if (i == 0 && j == 0) row.add(new Point(i, j, 1));
		    else if (i == size-1 && j == size-1) row.add(new Point(i, j, 0));
		    else row.add(new Point(i, j));   
		} else {
		    row.add(new Point());
		}
	    }
	    this.vertexes.add(row);
	}
	// WIP
	this.playerLines = new ArrayList<Line>();
	this.start = new Point();
	this.end = new Point();
    }

    public ArrayList<List<Point>> generateGrid(int size) {
	return null;
    }

    /**
     * @return the vertexes
     */
    public List<List<Point>> getVertexes() {
	return vertexes;
    }
    /**
     * @param vertexes the vertexes to set
     */
    public void setVertexes(List<List<Point>> vertexes) {
	this.vertexes = vertexes;
    }
    /**
     * @return the playerLines
     */
    public List<Line> getPlayerLines() {
	return playerLines;
    }
    /**
     * @param playerLines the playerLines to set
     */
    public void setPlayerLines(List<Line> playerLines) {
	this.playerLines = playerLines;
    }
    /**
     * @return the start
     */
    public Point getStart() {
	return start;
    }
    /**
     * @param start the start to set
     */
    public void setStart(Point start) {
	this.start = start;
    }
    /**
     * @return the end
     */
    public Point getEnd() {
	return end;
    }
    /**
     * @param end the end to set
     */
    public void setEnd(Point end) {
	this.end = end;
    }

}
