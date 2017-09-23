package gui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.lists.ListOfNeighbours;

public class PanelListNeigbour extends JPanel {

	private static final long serialVersionUID = -4777236129888286310L;

	public PanelListNeigbour() {
		this.setLayout(new GridBagLayout());
		createLayout();
	}
	
	private void createLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(new JLabel("Adjacent to: "), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(new ListOfNeighbours(), gbc);
	}
}
