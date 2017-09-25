package gui;

import javax.swing.JFrame;

public class MainFrame {
	public final static int SIZE_X = 700;
	public final static int SIZE_Y = 400;
	private JFrame frame;
	
	/**
	 * Creates the Mainframe.
	 * Setting the size of the Frame and the location to the CENTER of the screen.
	 * Adds the GraphPanel to the frame (this contains all content)
	 */
	public MainFrame() {
		frame = new JFrame();
		frame.setSize(SIZE_X, SIZE_Y);
		frame.add(new GraphPanel());
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
}
