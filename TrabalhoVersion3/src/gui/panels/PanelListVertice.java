package gui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.buttons.ButtonBFS;
import gui.buttons.ButtonDFS;
import gui.buttons.ButtonDijkstra;
import gui.lists.ListOfVertices;


public class PanelListVertice extends JPanel {

	private static final long serialVersionUID = 6702854517913321689L;

	public PanelListVertice() {
		this.setLayout(new GridBagLayout());
		createLayout();
	}
	
	private void createLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(new JLabel("Vertices: "), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(new ListOfVertices(), gbc);
		
		gbc.fill = GridBagConstraints.BOTH;// Making the Button as big as the cell
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(new ButtonBFS(), gbc);
	
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(new ButtonDFS(), gbc);

	}
}
