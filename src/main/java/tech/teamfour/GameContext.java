package tech.teamfour;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import tech.teamfour.model.Line;
import tech.teamfour.model.LinePuzzle;
import tech.teamfour.model.Point;

@Data
@NoArgsConstructor
public class GameContext {

    private LinePuzzle game;
    private Line path;
    private Point location;
    private Point end;

    @Autowired
    public GameContext(LinePuzzle puzzle) {
	this.game = puzzle;
	this.path = puzzle.getPath();
	game.generate();
	setLocation(game.getMainGrid().getStart());
	game.getMainGrid().getStart().setVisited(true);
	this.end = game.getMainGrid().getEnd();
    }

    private void changeLocation() {
	if(game.getMainGrid().getPoint(location).isVisited() == true) {
	    Point lastLocation = path.getLine().pop();
	    path.getLine().pop();
	    game.getMainGrid().getPoint(lastLocation).setVisited(false);
	}
	game.getMainGrid().getPoint(location).setVisited(true);
    }
    
    public boolean move(String nextLine) throws InterruptedException {
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

    public int getGridSize() {
	return game.getMainGrid().getVertexes().size();
    }
}
