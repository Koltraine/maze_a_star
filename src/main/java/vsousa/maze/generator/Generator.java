package vsousa.maze.generator;

import java.awt.Color;
import java.util.List;

public class Generator {
	
	private Board myBoard;
	private Cell curr;
	private List<Cell> toVisit;
	
	public Generator(int size) {
		myBoard = new Board(size);
		toVisit = myBoard.getCells();
		curr = myBoard.getCell(0, 0);
		toVisit.remove(curr);
	}
	
	private void setCurr( Cell next ) {
		curr.setColor(Color.BLACK);
		toVisit.remove(next);
		Color c = toVisit.isEmpty() ? Color.BLACK : Color.GREEN;
		next.setColor(c);
		curr = next;
	}
	
	public boolean done() {
		return toVisit.isEmpty();
	}
	
	public void step() {
		Cell next = myBoard.getRandomNeighbour(curr);
		if ( toVisit.contains(next) ) {
			myBoard.removeWall(curr, next);
		}
		setCurr(next);
	}
	
	public Board getBoard() {
		return myBoard;
	}
}
