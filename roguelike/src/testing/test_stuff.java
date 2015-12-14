package testing;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class test_stuff extends JFrame{

	private Image dbImage;
	private Graphics dbg;
	
	Image floor;
	
	public test_stuff() {
		
		ImageIcon i = new ImageIcon("E:/workspace/roguelike/bin/images/floor.png");
		floor = i.getImage();
		
		setTitle("Game");
		setSize(1024, 1050);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void paintComponent(Graphics g) {
		for (int row = 0; row < 64; row ++) {
			for (int col = 0; col < 64; col ++) {
				g.drawImage(floor, (3 + (row*16)), (26 + (col*16)), 16, 16, this);
			}
		}
		
		//g.drawImage(floor, 3, 26, 16, 16, this);
		//g.drawImage(floor, 3, 42, 16, 16, this);
		//g.drawString("Hello World", 75, 75);
		repaint();
		
	}
	
	public static void main(String[] args) {
		new test_stuff();
	}
}

