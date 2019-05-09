package vsousa.maze.generator;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {  
	
	public static final int[][] NEIGTHBOURS = {{0,-1},{0,1},{-1,0},{1,0}};  // U, D, L, R
	
	private Cell cells[][];
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
	
	public List<Cell> getCells() {
		List<Cell> res = new ArrayList<Cell>();
		for (int x=0; x < size; x++ ) {
			for (int y=0; y < size; y++) {
				res.add( cells[x][y] );
			}	
		}
		return res;
	}
	
	public Cell getCell(int x, int y) {
		if ( !(0 <= x && x < size)  || !(0 <= y && y < size )) {
			return null;
		}
		return cells[x][y];
	}
	
	protected Cell getRandomNeighbour( Cell cell) {
		Cell random = null;
		
		while ( random == null ) {
			int rndId = new Random().nextInt(NEIGTHBOURS.length);
			
			int rx = (int) (cell.getX() + NEIGTHBOURS[rndId][0] );
			int ry = (int) (cell.getY() + NEIGTHBOURS[rndId][1] );
			random = getCell ( rx, ry);
		}
		return random;
	}
	
	protected void removeWall(Cell a, Cell b) {
		int[] invDelta = {a.getX() - b.getX(), a.getY() - b.getY()};
		int[] delta = {b.getX() - a.getX(), b.getY() - a.getY()};
		for (int i = 0; i < NEIGTHBOURS.length; i++) {
			if ( Arrays.equals(NEIGTHBOURS[i], invDelta) ) {
				b.removeWall(i);
			} else if( Arrays.equals(NEIGTHBOURS[i], delta) ) {
				a.removeWall(i);
			}
		}
	}
	
	public void render( Graphics2D g, int mazeSize ) {
		
		int cellSize = mazeSize/size;
		
		for (int x=0; x < size; x++ ) {
			for (int y=0; y < size; y++) {
				cells[x][y].render(g, x*cellSize, y*cellSize, cellSize);
			}	
		}
	}
}
