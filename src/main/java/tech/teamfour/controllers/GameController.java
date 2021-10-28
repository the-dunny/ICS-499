package tech.teamfour.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.teamfour.services.GameServce;
import tech.teamfour.services.GameServceImpl;

// FOR DEBUG USE

@RestController
public class GameController {
    final GameServce gameServce;

    @Autowired
    public GameController(GameServceImpl gameServce) {
        this.gameServce = gameServce;
    }

    @GetMapping("game/newGame")
    public ResponseEntity getNewGame(@RequestParam(defaultValue = "5", required = false) int gridSize){
        return new ResponseEntity<>(gameServce.getNewPuzzle(gridSize), HttpStatus.OK);
    }

    @GetMapping("game/currentGame")
    public ResponseEntity getPlayerMove(@RequestParam int keyPressed){
        boolean wasMoveSuccessful = gameServce.validatePlayerMove(keyPressed);
        if(wasMoveSuccessful){
            return new ResponseEntity(gameServce.getUpdatedPuzzle(), HttpStatus.OK);
        }else{
            return new ResponseEntity(new String("illegal move"), HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("game/currentTime")
    public ResponseEntity getCurrentTime(){
        return new ResponseEntity(gameServce.getTimerTime(), HttpStatus.OK);
    }

    @GetMapping("game/stopTimer")
    public ResponseEntity stopTimer(){
        gameServce.stopTimer();
        return new ResponseEntity(new String("Timer stopped"), HttpStatus.OK);
    }

    @GetMapping("game/startTimer")
    public ResponseEntity startTimer(){
        gameServce.startTimer();
        return new ResponseEntity(new String("Timer started"), HttpStatus.OK);
    }

}
//    LinePuzzle game;
//    Line path;
//    Point location;
//    Point end;
//    Scanner scanner = new Scanner(System.in);
//    public Game(LinePuzzle puzzle) {
//	this.game = puzzle;
//	this.path = new Line();
//	game.generate();
//	setLocation(game.getMainGrid().getStart());
//	game.getMainGrid().getStart().setVisited(true);
//	this.end = game.getMainGrid().getEnd();
//    }
//
//    public void RunGame() throws InterruptedException {
//	while (!end.isTravel()) {
//	    System.out.println("\r" + game.toString() + "\nEnter Up, Down, Left or Right to Move");
//	    System.out.println("S is the Start, E is the end, O are points you can travel on, ! are required points, # are traveled points and X is unpassable");
//	    if (Move(scanner.nextLine()))
//		ChangeLocation();
//	    if (end.isTravel()) {
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
//	if (game.getMainGrid().getPoint(location).isTravel() == true) {
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
//	if (game.getMainGrid().getNorth(location).isTravel()) 
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
//	if (game.getMainGrid().getSouth(location).isTravel()) 
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
//	if (game.getMainGrid().getWest(location).isTravel()) 
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
//	if (game.getMainGrid().getEast(location).isTravel())
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