package gui.lists;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import graph.Program;
import graph.Vertice;

public class ListOfVertices extends JScrollPane {

	private static final long serialVersionUID = 8974234392985394197L;
	private static final int SCROLLPANE_HEIGTH = 125;
	private static final int SCROLLPANE_WIDTH = 150;
	
	private static JList<String> list;

	public ListOfVertices() {
		list = new JList<>();
		this.setViewportView(list);
		this.setPreferredSize(new Dimension(SCROLLPANE_WIDTH, SCROLLPANE_HEIGTH));
		// This happens every time something new gets selected in the List
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ListOfNeighbours.refresh();
			}
		});
	}
	
	/**
	 * First removing all items from the list of Vertices.
	 * Than adding all Vertices that were created to the list.
	 */
	public static void refresh() {
		list.removeAll();
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (Vertice vertice : Program.getVertices()) {
			listModel.addElement(vertice.getName());
		}
		list.setModel(listModel);
		list.setSelectedIndex(-1);
	}
	
	public static JList<String> getList() {
		return list;
	}
}
