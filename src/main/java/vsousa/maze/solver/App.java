package vsousa.maze.solver;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Map;

import javax.swing.JFrame;

import vsousa.maze.generator.Generator;
import vsousa.maze.ui.MazePanel;

public class App extends Canvas implements Runnable {
	
	private static final String NAME = "Maze A*";
	private static final int WIND_SIZE = 1000;
	private static final int MAZE_SIZE = 10;
	private static final int MARG_SIZE = 2;
	private static final long serialVersionUID = 1L;
	private final static Dimension dim = new Dimension( WIND_SIZE, WIND_SIZE);
	private final static double NS_PER_UPDATE = 1000000000D/60;
	
	private boolean running = false;
	private int width;
	private int height;
	private JFrame frame;
	private MazePanel mp;
	
	
	public App() {
		setMinimumSize( dim );
		setMaximumSize( dim );
		setPreferredSize( dim );
		
		frame = new JFrame( NAME );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setLayout( new BorderLayout() ); 
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable( false );
		frame.setLocationRelativeTo( null );
		frame.setVisible( true );
		
		
		width = getWidth();
		height = getHeight();
		
		
		
		mp = new MazePanel(WIND_SIZE, MAZE_SIZE, MARG_SIZE, Generator.generateMaze(MAZE_SIZE));
	}
	
	public synchronized void start() {
		running = true;
		new Thread( this ).start();
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	public void run() {
		long lastTimeNs = System.nanoTime();
		long lastSecondNs = System.nanoTime();
		
		long fpsCounter = 0;
		
		double delta = 0;
		
		while( running ) {
			long curr = System.nanoTime();
			delta += ( curr - lastTimeNs ) / NS_PER_UPDATE;
			lastTimeNs = curr;
			
			if ( curr - lastSecondNs > 1000000000D ) {
				lastSecondNs = curr;
				System.out.println( fpsCounter );
				fpsCounter = 0;
			}
			
			if ( delta >= 1 ) {
				delta -= 1 ;
				fpsCounter+=1;
				update();
				render();
			}
		}
	}
	
	private void update() {}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if ( bs == null ) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		
		mp.render(g);
		
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new App().start();
	}
}
