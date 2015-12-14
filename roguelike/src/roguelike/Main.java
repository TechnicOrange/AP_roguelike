package roguelike;

import javax.swing.JFrame;

public class Main extends JFrame {

	roguelikeGAMEPANEL gp;	
	
	public Main() {
		gp = new roguelikeGAMEPANEL();
		//Initializes JPanel info//
		setSize(520, 542);//sets size of the WINDOW, not drawing area
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//terminates program when the JPanel is closed
		setVisible(true);//makes the window visible
		setResizable(false);//prevents user from manually resizing window using mouse
		add(gp);
	}
	
	public static void main(String[] args) {
		Main m = new Main();//

	}

}
