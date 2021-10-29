package tech.teamfour.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TravelGrid extends Grid {
    public TravelGrid(int size) {
	Random rand = new Random();
	size -= 1;
	int start_offset = rand.nextInt(size);
	int end_offset = rand.nextInt(size);
	this.vertexes = new ArrayList<List<Point>>(size);

	for (int x = 0; x <= size; x++)
	{
	    List<Point> row = new ArrayList<Point>(size);
	    for (int y = size; y >= 0; y--) {

		if (x == 0 && y == size - end_offset) {
		    this.end = new Point(x, y, 1);
		    row.add(this.end);
		}
		else if (x == size && y == start_offset) {
		    this.start = new Point(x, y, 0);
		    row.add(this.start);
		}
		else row.add(new Point(x, y));   

	    }
	    this.vertexes.add(row);
	}
    }
}
