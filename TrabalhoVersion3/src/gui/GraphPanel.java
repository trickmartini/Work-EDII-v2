package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import graph.Program;
import graph.Vertice;

public class GraphPanel extends JPanel{
	public static final int TEXTFIELD_HEIGTH = 20;
	public static final int TEXTFIELD_WIDTH = 70;
	private JTextField textFieldVertice1, textFieldVertice2, textFieldWeight;
	private JButton addNew;
	private JLabel infoLabel;
	private JList<String> listOfVertices;
	
	
	public GraphPanel() {
		// Setting the size of the panel to the same size as the MainFrame
		this.setPreferredSize(new Dimension(MainFrame.SIZE_X, MainFrame.SIZE_Y));

		// Creating the button for adding new Vertices and adding the actionlistener
		addNew = new JButton("add");
		addActionListenerAddNew();
		
		infoLabel = new JLabel("Welcome!");

		listOfVertices = new JList<>();
		addActionListenerList();
		refreshList();
		
		// Creating the Textfields for the vertices names and weight
		textFieldVertice1 = new JTextField();
		textFieldVertice2 = new JTextField();		
		textFieldWeight = new JTextField();
		textFieldVertice1.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGTH));
		textFieldVertice2.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGTH));
		textFieldWeight.setPreferredSize(new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGTH));
		
		// Setting the layout of the panel
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		/* Adding all components to the panel on the specified location
		 * gridx = 0 and gridy = 0 is the upper left corner
		 * gridx = 1 and gridy = 0 would be the object right from the object that is in the upper left corner
		 * gridx = 0 and gridy = 1 would be the object below the object that is in the upper left corner
		 */
		addAddVerticeComponents(gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(new JLabel("\n"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		this.add(new JLabel("Vertices: "), gbc);
		
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		gbc.gridy = 5;
		this.add(listOfVertices, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 6;
		this.add(new JLabel("\n"), gbc);
		

		gbc.gridwidth = 4; // This makes the cells width of the layout as big as three cells width

		gbc.gridx = 0;
		gbc.gridy = 7;
		this.add(infoLabel, gbc);
		
		gbc.gridheight = 1; 
		gbc.gridwidth = 1;
		
	}
	
	private void addAddVerticeComponents(GridBagConstraints gbc) {
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		this.add(new JLabel("Vertice 1 name: "), gbc); // Position y0 x0
		
		gbc.gridx++;
		this.add(textFieldVertice1, gbc); // Position y0 x1
		
		gbc.gridx--;
		gbc.gridy++;
		this.add(new JLabel("Vertice 2 name: "), gbc);// Position y1 x0
		
		gbc.gridx++;
		this.add(textFieldVertice2, gbc);// Position y1 x1
		
		gbc.gridx--;
		gbc.gridy++;
		this.add(new JLabel("weight: "), gbc);// Position y2 x0
		
		gbc.gridx++;
		this.add(textFieldWeight, gbc);// Position y2 x1
		
		gbc.gridheight = 3; // This makes the cells height of the layout as big as three cells height
		gbc.fill = GridBagConstraints.BOTH; // Making the Button as big as the cell
		gbc.gridx++;
		gbc.gridy -= 2;
		this.add(addNew, gbc);// Position y1 x2 (3 cells height)
	}
	

	private void refreshList() {
		listOfVertices.removeAll();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (Vertice vertice : Program.getVertices()) {
			listModel.addElement(vertice.getName());
		}
		listOfVertices.setModel(listModel);
		listOfVertices.setSelectedIndex(-1);
	}
	
	private void addActionListenerList() {
		listOfVertices.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				
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
				refreshList();
			}
		});
	}
	
}
