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
	size -= 1;
	if (inner) size -= 1;
	this.vertexes = new ArrayList<List<Point>>(size);
	for (int x = 0; x <= size; x++)
	{
	    List<Point> row = new ArrayList<Point>(size);
	    for (int y = size; y >= 0; y--) {
		if (!inner) {
		    if (x == 0 && y == size) {
			this.end = new Point(x, y, 1);
			row.add(this.end);
		    }
		    else if (x == size && y == 0) {
			this.start = new Point(x, y, 0);
			row.add(this.start);
		    }
		    else row.add(new Point(x, y));   
		} else {
		    row.add(new Point());
		}
	    }
	    this.vertexes.add(row);
	}
	// WIP
	this.playerLines = new ArrayList<Line>();
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
    
    public Point getPoint(int x, int y) {
	System.out.println(x + "; " + y);
	return this.vertexes.get(x).get(vertexes.size() - 1 - y);
    }

}
