package vsousa.maze.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;

import vsousa.maze.generator.Board;

public class MazePanel {

	private int dims;
	private Board board;

	public MazePanel(int dims, Board board){
		this.dims = dims;
		this.board = board;
	}

	public void render(Graphics g){

		Graphics2D g2 = (Graphics2D) g;
		
		board.render( g2, dims );
		
	}
}

