package vsousa.maze.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vsousa.maze.Board;
import vsousa.maze.Cell;

public class Generator {
	
	public static List<int[]> generateMaze(int size) {
		
		List<int[]> paths = new ArrayList<int[]>();
		
		Board board = new Board(size);
		Cell curr = board.getCell(0, 0);
		
		while( !board.Allvisited() ) {
			Cell next = board.getRandomNeighbor(curr);
			if ( !board.isVisited( next ) ) {
				int[] tmp = {curr.getXY()[0], curr.getXY()[1], next.getXY()[0], next.getXY()[1]};
				paths.add(tmp);
				board.setVisited(next);
			}
			curr = next;
		}
		
		return paths;
	}
}
