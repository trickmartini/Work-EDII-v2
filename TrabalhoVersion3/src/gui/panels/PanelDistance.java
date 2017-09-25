package gui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.buttons.ButtonDijkstra;
import gui.lists.ListOfNeighbours;
import gui.textfields.TextFieldHandler;


public class PanelDistance extends JPanel {

	private static final long serialVersionUID = 6746320250231650744L;

	public PanelDistance() {
		this.setLayout(new GridBagLayout());
		createLayout();
	}
	
	private void createLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(new JLabel("Go from "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(TextFieldHandler.getDistance1(), gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		this.add(new JLabel(" to "), gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		this.add(TextFieldHandler.getDistance2(), gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 0;
		this.add(new ButtonDijkstra(), gbc);
	}
}
