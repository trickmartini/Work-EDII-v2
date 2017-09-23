package gui.lists;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import graph.Adjacente;
import graph.Program;
import graph.Vertice;
import gui.GraphPanel;


public class ListOfNeighbours extends JScrollPane {

	private static final long serialVersionUID = -407586586961122442L;
	private static final int SCROLLPANE_HEIGTH = 175;
	private static final int SCROLLPANE_WIDTH = 150;
	
	private static JList<String> list;

	public ListOfNeighbours() {
		list = new JList<>();
		this.setViewportView(list);
		this.setPreferredSize(new Dimension(SCROLLPANE_WIDTH, SCROLLPANE_HEIGTH));
	}
	
	/**
	 * First removing all items from the list of Neighbours.
	 * Than adding all neighbours of the currently selected Vertice.
	 */
	public static void refresh() {
		list.removeAll();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		String verticeName = ListOfVertices.getList().getSelectedValue();
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
				listModel.addElement(nameOfNeighbour + " (" + weightToNeighbour + GraphPanel.UNIT + ")");
			}
		} 
		
		list.setModel(listModel);
		list.setSelectedIndex(-1);
	}
	
	public static JList<String> getList() {
		return list;
	}
	
}
