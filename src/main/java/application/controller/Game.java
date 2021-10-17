package application.controller;

import java.util.Scanner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import application.events.TimerStart;
import application.events.TimerStop;
import application.model.Line;
import application.model.LinePuzzle;
import application.model.Point;
import application.states.GameContext;

@RestController
public class Game {
    LinePuzzle game;
    Line path;
    Point location;
    Point end;
    Scanner scanner = new Scanner(System.in);
    GameContext gameContext = GameContext.instance();

    @GetMapping("/game")
    public String hostGame() {
	return "Under Construction";
    }

    public Game(LinePuzzle puzzle) {
	this.game = puzzle;
	this.path = new Line();
	game.generate();
	setLocation(game.getMainGrid().getStart());
	game.getMainGrid().getStart().setVisited(true);
	this.end = game.getMainGrid().getEnd();
    }

    public void RunGame() throws InterruptedException {
	while (!end.isTravel()) {
	    gameContext.handleEvent(TimerStart.instance());
	    System.out.println("\r" + game.toString() + "\nEnter Up, Down, Left or Right to Move");
	    System.out.println("S is the Start, E is the end, O are points you can travel on, # are traveled points and X is unpassable");
	    if (Move(scanner.nextLine()))
		ChangeLocation();
	}
	scanner.close();
	System.out.println("Level Complete");
	gameContext.handleEvent(TimerStop.instance());
    }

    private void ChangeLocation() {
	if (getGridPoint(location.getX(), location.getY()).isTravel() == true) {
	    Point lastLocation = path.getLine().pop();
	    path.getLine().pop();
	    getGridPoint(lastLocation.getX(), lastLocation.getY()).setVisited(false);
	}
	getGridPoint(location.getX(), location.getY()).setVisited(true);
    }

    private boolean Move(String nextLine) throws InterruptedException {
	switch (nextLine.toUpperCase()) {

	case "UP":
	    if (checkUp()) {
		path.getLine().push(new Point(location.getX(), location.getY()));
		location.setY(location.getY() + 1);
	    } else {
		System.out.println("Illegal Move");
		return false;
	    } break;

	case "DOWN": 
	    if (checkDown()) {
		path.getLine().push(new Point(location.getX(), location.getY()));
		location.setY(location.getY() - 1);
	    } else {
		System.out.println("Illegal Move");
		return false;
	    } break;

	case "LEFT": 
	    if (checkLeft()) {
		path.getLine().push(new Point(location.getX(), location.getY()));
		location.setX(location.getX() - 1);
	    } else {
		System.out.println("Illegal Move");
		return false;
	    } break;

	case "RIGHT":
	    if (checkRight()) {
		path.getLine().push(new Point(location.getX(), location.getY()));
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

    private boolean checkUp() {
	boolean temp = false;
	if (location.getY() < getGridSize() - 1)
	    temp = true;
	else 
	    return temp;

	if (getGridPoint(location.getX(), location.getY() + 1).isTravel()) 
	    if (getGridPoint(location.getX(), location.getY() + 1) 
		    != getGridPoint(path.getLine().peek().getX(), path.getLine().peek().getY()))
		temp = false;
	    else
		temp = true;

	if (getGridPoint(location.getX(), location.getY() + 1).isDead()) 
	    temp = false;

	return temp;
    }

    private boolean checkDown() {
	boolean temp = false;
	if (location.getY() > 0) 
	    temp = true; 
	else 
	    return temp;

	if (getGridPoint(location.getX(), location.getY() - 1).isTravel()) 
	    if (getGridPoint(location.getX(), location.getY() - 1) 
		    != getGridPoint(path.getLine().peek().getX(), path.getLine().peek().getY()))
		temp = false;
	    else
		temp = true;

	if (getGridPoint(location.getX(), location.getY() - 1).isDead()) 
	    temp = false;

	return temp;
    }

    private boolean checkLeft() {
	boolean temp = false;
	if (location.getX() > 0) 
	    temp = true;
	else 
	    return temp;

	if (getGridPoint(location.getX() - 1, location.getY()).isTravel()) 
	    if (getGridPoint(location.getX() - 1, location.getY()) 
		    != getGridPoint(path.getLine().peek().getX(), path.getLine().peek().getY()))
		temp = false;
	    else
		temp = true;

	if (getGridPoint(location.getX() - 1, location.getY()).isDead())
	    temp = false;

	return temp;
    }

    private boolean checkRight() {
	boolean temp = false;
	if (location.getX() < getGridSize() - 1) 
	    temp = true;
	else 
	    return temp;

	if (getGridPoint(location.getX() + 1, location.getY()).isTravel()) 
	    if (getGridPoint(location.getX() + 1, location.getY()) 
		    != getGridPoint(path.getLine().peek().getX(), path.getLine().peek().getY()))
		temp = false;
	    else
		temp = true;

	if (getGridPoint(location.getX() + 1, location.getY()).isDead()) 
	    temp = false;

	return temp;
    }

    public int getGridSize() {
	return game.getMainGrid().getVertexes().size();
    }

    public Point getGridPoint(int x, int y) {
	return game.getMainGrid().getPoint(x, y);
    }

    public Point getLocation() {
	return location;
    }

    public void setLocation(Point location) {
	this.location = new Point(location.getX(), location.getY());
    }

    public LinePuzzle getGame() {
	return game;
    }

    public void setGame(LinePuzzle game) {
	this.game = game;
    }
}