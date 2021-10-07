package application.model;

public class LinePuzzle {
    private Grid mainGrid;
    private Grid innerGrid;

    public LinePuzzle() {
	this.mainGrid = new Grid();
	this.innerGrid = new Grid();
    }

    public LinePuzzle(int size) {
	if (size < 3) size = 3;
	this.mainGrid = new Grid(size, false);
	this.innerGrid = new Grid(size, true);
    }

    /**
     * @return the mainGrid
     */
    public Grid getMainGrid() {
	return mainGrid;
    }
    /**
     * @param mainGrid the mainGrid to set
     */
    public void setMainGrid(Grid mainGrid) {
	this.mainGrid = mainGrid;
    }
    /**
     * @return the innerGrid
     */
    public Grid getInnerGrid() {
	return innerGrid;
    }
    /**
     * @param innerGrid the innerGrid to set
     */
    public void setInnerGrid(Grid innerGrid) {
	this.innerGrid = innerGrid;
    }
    @Override
    public String toString() {
	String display = "";
	for (int i = 0; i < mainGrid.getVertexes().size(); i++) {
	    for (int j = 0; j < mainGrid.getVertexes().size(); j++) {
		display += mainGrid.getVertexes().get(i).get(j) + "\s\s\s\s\s";
	    }
	    display += "\n\s\s\s";
	    for (int k = 0; k < innerGrid.getVertexes().size(); k++) {
		if (i < innerGrid.getVertexes().size()) {
		    display += innerGrid.getVertexes().get(i).get(k) + "\s\s\s\s\s";
		}
	    }
	    display += "\n";
	}
	return display;
    }
}
