package vsousa.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {  
	
	private static final int[][] RANDOM = {{1,0},{-1,0},{0,1},{0,-1}};
	
	private Cell cells[][];
	private List<Cell> visited = new ArrayList<Cell>();
	private int size;
	
	public Board(int size) {
		this.size = size;
		cells = new Cell[size][size];
		
		for (int x=0; x < size; x++ ) {
			for (int y=0; y < size; y++) {
				cells[x][y] = new Cell(x, y);
			}	
		}
	}
	
	public Cell getCell(int x, int y) {
		if ( !(0 <= x && x < size)  || !(0 <= y && y < size )) {
			return null;
		}
		return cells[x][y];
	}
	
	public Cell getRandomNeighbor( Cell cell) {
		int[] xy = cell.getXY();
		Cell random = null;
		
		while ( random == null ) {
			int rndId = new Random().nextInt(RANDOM.length);
			
			int rx = (int) (xy[0] + RANDOM[rndId][0] );
			int ry = (int) (xy[1] + RANDOM[rndId][1] );
			random = getCell ( rx, ry);
		}
		return random;
	}
	
	public boolean Allvisited() {
		float percent = ( ( (float) visited.size() )/( (float)size*size) );
		System.out.println(visited.size() +"/" + size*size + "  == " + percent );
		return visited.size() == size*size;
	}
	
	public boolean isVisited( Cell cell ) {
		return visited.contains(cell);
	}
	
	public void setVisited( Cell cell ) {
		visited.add(cell);
	}
}
