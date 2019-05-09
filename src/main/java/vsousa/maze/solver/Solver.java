package vsousa.maze.solver;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import vsousa.maze.generator.Board;
import vsousa.maze.generator.Cell;

public class Solver {
	
	private Board board;
	private Cell end;
	private List<Cell> toVisit;
	private Stack<Cell> path = new Stack<Cell>();
	
	public Solver(Cell end, Board board) {
		this.board = board;
		this.end = end;
		path.push( board.getCell(0, 0));
		toVisit = board.getCells();
		toVisit.remove(path.lastElement());
		
		path.lastElement().setColor(Color.RED);
		end.setColor(Color.RED);
	}
	
	public boolean done() {
		return path.lastElement().equals(end);
	}
	
	public void step() {
		List<Cell> paths = getPaths(path.lastElement());
		if ( paths.isEmpty()) {
			path.pop().setColor(Color.GRAY);;
			return;
		} else if (paths.size() == 1) {
			setCurrent(paths.get(0));
		}
		Cell next = null;
		int minDist = end.getX() + end.getY();
		for (Cell c : paths) {
			int dist = manataDistance(c);
			if ( dist < minDist ) {
				next = c;
			}
		}
		
		setCurrent(next);
	}
	
	private void setCurrent(Cell c) {
		path.lastElement().setColor(Color.BLUE);
		c.setColor(Color.RED);
		path.push(c);
		toVisit.remove(c);
		path.push(c);
	}
	
	private int manataDistance( Cell c ) {
		return end.getX() - c.getX() + end.getY() - c.getY();
	}
	
	private List<Cell> getPaths(Cell c) {
		List<Cell> result = new ArrayList<Cell>();
		boolean[] walls = c.getWalls();
		for (int i = 0; i < Board.NEIGTHBOURS.length; i++) {
			if ( !walls[i] ) {
				Cell tmp = board.getCell(c.getX() + Board.NEIGTHBOURS[i][0], c.getY() + Board.NEIGTHBOURS[i][1]);
				if (toVisit.contains(tmp))
					result.add(tmp);
			}
		}
		return result;
	}
}
