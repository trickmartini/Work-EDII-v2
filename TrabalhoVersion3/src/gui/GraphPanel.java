package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import graph.Program;
import graph.Vertice;

public class GraphPanel extends JPanel{
	public static final int TEXTFIELD_HEIGTH = 20;
	public static final int TEXTFIELD_WIDTH = 70;
	private JTextField textFieldVertice1, textFieldVertice2, textFieldWeight;
	private JButton addNew;
	private JLabel infoLabel;
	private JComboBox<String> comboBox;
	private JTable table;
	
	
	public GraphPanel() {
		// Setting the size of the panel to the same size as the MainFrame
		this.setPreferredSize(new Dimension(MainFrame.SIZE_X, MainFrame.SIZE_Y));

		// Creating the button for adding new Vertices and adding the actionlistener
		addNew = new JButton("add");
		addActionListenerAddNew();
		
		infoLabel = new JLabel("Welcome!");
		comboBox = new JComboBox<>();
		refreshComboBox();
		
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
		
		gbc.gridwidth = 3; // This makes the cells width of the layout as big as three cells width
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(infoLabel, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(new JLabel("Vertice 1 name: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		this.add(textFieldVertice1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(new JLabel("Vertice 2 name: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(textFieldVertice2, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(new JLabel("weight: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		this.add(textFieldWeight, gbc);
		
		gbc.gridheight = 3; // This makes the cells height of the layout as big as three cells height
		gbc.fill = GridBagConstraints.BOTH; // Making the Button as big as the cell
		gbc.gridx = 2;
		gbc.gridy = 1;
		this.add(addNew, gbc);
		
		
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 5;
		this.add(new JLabel("Vertices: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		this.add(comboBox, gbc);
		
	}
	
	private void refreshComboBox() {
		comboBox.removeAllItems();
		for (Vertice vertice : Program.getVertices()) {
			comboBox.addItem(vertice.getName());
		}
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
				refreshComboBox();
			}
		});
	}
	
}
