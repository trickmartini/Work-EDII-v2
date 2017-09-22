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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import graph.Adjacente;
import graph.Program;
import graph.Vertice;

public class GraphPanel extends JPanel{
	public static final String UNIT = "km";
	public static final int TEXTFIELD_HEIGTH = 20;
	public static final int TEXTFIELD_WIDTH = 150;
	private JTextField textFieldVertice1, textFieldVertice2, textFieldWeight;
	private JButton addNew;
	private JLabel infoLabel;
	private JList<String> listOfVertices, listOfNeighbours;
	private JScrollPane scrollPaneVertices, scrollPaneNeighbours;
	
	private JPanel panelAddNewVertice, panelShowVertices, panelShowNeighbours;
	
	
	public GraphPanel() {
		// Setting the size of the panel to the same size as the MainFrame
		this.setPreferredSize(new Dimension(MainFrame.SIZE_X, MainFrame.SIZE_Y));

		// Creating the button for adding new Vertices and adding the actionlistener
		addNew = new JButton("add");
		addActionListenerAddNew();
		
		infoLabel = new JLabel("Welcome!");

		panelAddNewVertice = new JPanel(new GridBagLayout());
		panelShowVertices = new JPanel(new GridBagLayout());
		panelShowNeighbours = new JPanel(new GridBagLayout());
		
		scrollPaneVertices = new JScrollPane();
		listOfVertices = new JList<>();
		scrollPaneVertices.setViewportView(listOfVertices);
		scrollPaneVertices.setPreferredSize(new Dimension(150, 150));
		
		scrollPaneNeighbours = new JScrollPane();
		listOfNeighbours = new JList<>();
		scrollPaneNeighbours.setViewportView(listOfNeighbours);
		scrollPaneNeighbours.setPreferredSize(new Dimension(150, 150));
		
		
		addActionListenerList();
		refreshVerticesList();
		refreshNeighbourList();
		
		// Creating the Textfields for the vertices names and weight
		textFieldVertice1 = new JTextField();
		textFieldVertice1.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGTH));
		
		textFieldVertice2 = new JTextField();		
		textFieldVertice2.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGTH));
		
		textFieldWeight = new JTextField();
		textFieldWeight.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGTH));
		
		// Setting the layout of the panel
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 10;
		
		// Adding all components to the panel on the specified location
		gbc.gridx = 0;
		gbc.gridy = 0;
		addAddVerticeComponents(gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		addShowVerticeComponents(gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		addShowNeighbours(gbc);

		gbc.gridwidth = 3; // This makes the cells width of the layout as big as three cells width
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(infoLabel, gbc);
	}
	
	private void addShowNeighbours(GridBagConstraints panelContrains) {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelShowNeighbours.add(new JLabel("Adjacent to: "), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelShowNeighbours.add(scrollPaneNeighbours, gbc);
		
		this.add(panelShowNeighbours, panelContrains);
		panelShowNeighbours.setVisible(false);
	}
	
	private void addShowVerticeComponents(GridBagConstraints panelContrains) {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelShowVertices.add(new JLabel("Vertices: "), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelShowVertices.add(scrollPaneVertices, gbc);
	
		this.add(panelShowVertices, panelContrains);
	}
	
	private void addAddVerticeComponents(GridBagConstraints panelContrains) {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelAddNewVertice.add(new JLabel("Vertice 1 name: "), gbc); 
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelAddNewVertice.add(textFieldVertice1, gbc); 
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelAddNewVertice.add(new JLabel("Vertice 2 name: "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelAddNewVertice.add(textFieldVertice2, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelAddNewVertice.add(new JLabel("weight: "), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelAddNewVertice.add(textFieldWeight, gbc);
		
		gbc.gridheight = 4; // This makes the cells height of the layout as big as three cells height
		gbc.fill = GridBagConstraints.BOTH; // Making the Button as big as the cell
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelAddNewVertice.add(addNew, gbc);
		
		this.add(panelAddNewVertice, panelContrains);
	}
	

	private void refreshVerticesList() {
		listOfVertices.removeAll();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (Vertice vertice : Program.getVertices()) {
			listModel.addElement(vertice.getName());
		}
		listOfVertices.setModel(listModel);
		listOfVertices.setSelectedIndex(-1);
	}
	
	private void refreshNeighbourList() {
		listOfNeighbours.removeAll();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		String verticeName = listOfVertices.getSelectedValue();
		Vertice selectedVertice = Program.findVerticeByName(verticeName);
		if(null != selectedVertice) {
			ArrayList<Adjacente> neighbours = selectedVertice.getAdjacentes();
			for (Adjacente adjacente : neighbours) {
				Vertice neighbour = adjacente.getNeighbourOf(selectedVertice);
				String nameOfNeighbour = neighbour.getName();
				String weightToNeighbour = "" + adjacente.getTempo();
				listModel.addElement(nameOfNeighbour + " (" + weightToNeighbour + UNIT + ")");
			}
			panelShowNeighbours.setVisible(true);
		} else {
			panelShowNeighbours.setVisible(false);
		}
		
		listOfNeighbours.setModel(listModel);
		listOfNeighbours.setSelectedIndex(-1);
	}
	
	private void addActionListenerList() {
		listOfVertices.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				refreshNeighbourList();
			}
		});
	}

	
	private void addActionListenerAddNew() {
		addNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameVertice1 = textFieldVertice1.getText();
				String nameVertice2 = textFieldVertice2.getText();
				String weight = textFieldWeight.getText();
				Program.addNewConnection(nameVertice1, nameVertice2, weight);
				infoLabel.setText("Added connection from " + nameVertice1 + " to " + nameVertice2 + " with weight " + weight + ".");
				refreshVerticesList();
			}
		});
	}
	
}
