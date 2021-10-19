package tech.teamfour.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LinePuzzle {
    private Grid mainGrid;
    private Grid innerGrid;

    public LinePuzzle() {
	this.mainGrid = new Grid();
	this.innerGrid = new Grid();
    }

    public LinePuzzle(int size) {
	if (size < 3) size = 3;
	this.mainGrid = new Grid(size, false);
	this.innerGrid = new Grid(size, true);
    }

    public void travel(int x, int y) {
	System.out.println(this.mainGrid);
    }

    /**
     * @return the mainGrid
     */
    public Grid getMainGrid() {
	return mainGrid;
    }
    /**
     * @param mainGrid the mainGrid to set
     */
    public void setMainGrid(Grid mainGrid) {
	this.mainGrid = mainGrid;
    }
    /**
     * @return the innerGrid
     */
    public Grid getInnerGrid() {
	return innerGrid;
    }
    /**
     * @param innerGrid the innerGrid to set
     */
    public void setInnerGrid(Grid innerGrid) {
	this.innerGrid = innerGrid;
    }  

    /**
     * Generate a LinePuzzle with obstacles and additional features.
     */
    public void generate() {
	primsMaze();
	pointsAndZones();
    }

    /**
     * Generate a random "loose" maze using Prim's Algorithm.
     */
    public void primsMaze() {
	ArrayList<Grid> gridLayer = new ArrayList<Grid>();
	int size = mainGrid.getVertexes().size();
	int layerCount = 2;

	for (int i = 0; i < layerCount; i++) gridLayer.add(new Grid(mainGrid.getVertexes().size(), false));

	for (Grid grid : gridLayer) {
	    LinkedList<int[]> nav = new LinkedList<>();
	    Random rand = new Random();

	    int x = rand.nextInt(size);
	    int y = rand.nextInt(size);

	    nav.add(new int[] {x, y, x, y});

	    while (!nav.isEmpty()) {
		final int[] f = nav.remove(rand.nextInt(nav.size()));
		x = f[2];
		y = f[3];
		if (grid.getPoint(x, y).isDead())
		{
		    grid.getPoint(f[0], f[1]).setDead(false);
		    grid.getPoint(x, y).setDead(false);
		    if (x >= 2 && grid.getPoint(x - 2, y).isDead()) nav.add(new int[] {x - 1, y, x - 2, y});
		    if (y >= 2 && grid.getPoint(x, y - 2).isDead()) nav.add(new int[] {x, y - 1, x, y - 2});
		    if (x < size - 2 && grid.getPoint(x + 2, y).isDead()) nav.add(new int[] {x + 1, y, x + 2, y});
		    if (y < size - 2 && grid.getPoint(x, y + 2).isDead()) nav.add(new int[] {x, y + 1, x, y + 2});
		}
	    }

	    mainGrid.mergePaths(grid);
	}

	// If the start and end points of the final maze do not have paths, retry.
	if (!validatePuzzle()) {
	    mainGrid.clear();
	    primsMaze();
	} else {
	    mainGrid.getStart().setDead(false);
	    mainGrid.getEnd().setDead(false);
	}
    }

    /**
     * Generate collectible points and capture zones.
     */
    public void pointsAndZones() {
	Line path = randomValidPath();
	int probability = 12 - (12 - mainGrid.getVertexes().size());
	for (Point point : path.getLine()) {
	    Random rand = new Random();
	    if (rand.nextInt(probability) == 0) {
		mainGrid.getPoint(point.getX(), point.getY()).setRequired(true);
	    }
	}
	System.out.println(path.getLine());
	// TODO ZONES
    }

    /**
     * Return a random valid path on the main grid, returns empty if there's no available path.
     */ 
    public Line randomValidPath() {
	Grid tmpGrid = new Grid(mainGrid.getVertexes().size(), false);
	Line randomPath = new Line();
	Point location = new Point(mainGrid.getStart().getX(), mainGrid.getStart().getY());
	int size = tmpGrid.getVertexes().size();
	int maxattempts = (size * size) * 4;
	List<String> direction = new ArrayList<String>(Arrays.asList("N", "S", "E", "W"));
	tmpGrid.mergePaths(mainGrid);

	for (int i = 0; i < maxattempts; i++) {
	    Random rand = new Random();
	    switch (direction.get(rand.nextInt(direction.size()))) {
	    case "N": {
		if (location.getY() >= size - 1) {
		    direction.remove("N");
		    break;
		}

		if (tmpGrid.getNorth(location).isDead()) {
		    direction.remove("N");
		    break;
		}

		if (tmpGrid.getNorth(location).isTravel()) {
		    if (tmpGrid.getNorth(location) != tmpGrid.getPoint(randomPath.getLine().peek().getX(), randomPath.getLine().peek().getY())) {
			direction.remove("N");
			break;
		    }
		}

		randomPath.getLine().push(new Point(location.getX(), location.getY()));
		tmpGrid.getPoint(location.getX(), location.getY()).setVisited(true);
		location.setY(location.getY() + 1);
		direction = new ArrayList<String>(Arrays.asList("N", "E", "W"));
		break;
	    }
	    case "S": {
		if (location.getY() == 0) {
		    direction.remove("S");
		    break;
		}

		if (tmpGrid.getSouth(location).isDead()) {
		    direction.remove("S");
		    break;
		}

		if (tmpGrid.getSouth(location).isTravel()) {
		    if (tmpGrid.getSouth(location) != tmpGrid.getPoint(randomPath.getLine().peek().getX(), randomPath.getLine().peek().getY())) {
			direction.remove("S");
			break;
		    }   
		}

		randomPath.getLine().push(new Point(location.getX(), location.getY()));
		tmpGrid.getPoint(location.getX(), location.getY()).setVisited(true);
		location.setY(location.getY() - 1);
		direction = new ArrayList<String>(Arrays.asList("S", "E", "W"));
		break;
	    }
	    case "E":  {
		if (location.getX() == size - 1) {
		    direction.remove("E");
		    break;
		}

		if (tmpGrid.getEast(location).isDead()) {
		    direction.remove("E");
		    break;
		}

		if (tmpGrid.getEast(location).isTravel()) {
		    if (tmpGrid.getEast(location) != tmpGrid.getPoint(randomPath.getLine().peek().getX(), randomPath.getLine().peek().getY())) {
			direction.remove("E");
			break;
		    }   
		}

		randomPath.getLine().push(new Point(location.getX(), location.getY()));
		tmpGrid.getPoint(location.getX(), location.getY()).setVisited(true);
		location.setX(location.getX() + 1);
		direction = new ArrayList<String>(Arrays.asList("N", "S", "E"));
		break;
	    }
	    case "W": {
		if (location.getX() == 0) {
		    direction.remove("W");
		    break;
		}

		if (tmpGrid.getWest(location).isDead()) {
		    direction.remove("W");
		    break;
		}

		if (tmpGrid.getWest(location).isTravel()) {
		    if (tmpGrid.getWest(location) != tmpGrid.getPoint(randomPath.getLine().peek().getX(), randomPath.getLine().peek().getY())) {
			direction.remove("W");
			break;
		    }   
		}

		randomPath.getLine().push(new Point(location.getX(), location.getY()));
		tmpGrid.getPoint(location.getX(), location.getY()).setVisited(true);
		location.setX(location.getX() - 1);
		direction = new ArrayList<String>(Arrays.asList("N", "S", "W"));
		break;
	    }
	    }

	    if (tmpGrid.getEnd().getX() == location.getX() && tmpGrid.getEnd().getY() == location.getY()) {
		randomPath.getLine().push(new Point(location.getX(), location.getY()));
		return randomPath;
	    }

	    if (direction.size() == 0) {
		tmpGrid.getPoint(location.getX(), location.getY()).setVisited(false);
		tmpGrid.getPoint(location.getX(), location.getY()).setDead(true);
		try {
		    randomPath.getLine().pop();  
		    location = new Point(randomPath.getLine().peek().getX(), randomPath.getLine().peek().getY());
		} catch (EmptyStackException ese) {
		    return randomValidPath();
		}
		direction = new ArrayList<String>(Arrays.asList("N", "S", "E", "W"));
	    }
	}
	return randomValidPath();
    }

    /**
     * Returns true if the puzzle is valid.
     */
    public boolean validatePuzzle() {
	if (mainGrid.getPoint(mainGrid.getVertexes().size() - 2, 0).isDead() && mainGrid.getPoint(mainGrid.getVertexes().size() - 1, 1).isDead() 
		|| mainGrid.getPoint(0, mainGrid.getVertexes().size() - 1).isDead() && mainGrid.getPoint(1, mainGrid.getVertexes().size() - 1).isDead()) {
	    return false;
	}
	return true;
    }

    /**
     * Returns true if the puzzle is valid.
     */
    public boolean isComplete() {
	for (List<Point> row : mainGrid.getVertexes()) {
	    for (Point point : row) {
		if (point.isRequired() && !point.isTravel()) {
		    return false;
		}
	    }
	}
	return true;
    }

    /**
     * Restarts the player back to the beginning.
     */
    public Point retry() {
	for (List<Point> row : mainGrid.getVertexes()) {
	    for (Point point : row) {
		if (point.isTravel()) {
		    point.setVisited(false);
		}
	    }
	}
	return mainGrid.getStart();
    }

    @Override
    public String toString() {
	String display = "";
	for (int j = 0; j < mainGrid.getVertexes().size(); j++) {
	    for (int i = 0; i < mainGrid.getVertexes().size(); i++) {
		if (i != 0) display += "-----";
		display += mainGrid.getVertexes().get(i).get(j);
	    }
	    if (j < mainGrid.getVertexes().size() - 1) display += "\n|\s\s";
	    for (int k = 0; k < innerGrid.getVertexes().size(); k++) {
		if (j < innerGrid.getVertexes().size()) display += innerGrid.getVertexes().get(k).get(j) + "\s\s|\s\s";
	    }
	    display += "\n";
	}
	return display;
    }
}