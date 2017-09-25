package gui.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import graph.GraphAlgo;
import gui.labels.OutputLabel;
import gui.lists.ListOfVertices;


public class ButtonBFS extends JButton {

	private static final long serialVersionUID = -3077277266991833068L;

	public ButtonBFS() {
		super("BFS");
		
		/**
		 * Adding the Actionlistener to the BFS button.
		 * Calls the function from the Class "Programm" with the selected
		 * Vertice and prints the resulting message into the infoLabel.
		 */
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JList<String> list = ListOfVertices.getList();
				String selectedValue = list.getSelectedValue();
				if(null != selectedValue) {
					String result = GraphAlgo.BFS(list.getSelectedValue());
					OutputLabel.getLabel().setText(result);					
				} else {
					JOptionPane.showMessageDialog(null, "Select the starting vertice to excecute the BFS.", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
}
