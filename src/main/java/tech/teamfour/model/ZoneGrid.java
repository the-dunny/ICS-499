package tech.teamfour.model;

import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ZoneGrid extends Grid {
    public ZoneGrid(int size) {
	size -= 2;
	this.vertexes = new ArrayList<List<Point>>(size);

	for (int x = 0; x <= size; x++)
	{
	    List<Point> row = new ArrayList<Point>(size);
	    for (int y = size; y >= 0; y--) {
		row.add(new Point(x, y));
	    }
	    this.vertexes.add(row);
	}
    }
}
