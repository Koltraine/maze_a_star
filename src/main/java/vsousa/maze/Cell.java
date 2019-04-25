package vsousa.maze;

import java.util.ArrayList;
import java.util.List;

public class Cell {
	
	private int x, y;
	
	protected Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int[] getXY() {
		int[] xy = {x, y};
		return xy; 
	}
}
