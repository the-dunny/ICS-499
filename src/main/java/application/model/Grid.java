package application.model;

import java.util.List;

public class Grid {
    private List<Point> vertexes;
    private List<Line> playerLines;
    private List<Point> deadVertexes;
    private Point start;
    private Point end;
    
    /**
     * @return the vertexes
     */
    public List<Point> getVertexes() {
	return vertexes;
    }
    /**
     * @param vertexes the vertexes to set
     */
    public void setVertexes(List<Point> vertexes) {
	this.vertexes = vertexes;
    }
    /**
     * @return the playerLines
     */
    public List<Line> getPlayerLines() {
	return playerLines;
    }
    /**
     * @param playerLines the playerLines to set
     */
    public void setPlayerLines(List<Line> playerLines) {
	this.playerLines = playerLines;
    }
    /**
     * @return the deadVertexes
     */
    public List<Point> getDeadVertexes() {
	return deadVertexes;
    }
    /**
     * @param deadVertexes the deadVertexes to set
     */
    public void setDeadVertexes(List<Point> deadVertexes) {
	this.deadVertexes = deadVertexes;
    }
    /**
     * @return the start
     */
    public Point getStart() {
	return start;
    }
    /**
     * @param start the start to set
     */
    public void setStart(Point start) {
	this.start = start;
    }
    /**
     * @return the end
     */
    public Point getEnd() {
	return end;
    }
    /**
     * @param end the end to set
     */
    public void setEnd(Point end) {
	this.end = end;
    }

}
