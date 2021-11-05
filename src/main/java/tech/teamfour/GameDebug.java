
//package tech.teamfour;
//
//import java.util.Scanner;
//
//import org.springframework.web.bind.annotation.*;
//
//import tech.teamfour.model.*;
//
//@RestController
//public class GameDebug {
//    LinePuzzle game;
//    Line path;
//    Point location;
//    Point end;
//    Scanner scanner = new Scanner(System.in);
//
//    @GetMapping("/game")
//    public String hostGame() {
//	return "Under Construction";
//    }
//
//    public GameDebug(LinePuzzle puzzle) {
//	this.game = puzzle;
//	this.path = new Line();
//	game.generate();
//	setLocation(game.getMainGrid().getStart());
//	game.getMainGrid().getStart().setVisited(true);
//	this.end = game.getMainGrid().getEnd();
//    }
//
//    public void RunGame() throws InterruptedException {
//	while (!end.isVisited()) {
//	    System.out.println("\r" + game.toString() + "\nEnter Up, Down, Left or Right to Move");
//	    System.out.println("S is the Start, E is the end, O are points you can travel on, ! are required points, # are traveled points and X is unpassable");
//	    if (Move(scanner.nextLine()))
//		ChangeLocation();
//	    if (end.isVisited()) {
//		if (!game.isComplete()) {
//		    setLocation(game.retry());
//		    System.out.println("Invalid Solution");
//		}
//	    }
//	}
//	scanner.close();
//	System.out.println("Level Complete");
//    }
//
//    private void ChangeLocation() {
//	if (game.getMainGrid().getPoint(location).isVisited() == true) {
//	    Point lastLocation = path.getLine().pop();
//	    path.getLine().pop();
//	    game.getMainGrid().getPoint(lastLocation).setVisited(false);
//	}
//	game.getMainGrid().getPoint(location).setVisited(true);
//    }
//
//    private boolean Move(String nextLine) throws InterruptedException {
//	switch (nextLine.toUpperCase()) {
//	case "UP":
//	    if (checkUp()) {
//		path.getLine().push(new Point(location));
//		location.setY(location.getY() + 1);
//	    } else {
//		System.out.println("Illegal Move");
//		return false;
//	    } break;
//
//	case "DOWN": 
//	    if (checkDown()) {
//		path.getLine().push(new Point(location));
//		location.setY(location.getY() - 1);
//	    } else {
//		System.out.println("Illegal Move");
//		return false;
//	    } break;
//
//	case "LEFT": 
//	    if (checkLeft()) {
//		path.getLine().push(new Point(location));
//		location.setX(location.getX() - 1);
//	    } else {
//		System.out.println("Illegal Move");
//		return false;
//	    } break;
//
//	case "RIGHT":
//	    if (checkRight()) {
//		path.getLine().push(new Point(location));
//		location.setX(location.getX() + 1);
//	    } else {
//		System.out.println("Illegal Move");
//		return false;
//	    } break;
//	default:
//	    return false;
//	}
//	return true;
//    }
//
//    private boolean checkUp() {
//	boolean temp = false;
//	if (location.getY() < getGridSize() - 1)
//	    temp = true;
//	else 
//	    return temp;
//
//	if (game.getMainGrid().getNorth(location).isVisited()) 
//	    if (game.getMainGrid().getNorth(location) != game.getMainGrid().getPoint(path.getLine().peek()))
//		temp = false;
//	    else
//		temp = true;
//
//	if (game.getMainGrid().getNorth(location).isDead()) 
//	    temp = false;
//
//	return temp;
//    }
//
//    private boolean checkDown() {
//	boolean temp = false;
//	if (location.getY() > 0) 
//	    temp = true; 
//	else 
//	    return temp;
//
//	if (game.getMainGrid().getSouth(location).isVisited()) 
//	    if (game.getMainGrid().getSouth(location) != game.getMainGrid().getPoint(path.getLine().peek()))
//		temp = false;
//	    else
//		temp = true;
//
//	if (game.getMainGrid().getSouth(location).isDead()) 
//	    temp = false;
//
//	return temp;
//    }
//
//    private boolean checkLeft() {
//	boolean temp = false;
//	if (location.getX() > 0) 
//	    temp = true;
//	else 
//	    return temp;
//
//	if (game.getMainGrid().getWest(location).isVisited()) 
//	    if (game.getMainGrid().getWest(location) != game.getMainGrid().getPoint(path.getLine().peek()))
//		temp = false;
//	    else
//		temp = true;
//
//	if (game.getMainGrid().getWest(location).isDead())
//	    temp = false;
//
//	return temp;
//    }
//
//    private boolean checkRight() {
//	boolean temp = false;
//	if (location.getX() < getGridSize() - 1) 
//	    temp = true;
//	else 
//	    return temp;
//
//	if (game.getMainGrid().getEast(location).isVisited())
//	    if (game.getMainGrid().getEast(location) != game.getMainGrid().getPoint(path.getLine().peek()))
//		temp = false;
//	    else
//		temp = true;
//
//	if (game.getMainGrid().getEast(location).isDead())
//	    temp = false;
//
//	return temp;
//    }
//
//    public int getGridSize() {
//	return game.getMainGrid().getVertexes().size();
//    }
//
//    public Point getLocation() {
//	return location;
//    }
//
//    public void setLocation(Point location) {
//	this.location = new Point(location);
//    }
//
//    public LinePuzzle getGame() {
//	return game;
//    }
//
//    public void setGame(LinePuzzle game) {
//	this.game = game;
//    }
//}

