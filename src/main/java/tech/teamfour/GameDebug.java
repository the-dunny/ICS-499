package tech.teamfour;

import java.util.Scanner;

import tech.teamfour.model.*;


/**
 * The Class GameDebug.
 */
public class GameDebug {
    
    /** The game. */
    LinePuzzle game;
    
    /** The path. */
    Line path;
    
    /** The location. */
    Point location;
    
    /** The end. */
    Point end;
    
    /** The scanner. */
    Scanner scanner = new Scanner(System.in);

    /**
     * Instantiates a new game debug.
     *
     * @param puzzle the puzzle
     */
    public GameDebug(LinePuzzle puzzle) {
	this.game = puzzle;
	this.path = game.getPath();
	game.generate();
	setLocation(game.getMainGrid().getStart());
	game.getMainGrid().getStart().setVisited(true);
	path.getLine().push(game.getMainGrid().getStart());
	this.end = game.getMainGrid().getEnd();
    }

    /**
     * Run game.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void RunGame() throws InterruptedException {
	while (!end.isVisited()) {
	    System.out.println("\r" + game.toString() + "\nEnter Up, Down, Left or Right to Move");
	    System.out.println("S is the Start, E is the end, O are points you can travel on, ! are required points, # are traveled points and X is unpassable");
	    if (Move(scanner.nextLine()))
		ChangeLocation();
	    System.out.println(path);
	    if (end.isVisited()) {
		if (!game.isComplete()) {
		    setLocation(game.retry());
		    System.out.println("Invalid Solution");
		}
	    }
	}
	scanner.close();
	System.out.println("Level Complete");
    }

    /**
     * Change location.
     */
    private void ChangeLocation() {
	if (game.getMainGrid().getPoint(location).isVisited() == true) {
	    Point lastLocation = path.getLine().pop();
	    path.getLine().pop();
	    game.getMainGrid().getPoint(lastLocation).setVisited(false);
	}
	game.getMainGrid().getPoint(location).setVisited(true);
    }

    /**
     * Move.
     *
     * @param nextLine the next line
     * @return true, if successful
     * @throws InterruptedException the interrupted exception
     */
    private boolean Move(String nextLine) throws InterruptedException {
	switch (nextLine.toUpperCase()) {
	case "UP":
	    if (game.getMainGrid().checkUp(path, location)) {
		path.getLine().push(new Point(location));
		location.setY(location.getY() + 1);
	    } else {
		System.out.println("Illegal Move");
		return false;
	    } break;

	case "DOWN": 
	    if (game.getMainGrid().checkDown(path, location)) {
		path.getLine().push(new Point(location));
		location.setY(location.getY() - 1);
	    } else {
		System.out.println("Illegal Move");
		return false;
	    } break;

	case "LEFT": 
	    if (game.getMainGrid().checkLeft(path, location)) {
		path.getLine().push(new Point(location));
		location.setX(location.getX() - 1);
	    } else {
		System.out.println("Illegal Move");
		return false;
	    } break;

	case "RIGHT":
	    if (game.getMainGrid().checkRight(path, location)) {
		path.getLine().push(new Point(location));
		location.setX(location.getX() + 1);
	    } else {
		System.out.println("Illegal Move");
		return false;
	    } break;
	default:
	    return false;
	}
	return true;
    }

    /**
     * Gets the grid size.
     *
     * @return the grid size
     */
    public int getGridSize() {
	return game.getMainGrid().getVertexes().size();
    }

    /**
     * Gets the location.
     *
     * @return the location
     */
    public Point getLocation() {
	return location;
    }

    /**
     * Sets the location.
     *
     * @param location the new location
     */
    public void setLocation(Point location) {
	this.location = new Point(location);
    }

    /**
     * Gets the game.
     *
     * @return the game
     */
    public LinePuzzle getGame() {
	return game;
    }

    /**
     * Sets the game.
     *
     * @param game the new game
     */
    public void setGame(LinePuzzle game) {
	this.game = game;
    }
}