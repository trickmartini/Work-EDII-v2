package gui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.GraphPanel;
import gui.buttons.ButtonNewVertice;
import gui.textfields.TextFieldHandler;


public class PanelNewVertice extends JPanel {

	private static final long serialVersionUID = 8359391904769979946L;

	public PanelNewVertice() {
		this.setLayout(new GridBagLayout());
		createLayout();
	}
	
	private void createLayout() {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(new JLabel("Vertice 1 name: "), gbc); 
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(TextFieldHandler.getVertice1(), gbc); 
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(new JLabel("Vertice 2 name: "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(TextFieldHandler.getVertice2(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(new JLabel("weight in " + GraphPanel.UNIT + ": "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(TextFieldHandler.getWeight(), gbc);

		gbc.gridwidth = 1;
		gbc.gridheight = 5; // This makes the cells height of the layout as big as three cells height		 
		gbc.fill = GridBagConstraints.BOTH;// Making the Button as big as the cell
		gbc.gridx = 2;
		gbc.gridy = 0;
		this.add(new ButtonNewVertice(), gbc);
	}
}
