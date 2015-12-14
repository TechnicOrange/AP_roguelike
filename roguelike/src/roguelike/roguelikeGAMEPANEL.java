package roguelike;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class roguelikeGAMEPANEL extends JPanel implements Runnable {
	//double buffering variables
	private Image dbImage;
	private Graphics dbg;
	
	//JPanel variables
	static final int GWIDTH = 512, GHEIGHT = 512;
	static final Dimension gameDim = new Dimension(GWIDTH, GHEIGHT);
	
	//Game variables
	private Thread game;
	private volatile boolean running = false;
	
	//Game Objects
	roguelikeDUNGEON dungeon;
	
	public roguelikeGAMEPANEL(){
		dungeon = new roguelikeDUNGEON();
		
		setPreferredSize(gameDim);
		setBackground(Color.WHITE);
		setFocusable(true);
		requestFocus();
		
		//handle all key inputs from user
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
		});
	}
	
	public void run() {
		while (running) {
			
			gameUpdate();
			gameRender();
			paintScreen();
			
		}
	}
	
	private void gameUpdate() {
		if(running && game != null) {
			//update game state
		}
	}
	
	private void gameRender() {
		if(dbImage == null) {//create buffer
			dbImage = createImage(GWIDTH, GHEIGHT);
			if (dbImage == null){
				System.err.println("dbImage is still null!");
				return;
			} else {
				dbg = dbImage.getGraphics();
			}
		}
		//clear screen
		dbg.setColor(Color.WHITE);
		dbg.fillRect(0, 0, GWIDTH, GHEIGHT);
		//draw game stuff
		draw(dbg);
	}
	
	/* Draw all game content in this method */
	public void draw(Graphics g) {
		dungeon.draw(g);
	}
	
	private void paintScreen() {
		Graphics g;
		try{
			g = this.getGraphics();
			if (dbImage != null && g != null) {
				g.drawImage(dbImage,  0,  0,  null);
			}
			g.dispose();
		}catch(Exception e) {
			System.err.println(e);
		}
	}
	
	public void addNotify() {
		super.addNotify();
		startGame();
	}
	
	public void startGame() {
		if(game == null || !running) {
			game = new Thread(this);
			game.start();
			running = true;
		}
	}
	
	public void stopGame() {
		if (running) {
			running = false;
		}
	}
	
	private void log(String s) {
		System.out.println(s);
	}
}
