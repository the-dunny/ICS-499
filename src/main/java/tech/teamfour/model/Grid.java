package tech.teamfour.model;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Grid {
    protected List<List<Point>> vertexes;
    protected Point start;
    protected Point end;

    public ArrayList<List<Point>> generateGrid(int size) {
	return null;
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