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

public class GraphPanel extends JPanel{
	public static final String UNIT = "km";
	public static final int TEXTFIELD_HEIGTH = 20;
	public static final int TEXTFIELD_WIDTH = 150;
	public static final int SCROLLPANE_HEIGTH = 125;
	public static final int SCROLLPANE_WIDTH = 150;
	private JTextField textFieldVertice1, textFieldVertice2, textFieldWeight;
	private JButton addNew;
	private JLabel infoLabel;
	private JList<String> listOfVertices, listOfNeighbours;
	private JScrollPane scrollPaneVertices, scrollPaneNeighbours;
	
	private JPanel panelAddNewVertice, panelShowVertices, panelShowNeighbours;
	
	
	public GraphPanel() {
		// Setting the size of the panel to the same size as the MainFrame
		this.setPreferredSize(new Dimension(MainFrame.SIZE_X, MainFrame.SIZE_Y));

		infoLabel = new JLabel("Welcome!");

		panelAddNewVertice = new JPanel(new GridBagLayout());
		panelShowVertices = new JPanel(new GridBagLayout());
		panelShowNeighbours = new JPanel(new GridBagLayout());
		
		
		// Setting the layout of the panel
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 10;
		gbc.ipady = 10;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		addAddVerticeComponents(gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		addShowVerticeComponents(gbc);
		refreshVerticesList();
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		addShowNeighbours(gbc);
		refreshNeighbourList();

		gbc.gridwidth = 3; // This makes the cells width of the layout as big as three cells width
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(infoLabel, gbc);
	}
	
	private void addShowNeighbours(GridBagConstraints panelContrains) {
		scrollPaneNeighbours = new JScrollPane();
		listOfNeighbours = new JList<>();
		scrollPaneNeighbours.setViewportView(listOfNeighbours);
		scrollPaneNeighbours.setPreferredSize(new Dimension(SCROLLPANE_WIDTH, SCROLLPANE_HEIGTH));
		
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
		scrollPaneVertices = new JScrollPane();
		listOfVertices = new JList<>();
		scrollPaneVertices.setViewportView(listOfVertices);
		scrollPaneVertices.setPreferredSize(new Dimension(SCROLLPANE_WIDTH, SCROLLPANE_HEIGTH));
		
		// This happens every time something new gets selected in the List
		listOfVertices.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				refreshNeighbourList();
			}
		});
		
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
		// Creating the button for adding new Vertices and adding the actionlistener
		addNew = new JButton("create");
		addActionListenerAddNew();
		
		// Creating the Textfields for the vertices names and weight
		textFieldVertice1 = new JTextField();
		textFieldVertice1.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGTH));
		
		textFieldVertice2 = new JTextField();		
		textFieldVertice2.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGTH));
		
		textFieldWeight = new JTextField();
		textFieldWeight.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGTH));
				
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
				Vertice neighbour = adjacente.getV1();
				if(neighbour.equals(selectedVertice)) {
					neighbour = adjacente.getV2();
				}
				
				String nameOfNeighbour = neighbour.getName();
				String weightToNeighbour = "" + adjacente.getWeight();
				listModel.addElement(nameOfNeighbour + " (" + weightToNeighbour + UNIT + ")");
			}
			panelShowNeighbours.setVisible(true);
		} else {
			panelShowNeighbours.setVisible(false);
		}
		
		listOfNeighbours.setModel(listModel);
		listOfNeighbours.setSelectedIndex(-1);
	}
	

	private void addActionListenerAddNew() {
		addNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameVertice1 = textFieldVertice1.getText();
				String nameVertice2 = textFieldVertice2.getText();
				
				if(nameVertice1.equals(nameVertice2)) {
					JOptionPane.showMessageDialog(null, "You can't say that a vertice is adjacent to itself!", "Warning", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String weight = textFieldWeight.getText();
				Program.addNewConnection(nameVertice1, nameVertice2, weight);
				infoLabel.setText("Added connection from " + nameVertice1 + " to " + nameVertice2 + " with weight " + weight + ".");
				refreshVerticesList();
			}
		});
	}
	
}
