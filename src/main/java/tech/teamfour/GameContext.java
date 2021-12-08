package tech.teamfour;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import tech.teamfour.events.TimerStart;
import tech.teamfour.events.TimerStop;
import tech.teamfour.events.TimerTickedEvent;
import tech.teamfour.model.Line;
import tech.teamfour.model.LinePuzzle;
import tech.teamfour.model.Point;
import tech.teamfour.timer.Notifiable;
import tech.teamfour.timer.Timer;

/**
 * The Class GameContext.
 */
@Data
@NoArgsConstructor
public class GameContext implements Notifiable {

    /** The game. */
    private LinePuzzle game;

    /** The path. */
    private Line path;

    /** The location. */
    private Point location;

    /** The end. */
    private Point end;

    /** The timer. */
    private Timer timer;

    /**
     * Instantiates a new game context.
     *
     * @param puzzle the puzzle
     */
    @Autowired
    public GameContext(LinePuzzle puzzle) {
	this.game = puzzle;
	this.path = game.getPath();
	game.generate();
	setLocation(game.getMainGrid().getLocation());
	game.getMainGrid().getStart().setVisited(true);
	path.getLine().push(game.getMainGrid().getLocation());
	this.end = game.getMainGrid().getEnd();
	this.timer = new Timer(this, 0);
	timer.start();
    }

    /**
     * Change location.
     *
     * @return true, if successful
     */
    public boolean ChangeLocation() {
	if (game.getMainGrid().getPoint(location).isVisited() == true) {
	    Point lastLocation = path.getLine().pop();
	    path.getLine().pop();
	    game.getMainGrid().getPoint(lastLocation).setVisited(false);
	}

	game.getMainGrid().getPoint(location).setVisited(true);

	if (end.isVisited()) {
	    if (!game.isComplete()) {
		game.retry();
		setLocation(game.getMainGrid().getLocation());
		game.getMainGrid().getStart().setVisited(true);
		System.out.println("Invalid Solution");
	    } else {
		return true;
	    }
	}
	return false;
    }

    /**
     * Move.
     *
     * @param nextLine the next line
     * @return true, if successful
     * @throws InterruptedException the interrupted exception
     */
    public boolean Move(String nextLine) throws InterruptedException {
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
     * Gets the time.
     *
     * @return the time
     */
    public int getTime() {
	return this.timer.getTimeValue();
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
     * Handle event.
     *
     * @param event the event
     */
    @Override
    public void handleEvent(TimerTickedEvent event) {

    }

    /**
     * Handle event.
     *
     * @param event the event
     */
    @Override
    public void handleEvent(TimerStart event) {

    }

    /**
     * Handle event.
     *
     * @param event the event
     */
    @Override
    public void handleEvent(TimerStop event) {

    }
}
