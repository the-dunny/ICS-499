package tech.teamfour.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


/**
 * The Class LinePuzzle.
 */
@Data
@NoArgsConstructor
public class LinePuzzle {

    /** The unique ID. */
    private String uniqueID = UUID.randomUUID().toString();

    /** The main grid. */
    private TravelGrid mainGrid;

    /** The inner grid. */
    private ZoneGrid innerGrid;

    /** The path. */
    private Line path;

    /** The score value. */
    private int score_value;

    /**
     * Instantiates a new line puzzle.
     *
     * @param size the size
     */
    public LinePuzzle(int size) {
	if (size < 3) size = 3;
	this.mainGrid = new TravelGrid(size);
	this.innerGrid = new ZoneGrid(size);
	this.path = new Line();
	this.score_value = 0;
    }

    /**
     * Travel.
     *
     * @param x the x
     * @param y the y
     */
    public void travel(int x, int y) {
	System.out.println(this.mainGrid);
    }

    /**
     * Generate a LinePuzzle with obstacles and additional features.
     */
    public void generate() {
	primsMaze();
	pointsAndZones();

	boolean w_zone = false;
	boolean b_zone = false;
	for (List<Point> row : innerGrid.getVertexes()) {
	    for (Point point : row) {
		if (point.getZone() == 1) w_zone = true;
		if (point.getZone() == 2) b_zone = true;
	    }
	}

	// if puzzle is too simple, make a new one.
	if (!w_zone || !b_zone) {
	    reset();
	    generate();
	}
    }

    /**
     * Generate a random "loose" maze using Prim's Algorithm.
     */
    public void primsMaze() {
	ArrayList<Grid> gridLayer = new ArrayList<Grid>();
	int size = mainGrid.getVertexes().size();
	int layerCount = 2;

	for (int i = 0; i < layerCount; i++) gridLayer.add(new TravelGrid(mainGrid.getVertexes().size()));

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
	score_value = path.getLine().size();
	int probability = 11 - (13 - mainGrid.getVertexes().size());
	for (Point point : path.getLine()) {
	    Random rand = new Random();
	    if (rand.nextInt(probability) == 0) {
		mainGrid.getPoint(point.getX(), point.getY()).setRequired(true);
	    }
	}

	ArrayList<ArrayList<Point>> zones = randomZones(path);
	for (ArrayList<Point> zone : zones) {
	    for (int j = 0; j < zone.size(); j++) {
		if (j > innerGrid.getVertexes().size()) {
		    zone.get(j).setZone(0);
		}
	    }
	}
    }

    /**
     * Return a random valid path on the main grid, returns empty if there's no available path.
     *
     * @return the line
     */ 
    public Line randomValidPath() {
	TravelGrid tmpGrid = new TravelGrid(mainGrid.getVertexes().size());
	Line randomPath = new Line();
	Point location = new Point(mainGrid.getStart());
	int size = tmpGrid.getVertexes().size();
	int maxattempts = (size * size) * 4;
	List<String> direction = new ArrayList<String>(Arrays.asList("UP", "DOWN", "RIGHT", "LEFT"));
	tmpGrid.mergePaths(mainGrid);
	tmpGrid.setEnd(new Point(mainGrid.getEnd()));

	for (int i = 0; i < maxattempts; i++) {
	    Random rand = new Random();
	    switch (direction.get(rand.nextInt(direction.size()))) {
	    case "UP": {
		if (!tmpGrid.checkUp(randomPath, location)) {
		    direction.remove("UP");
		    break;
		}

		randomPath.getLine().push(new Point(location.getX(), location.getY()));
		tmpGrid.getPoint(location.getX(), location.getY()).setVisited(true);
		location.setY(location.getY() + 1);
		direction = new ArrayList<String>(Arrays.asList("UP", "RIGHT", "LEFT"));
		break;
	    }
	    case "DOWN": {
		if (!tmpGrid.checkDown(randomPath, location)) {
		    direction.remove("DOWN");
		    break;
		}

		randomPath.getLine().push(new Point(location.getX(), location.getY()));
		tmpGrid.getPoint(location.getX(), location.getY()).setVisited(true);
		location.setY(location.getY() - 1);
		direction = new ArrayList<String>(Arrays.asList("DOWN", "RIGHT", "LEFT"));
		break;
	    }
	    case "RIGHT":  {
		if (!tmpGrid.checkRight(randomPath, location)) {
		    direction.remove("DOWN");
		    break;
		}

		randomPath.getLine().push(new Point(location.getX(), location.getY()));
		tmpGrid.getPoint(location.getX(), location.getY()).setVisited(true);
		location.setX(location.getX() + 1);
		direction = new ArrayList<String>(Arrays.asList("UP", "DOWN", "RIGHT"));
		break;
	    }
	    case "LEFT": {
		if (!tmpGrid.checkLeft(randomPath, location)) {
		    direction.remove("DOWN");
		    break;
		}

		randomPath.getLine().push(new Point(location.getX(), location.getY()));
		tmpGrid.getPoint(location.getX(), location.getY()).setVisited(true);
		location.setX(location.getX() - 1);
		direction = new ArrayList<String>(Arrays.asList("UP", "DOWN", "LEFT"));
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
		direction = new ArrayList<String>(Arrays.asList("UP", "DOWN", "RIGHT", "LEFT"));
	    }
	}
	try {
	    return randomValidPath();
	} catch (StackOverflowError soe) {
	    return new Line();
	}
    }

    /**
     * Returns array of lines sectioned off by a given path on the puzzle and draws out those zones to the innerGrid.
     *
     * @param path the path
     * @return the array list
     */
    public ArrayList<ArrayList<Point>> randomZones(Line path) {
	ArrayList<ArrayList<Point>> zones = new ArrayList<ArrayList<Point>>();
	ArrayList<Point> pathList = new ArrayList<Point>();
	Random rand = new Random();
	int size = innerGrid.getVertexes().size();
	ZoneGrid zg = new ZoneGrid(size);
	zg = innerGrid; //temp

	int x = rand.nextInt(size);
	int y = rand.nextInt(size);

	for (Point point : path.getLine()) {
	    point = mainGrid.getPoint(point.getX(), point.getY());
	    pathList.add(point);
	}

	zones.add(new ArrayList<Point>());
	zoneSearch(zg, zones.get(0), pathList, zg.getPoint(x, y), 1);

	x = rand.nextInt(size);
	y = rand.nextInt(size);

	zones.add(new ArrayList<Point>());
	for (int i = 0; i < size; i++) {
	    for (int j = 0; j < size; j++) {
		x = i;
		y = j;
		if (zg.getPoint(x, y).getZone() == 0) {
		    zoneSearch(zg, zones.get(1), pathList, zg.getPoint(x, y), 2);
		    i = j = size;
		}
	    }
	}
	return zones;
    }

    /**
     * Zone search.
     *
     * @param zg the ZoneGrid to find the zones on.
     * @param zone is the zone being mapped out.
     * @param pathList is the given path that the zone is cut from.
     * @param point is the starting point.
     * @param flag is the type of zone.
     * @return the array list
     */
    public ArrayList<Point> zoneSearch(ZoneGrid zg, ArrayList<Point> zone, ArrayList<Point> pathList, Point point, int flag) {
	int x = point.getX();
	int y = point.getY();

	if (zg.checkUp(point)) {
	    int distance = pathList.indexOf(mainGrid.getPoint(point.getX(), point.getY() + 1)) - pathList.indexOf(mainGrid.getPoint(x + 1, y + 1));
	    point.setVisited(true);
	    point.setZone(flag);
	    zone.add(point);
	    if (distance != 1 && distance != -1) {
		zoneSearch(zg, zone, pathList, zg.getUp(point), flag);
	    }
	}

	if (zg.checkDown(point)) {
	    int distance = pathList.indexOf(mainGrid.getPoint(point.getX(), point.getY())) - pathList.indexOf(mainGrid.getPoint(x + 1, y));
	    point.setVisited(true);
	    point.setZone(flag);
	    zone.add(point);
	    if (distance != 1 && distance != -1) {
		zoneSearch(zg, zone, pathList, zg.getDown(point), flag);
	    }
	}

	if (zg.checkLeft(point)) {
	    int distance = pathList.indexOf(mainGrid.getPoint(point.getX(), point.getY())) - pathList.indexOf(mainGrid.getPoint(x, y + 1));
	    point.setVisited(true);
	    point.setZone(flag);
	    zone.add(point);
	    if (distance != 1 && distance != -1) {
		zoneSearch(zg, zone, pathList, zg.getLeft(point), flag);
	    } 
	}

	if (zg.checkRight(point)) {
	    int distance = pathList.indexOf(mainGrid.getPoint(point.getX() + 1, point.getY() + 1)) - pathList.indexOf(mainGrid.getPoint(x + 1, y));
	    point.setVisited(true);
	    point.setZone(flag);
	    zone.add(point);
	    if (distance != 1 && distance != -1) {
		zoneSearch(zg, zone, pathList, zg.getRight(point), flag);
	    }  
	}
	return zone;
    }

    /**
     * Returns true if point belongs in zone.
     *
     * @param pathList is the given path that the zone is cut from.
     * @param point is the starting point.
     * @param flag is the type of zone.
     * @return true, if successful
     */
    public boolean zoneCheck(ArrayList<Point> pathList, Point point, int flag) {
	int x = point.getX();
	int y = point.getY();
	if (point.getZone() != 0 && point.getZone() != flag) return false;
	if (innerGrid.checkUp(point)) {
	    int distance = pathList.indexOf(mainGrid.getPoint(point.getX(), point.getY() + 1)) - pathList.indexOf(mainGrid.getPoint(x + 1, y + 1));
	    point.setVisited(true);
	    if (distance != 1 && distance != -1) {
		if (point.getZone() == 0 || point.getZone() == flag) {
		    if (!zoneCheck(pathList, innerGrid.getUp(point), flag)) return false;
		} else {
		    return false;
		}
	    }
	}

	if (innerGrid.checkDown(point)) {
	    int distance = pathList.indexOf(mainGrid.getPoint(point.getX(), point.getY())) - pathList.indexOf(mainGrid.getPoint(x + 1, y));
	    point.setVisited(true);
	    if (distance != 1 && distance != -1) {
		if (point.getZone() == 0 || point.getZone() == flag) {
		    if (!zoneCheck(pathList, innerGrid.getDown(point), flag)) return false;
		} else {
		    return false;
		}
	    }
	}

	if (innerGrid.checkLeft(point)) {
	    int distance = pathList.indexOf(mainGrid.getPoint(point.getX(), point.getY())) - pathList.indexOf(mainGrid.getPoint(x, y + 1));
	    point.setVisited(true);	    
	    if (distance != 1 && distance != -1) {
		if (point.getZone() == 0 || point.getZone() == flag) {
		    if (!zoneCheck(pathList, innerGrid.getLeft(point), flag)) return false;
		} else {
		    return false;
		}
	    } 
	}

	if (innerGrid.checkRight(point)) {
	    int distance = pathList.indexOf(mainGrid.getPoint(point.getX() + 1, point.getY() + 1)) - pathList.indexOf(mainGrid.getPoint(x + 1, y));
	    point.setVisited(true);	    
	    if (distance != 1 && distance != -1) {
		if (point.getZone() == 0 || point.getZone() == flag) {
		    if (!zoneCheck(pathList, innerGrid.getRight(point), flag)) return false;
		} else {
		    return false;
		}
	    }  
	}
	return true;
    }

    /**
     * Returns true if the puzzle is valid.
     *
     * @return true, if successful
     */
    public boolean validatePuzzle() {
	if (mainGrid.getStart().isDead() && mainGrid.getPoint(mainGrid.getVertexes().size() - 1, 1).isDead() 
		|| mainGrid.getPoint(0, mainGrid.getVertexes().size() - 1).isDead() && mainGrid.getPoint(1, mainGrid.getVertexes().size() - 1).isDead()) {
	    return false;
	}
	return true;
    }

    /**
     * Returns true if the puzzle is solved.
     *
     * @return true, if is complete
     */
    public boolean isComplete() {
	boolean complete = true;
	if (!mainGrid.getEnd().isVisited()) return false;
	for (List<Point> row : mainGrid.getVertexes()) {
	    for (Point point : row) {
		if (point.isRequired() && !point.isVisited()) {
		    return false;
		}
	    }
	}

	ArrayList<Point> pathList = new ArrayList<Point>();

	for (Point point : path.getLine()) {
	    point = mainGrid.getPoint(point.getX(), point.getY());
	    pathList.add(point);
	}

	pathList.add(pathList.get(0));
	pathList.remove(0);

	innerGrid.unVisit();
	for (List<Point> row : innerGrid.getVertexes()) {
	    for (Point point : row) {
		if (point.getZone() != 0) {
		    if(!zoneCheck(pathList, point, point.getZone())) {
			complete = false;
		    }
		}
	    }
	}
	innerGrid.unVisit();
	return complete;
    }

    /**
     * Restarts the player back to the beginning.
     *
     * @return the point
     */
    public Point retry() {
	for (List<Point> row : mainGrid.getVertexes()) {
	    for (Point point : row) {
		if (point.isVisited()) {
		    point.setVisited(false);
		}
	    }
	}
	path.getLine().clear();
	mainGrid.setLocation(new Point(mainGrid.getStart()));
	this.path.getLine().push(mainGrid.getLocation());
	return mainGrid.getLocation();
    }

    /**
     * Reset.
     */
    public void reset() {
	this.mainGrid = new TravelGrid(mainGrid.getVertexes().size());
	this.innerGrid = new ZoneGrid(mainGrid.getVertexes().size());
    }

    /**
     * To string.
     *
     * @return the string
     */
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