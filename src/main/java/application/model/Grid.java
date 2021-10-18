package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {
    private List<List<Point>> vertexes;
    private Point start;
    private Point end;

    public Grid() {
	this.vertexes = new ArrayList<List<Point>>();
	this.start = new Point();
	this.end = new Point();
    }

    public Grid(int size, boolean inner) {
	Random rand = new Random();
	size -= 1;
	if (inner) size -= 1;
	int start_offset = rand.nextInt(size);
	int end_offset = rand.nextInt(size);
	this.vertexes = new ArrayList<List<Point>>(size);
	for (int x = 0; x <= size; x++)
	{
	    List<Point> row = new ArrayList<Point>(size);
	    for (int y = size; y >= 0; y--) {
		if (!inner) {
		    if (x == 0 && y == size - end_offset) {
			this.end = new Point(x, y, 1);
			row.add(this.end);
		    }
		    else if (x == size && y == start_offset) {
			System.out.println(x + ", " + y);
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

    public Point getNorth(Point point) {
	return getPoint(point.getX(), point.getY() + 1);
    }

    public Point getSouth(Point point) {
	return getPoint(point.getX(), point.getY() - 1);
    }

    public Point getEast(Point point) {
	return getPoint(point.getX() + 1, point.getY());
    }

    public Point getWest(Point point) {
	return getPoint(point.getX() - 1, point.getY());
    }

    /**
     * @param grid to merge paths with another grid.
     */
    public void mergePaths(Grid grid) {
	int size = vertexes.size() - 1;
	for (int x = 0; x <= size; x++) {
	    for (int y = size; y >= 0; y--) {
		if (getPoint(x, y).isDead() && !grid.getPoint(x, y).isDead()) {
		    getPoint(x, y).setDead(false);
		}
	    }
	}
    }

    /**
     * Clears a maze setting them all to dead points, no required points, and no zones.
     */
    public void clear() {
	for (List<Point> row : getVertexes()) {
	    for (Point point : row) {
		point.setDead(true);
		point.setRequired(false);
		// TODO Clear Zones
	    }
	}
    }

    public Point getPoint(Point point) {
	return this.vertexes.get(point.getX()).get(vertexes.size() - 1 - point.getY());
    }
    
    public Point getPoint(int x, int y) {
	return this.vertexes.get(x).get(vertexes.size() - 1 - y);
    }

}
