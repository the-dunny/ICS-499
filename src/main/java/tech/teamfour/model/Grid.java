package tech.teamfour.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
/**
 * The Class Grid.
 */
@Data
@NoArgsConstructor
public class Grid {
    
    /** The vertexes. */
    protected List<List<Point>> vertexes;
    
    /** The start. */
    protected Point start;
    
    /** The end. */
    protected Point end;
    
    /** The location. */
    protected Point location;

    /**
     * Generate grid.
     *
     * @param size the size
     * @return the array list
     */
    public ArrayList<List<Point>> generateGrid(int size) {
	return null;
    }

    /**
     * Gets the up.
     *
     * @param point the point
     * @return the up
     */
    public Point getUp(Point point) {
	return getPoint(point.getX(), point.getY() + 1);
    }

    /**
     * Gets the down.
     *
     * @param point the point
     * @return the down
     */
    public Point getDown(Point point) {
	return getPoint(point.getX(), point.getY() - 1);
    }

    /**
     * Gets the right.
     *
     * @param point the point
     * @return the right
     */
    public Point getRight(Point point) {
	return getPoint(point.getX() + 1, point.getY());
    }

    /**
     * Gets the left.
     *
     * @param point the point
     * @return the left
     */
    public Point getLeft(Point point) {
	return getPoint(point.getX() - 1, point.getY());
    }

    /**
     * Merge paths.
     *
     * @param grid to merge paths with another grid.
     */
    public void mergePaths(Grid grid) {
	int size = vertexes.size() - 1;
	for (int x = 0; x <= size; x++) {
	    for (int y = size; y >= 0; y--) {
		if (getPoint(x, y).isDead() && !grid.getPoint(x, y).isDead()) {
		    getPoint(x, y).setDead(false);
		    getPoint(x, y).setZone(grid.getPoint(x, y).getZone());
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
		point.setZone(0);
	    }
	}
    }

    /**
     * Gets the point.
     *
     * @param point the point
     * @return the point
     */
    public Point getPoint(Point point) {
	return this.vertexes.get(point.getX()).get(vertexes.size() - 1 - point.getY());
    }

    /**
     * Gets the point.
     *
     * @param x the x
     * @param y the y
     * @return the point
     */
    public Point getPoint(int x, int y) {
	return this.vertexes.get(x).get(vertexes.size() - 1 - y);
    }

}