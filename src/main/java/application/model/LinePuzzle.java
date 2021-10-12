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

    public void travel(int x, int y) {
	System.out.println(this.mainGrid);
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
	for (int j = 0; j < mainGrid.getVertexes().size(); j++) {
	    for (int i = 0; i < mainGrid.getVertexes().size(); i++) {
		if (i != 0) display += "-----";
		display += mainGrid.getVertexes().get(i).get(j);
	    }
	    if (j < mainGrid.getVertexes().size() - 1) display += "\n|\s\s";
	    for (int k = 0; k < innerGrid.getVertexes().size(); k++) {
		if (j < innerGrid.getVertexes().size()) display += innerGrid.getVertexes().get(k).get(j) + "\s\s|\s\s";
	    }
	    display += "\n";
	}
	return display;
    }
}
