package vsousa.maze.generator;

import java.awt.Color;
import java.awt.Graphics2D;

public class Cell {
	
	private int x, y;
	private boolean[] walls = {true, true, true, true}; // U, D, L, R
	private Color color = Color.RED;
	
	protected Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	protected void removeWall( int index ) {
		walls[index] = false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public void setColor( Color color) {
		this.color = color;
	}
	
	public boolean[] getWalls() {
		return walls;
	}
	
	protected void render(Graphics2D g, int x0, int y0, int cellSize) {
		int wallWidth = cellSize/10;
		g.setColor(color);
		g.fillRect(x0, y0, cellSize, cellSize);
		g.setColor(Color.WHITE);
		if (walls[0]) {
			g.fillRect(x0, y0, cellSize, wallWidth);
		}
		if (walls[1]) {
			g.fillRect(x0, y0+cellSize-wallWidth, cellSize, wallWidth);
		}
		if (walls[2]) {
			g.fillRect(x0, y0, wallWidth, cellSize);
		}
		if (walls[3]) {
			g.fillRect(x0+cellSize-wallWidth, y0, wallWidth, cellSize);
		}
	}
}
