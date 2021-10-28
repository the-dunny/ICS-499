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

@Data
@NoArgsConstructor
public class GameContext implements Notifiable {

    private LinePuzzle game;
    private Line path;
    public Point location;
    private Point end;
    private Timer timer;
    private int time;

    @Autowired
    public GameContext(LinePuzzle puzzle, Line line){
        this.game = puzzle;
        this.path = line;
        game.generate();
        setLocation(game.getMainGrid().getStart());
        game.getMainGrid().getStart().setVisited(true);
        this.end = game.getMainGrid().getEnd();
        this.timer = new Timer(this, 0);
        timer.start();
    }

    private void changeLocation(){
        if(game.getMainGrid().getPoint(location).isTravel() == true) {
            Point lastLocation = path.getLine().pop();
            path.getLine().pop();
            game.getMainGrid().getPoint(lastLocation).setVisited(false);
        }
        game.getMainGrid().getPoint(location).setVisited(true);
    }
    public boolean move(String nextLine){
        switch (nextLine.toUpperCase()) {
            case "UP":
                if (checkUp()) {
                    path.getLine().push(new Point(location));
                    location.setY(location.getY() + 1);
                } else {
                    System.out.println("Illegal Move");
                    return false;
                } break;

            case "DOWN":
                if (checkDown()) {
                    path.getLine().push(new Point(location));
                    location.setY(location.getY() - 1);
                } else {
                    System.out.println("Illegal Move");
                    return false;
                } break;

            case "LEFT":
                if (checkLeft()) {
                    path.getLine().push(new Point(location));
                    location.setX(location.getX() - 1);
                } else {
                    System.out.println("Illegal Move");
                    return false;
                } break;

            case "RIGHT":
                if (checkRight()) {
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
    private boolean checkUp() {
        boolean temp = false;
        if (location.getY() < getGridSize() - 1)
            temp = true;
        else
            return temp;

        if (game.getMainGrid().getNorth(location).isTravel())
            if (game.getMainGrid().getNorth(location) != game.getMainGrid().getPoint(path.getLine().peek()))
                temp = false;
            else
                temp = true;

        if (game.getMainGrid().getNorth(location).isDead())
            temp = false;

        return temp;
    }

    private boolean checkDown() {
        boolean temp = false;
        if (location.getY() > 0)
            temp = true;
        else
            return temp;

        if (game.getMainGrid().getSouth(location).isTravel())
            if (game.getMainGrid().getSouth(location) != game.getMainGrid().getPoint(path.getLine().peek()))
                temp = false;
            else
                temp = true;

        if (game.getMainGrid().getSouth(location).isDead())
            temp = false;

        return temp;
    }

    private boolean checkLeft() {
        boolean temp = false;
        if (location.getX() > 0)
            temp = true;
        else
            return temp;

        if (game.getMainGrid().getWest(location).isTravel())
            if (game.getMainGrid().getWest(location) != game.getMainGrid().getPoint(path.getLine().peek()))
                temp = false;
            else
                temp = true;

        if (game.getMainGrid().getWest(location).isDead())
            temp = false;

        return temp;
    }

    private boolean checkRight() {
        boolean temp = false;
        if (location.getX() < getGridSize() - 1)
            temp = true;
        else
            return temp;

        if (game.getMainGrid().getEast(location).isTravel())
            if (game.getMainGrid().getEast(location) != game.getMainGrid().getPoint(path.getLine().peek()))
                temp = false;
            else
                temp = true;

        if (game.getMainGrid().getEast(location).isDead())
            temp = false;

        return temp;
    }

    public int getGridSize() {
        return game.getMainGrid().getVertexes().size();
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = new Point(location);
    }

    public LinePuzzle getGame() {
        return game;
    }

    public void setGame(LinePuzzle game) {
        this.game = game;
    }

    public int getTime(){
        return timer.getTimeValue();
    }

    @Override
    public void handleEvent(TimerTickedEvent event) {
        this.time++;
    }

    @Override
    public void handleEvent(TimerStart event) {
        this.time = 0;
    }

    @Override
    public void handleEvent(TimerStop event) {
        this.timer.stop();
    }
}
