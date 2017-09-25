package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import graph.Adjacente;
import graph.Program;
import graph.Vertice;
import gui.buttons.ButtonBFS;
import gui.buttons.ButtonDFS;
import gui.buttons.ButtonNewVertice;
import gui.labels.OutputLabel;
import gui.lists.ListOfNeighbours;
import gui.lists.ListOfVertices;
import gui.panels.PanelDistance;
import gui.panels.PanelListNeigbour;
import gui.panels.PanelListVertice;
import gui.panels.PanelNewVertice;
import gui.textfields.TextFieldHandler;

public class GraphPanel extends JPanel{
	private static final long serialVersionUID = -1187790095325404798L;
	public static final String UNIT = "h";
	
	public GraphPanel() {
		// Setting the size of the panel to the same size as the MainFrame
		this.setPreferredSize(new Dimension(MainFrame.SIZE_X, MainFrame.SIZE_Y));
		
		// Setting the layout of the panel
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 10;
		gbc.ipady = 10;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(new PanelNewVertice(), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(new PanelListVertice(), gbc);

		gbc.gridx = 3;
		gbc.gridy = 0;
		this.add(new PanelListNeigbour(), gbc);

		gbc.gridwidth = 4;// This makes the cells width of the layout as big as three cells width
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(new PanelDistance(), gbc);
		
		 
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(OutputLabel.getLabel(), gbc);
	}
	

}
