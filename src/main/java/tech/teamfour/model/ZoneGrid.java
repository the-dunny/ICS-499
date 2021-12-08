package tech.teamfour.model;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class ZoneGrid.
 */
@NoArgsConstructor
public class ZoneGrid extends Grid {

    /**
     * Instantiates a new zone grid.
     *
     * @param size the size
     */
    public ZoneGrid(int size) {
	size -= 2;
	this.vertexes = new ArrayList<List<Point>>(size);

	for (int x = 0; x <= size; x++)
	{
	    List<Point> row = new ArrayList<Point>(size);
	    for (int y = size; y >= 0; y--) {
		row.add(new Point(x, y));
	    }
	    this.vertexes.add(row);
	}
    }

    /**
     * Check up.
     *
     * @param point is the current point.
     * <br>Check to see if there is a valid move north of the grid.
     * @return true, if successful
     */
    public boolean checkUp(Point point) {
	boolean temp = false;
	if (point.getY() < getVertexes().size() - 1)
	    temp = true;
	else 
	    return temp;

	if (getUp(point).isVisited()) 
	    temp = false;
	else
	    temp = true;

	return temp;
    }


    /**
     * Check down.
     *
     * @param point the point
     * @return true, if successful
     */
    public boolean checkDown(Point point) {
	boolean temp = false;
	if (point.getY() > 0) 
	    temp = true; 
	else 
	    return temp;

	if (getDown(point).isVisited()) 
	    temp = false;
	else
	    temp = true;

	return temp;
    }

    /**
     * Check left.
     *
     * @param point the point
     * @return true, if successful
     */
    public boolean checkLeft(Point point) {
	boolean temp = false;
	if (point.getX() > 0) 
	    temp = true;
	else 
	    return temp;

	if (getLeft(point).isVisited()) 
	    temp = false;
	else
	    temp = true;

	return temp;
    }

    /**
     * Check right.
     *
     * @param point the point
     * @return true, if successful
     */
    public boolean checkRight(Point point) {
	boolean temp = false;
	if (point.getX() < getVertexes().size() - 1) 
	    temp = true;
	else 
	    return temp;

	if (getRight(point).isVisited()) 
	    temp = false;
	else
	    temp = true;

	return temp;
    }

    /**
     * Un visit.
     */
    public void unVisit() {
	for (List<Point> row : getVertexes()) {
	    for (Point point : row) {
		point.setVisited(false);
	    }
	}
    }

}
