package vsousa.maze.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MazePanel {

	private int dims, thickness, margin;
	private List<int[]> paths;

	public MazePanel(int dims, int size, int margin, List<int[]> paths){
		this.dims = dims;
		this.thickness = (dims-margin*size)/size;
		this.margin = margin;
		this.paths = paths;
	}

	public void render(Graphics g){

		Graphics2D g2 = (Graphics2D) g;
		BasicStroke stroke = new BasicStroke(thickness);
		g2.setStroke(stroke);
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, dims, dims);

		g2.setColor(Color.BLACK);
		
		for (int[] path : paths) {
			int[] scaled = scale(path);
			g2.drawLine(scaled[0], scaled[1], scaled[2], scaled[3]);
		}
	}
	
	private int[] scale( int[] path ) {
		int[] result = new int[4];
		for (int i=0; i < 4; i++ ) {
			result[i] = path[i]*thickness + path[i]*margin;
		}
		return result;
	}
}

