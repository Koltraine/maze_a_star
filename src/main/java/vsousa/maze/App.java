package vsousa.maze;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import vsousa.maze.generator.Board;
import vsousa.maze.generator.Generator;
import vsousa.maze.solver.Solver;
import vsousa.maze.ui.MazePanel;

public class App extends Canvas implements Runnable {
	
	private static final String NAME = "Maze A*";
	private static final int WIND_SIZE = 1000;
	private static final int MAZE_SIZE = 100;
	private static final long serialVersionUID = 1L;
	private static final Dimension dim = new Dimension( WIND_SIZE, WIND_SIZE);
	private static final double NS_PER_UPDATE = 1000000000D/60;
	
	private boolean running = false;
	private JFrame frame;
	private MazePanel mp;
	private Generator gen = new Generator(MAZE_SIZE);
	private Solver solver;
	
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
		
		mp = new MazePanel(WIND_SIZE, gen.getBoard());
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
	
	private void update() {
		if(!gen.done()) {
			gen.step();
		} else {
			if (solver == null) {
				solver = new Solver(gen.getBoard().getCell(MAZE_SIZE-1, MAZE_SIZE-1), gen.getBoard());
			}
			if(!solver.done()) {
				solver.step();
			}
		}
	}
	
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
