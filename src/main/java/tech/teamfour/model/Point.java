package tech.teamfour.model;

import lombok.Data;


/**
 * The Class Point.
 */
@Data
public class Point {

    /** The x. */
    private int x;

    /** The y. */
    private int y;

    /** The visited. */
    private boolean visited;

    /** The required. */
    private boolean required;

    /** The dead. */
    private boolean dead;

    /** The start. */
    private boolean start;

    /** The end. */
    private boolean end;

    /** The zone. */
    private int zone;

    /**
     * No argument constructor.
     */
    public Point() {
	this.setVisited(false);
	this.setRequired(false);
	this.setDead(true);
	this.setStart(false);
	this.setEnd(false);
	this.setZone(0);
    }

    /**
     * Initialize the point.
     *
     * @param point the point
     */
    public Point(Point point) {
	this.setX(point.getX());
	this.setY(point.getY());
	this.setVisited(point.isVisited());
	this.setRequired(point.isRequired());
	this.setDead(point.isDead());
	this.setStart(false);
	this.setEnd(false);
	this.setZone(0);
    }

    /**
     * Initialize the point.
     *
     * @param x the x
     * @param y the y
     */
    public Point(int x, int y) {
	this.setX(x);
	this.setY(y);
	this.setVisited(false);
	this.setRequired(false);
	this.setDead(true);
	this.setStart(false);
	this.setEnd(false);
	this.setZone(0);
    }

    /**
     * Initialize the point and flag for unique properties.
     * <br>Keys:
     * <br>0 = Start Adjacent
     * <br>1 = End Adjacent
     * <br>2 = Required Point
     *
     * @param x the x
     * @param y the y
     * @param flag the flag
     */
    public Point(int x, int y, int flag) {
	this.setX(x);
	this.setY(y);
	this.setVisited(false);
	this.setRequired(false);
	this.setDead(true);
	this.setStart(false);
	this.setEnd(false);
	this.setZone(0);

	switch (flag) {
	case 0:
	    this.setStart(true);
	    break;
	case 1:
	    this.setEnd(true);
	    break;
	case 2:
	    this.setRequired(true);
	    break;
	default:
	    break;
	}
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
	return x + "," + y; 

    }
}