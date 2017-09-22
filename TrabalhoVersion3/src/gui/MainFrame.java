package gui;

import javax.swing.JFrame;

public class MainFrame {
	public final static int SIZE_X = 500;
	public final static int SIZE_Y = 200;
	private JFrame frame;
	
	public MainFrame() {
		frame = new JFrame();
		
		frame.setSize(SIZE_X, SIZE_Y);
		
		// Adding the panel containing all the content
		frame.add(new GraphPanel());
		
		// Setting the location of the frame to the center of the screen
		frame.setLocationRelativeTo(null);
		
		// Showing the frame
		frame.setVisible(true);
	}
	
	
}
