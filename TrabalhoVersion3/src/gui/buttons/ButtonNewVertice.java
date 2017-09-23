package gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import graph.Program;
import gui.labels.OutputLabel;
import gui.lists.ListOfVertices;
import gui.textfields.TextFieldHandler;


public class ButtonNewVertice extends JButton {

	private static final long serialVersionUID = -7026490325610651860L;

	/**
	 * Adding the Actionlistener to the Create new Vertice button.
	 * Gets both names from the Textfields and checks if they are not the same. 
	 * If they are the same, a Error Box will popup.
	 * A new Adjacent will be created and the infoLabel and Verticle list are updated.
	 */
	public ButtonNewVertice() {
		this.setText("Create");
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameVertice1 = TextFieldHandler.getVertice1().getText();
				String nameVertice2 = TextFieldHandler.getVertice2().getText();
				
				if(nameVertice1.equals(nameVertice2)) {
					JOptionPane.showMessageDialog(null, "You can't say that a vertice is adjacent to itself!", "Warning", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String weight = TextFieldHandler.getWeight().getText();
				Program.addNewConnection(nameVertice1, nameVertice2, weight);
				OutputLabel.getLabel().setText("Added connection from " + nameVertice1 + " to " + nameVertice2 + " with weight " + weight + ".");
				ListOfVertices.refresh();
			}
		});
		}
}
