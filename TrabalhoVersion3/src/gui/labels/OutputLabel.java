/**
 * 
 */
package gui.labels;

import javax.swing.JLabel;


public class OutputLabel extends JLabel {
	private static final long serialVersionUID = 4273232428167665421L;
	
	private static OutputLabel label;
	
	/**
	 * static initialize the label with the private constructor.
	 */
	static {
		label = new OutputLabel("Welcome");
	}
	
	/**
	 * Private Constructor so that no Object of this can be created.
	 * @param text The text of the Label.
	 */
	private OutputLabel(String text) {
		super(text);
	}

	/**
	 * Getting the reference to the label. (Singleton pattern)
	 * @return The reference to the label.
	 */
	public static OutputLabel getLabel() {
		return label;
	}
	
}
