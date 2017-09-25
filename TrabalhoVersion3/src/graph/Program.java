/**
 * 
 */
package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import gui.MainFrame;
import gui.lists.ListOfVertices;

/**
 * @author DanielFortunati
 *
 */
public class Program {
	private static ArrayList<Vertice> vertices = new ArrayList<>();
	
	/**
	 * Starts the program by creating the Mainframe and showing its content. 
	 */
	public Program() {
		new MainFrame();
	}
	
	public static void addNewConnection(String nameVertice1, String nameVertice2, String weight) {
		
		double weightNumber = 0;
		try{ // checking if the weight from the textfield is a valid number - if not so, a error messagebox appears
			weight = weight.replaceAll(",", ".");
			weightNumber = Double.parseDouble(weight);
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Weight has to be a valid number!", "Warning", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		/* if the name was not already used by a Vertice in the list, a new Vertice is created and added to the list.*/
		Vertice vertice1 = findVerticeByName(nameVertice1);
		if(null == vertice1) {
			vertice1 = new Vertice(nameVertice1);
			vertices.add(vertice1);
		}
		
		Vertice vertice2 = findVerticeByName(nameVertice2);
		if(null == vertice2) {
			vertice2 = new Vertice(nameVertice2);			
			vertices.add(vertice2);
		}
		
		// making both vertices adjacent to each other with the given weight
		vertice1.addAdjacento(vertice2, weightNumber) ;
		ListOfVertices.refresh();
	}
	
	/**
	 * Removes a Vertice from the list.
	 * @param vertice The Vertice to remove from the list.
	 */
	public static void removeVertice(Vertice vertice) {
		vertices.remove(vertice);
	}
	
	/**
	 * Gets the Vertice in the list by searching for its name.
	 * @param name The name of the Vertice to find.
	 * @return The Vertice object with the name.
	 */
	public static Vertice findVerticeByName(String name) {
		for (Vertice vertice : vertices) {
			if(vertice.getName().equals(name)) {
				return vertice;
			}
		}
		return null;
	}
	
	
	
    /**
     * Gets the list of Vertices that are currently in the program.
     * @return The list of vertices.
     */
	public static ArrayList<Vertice> getVertices() {
		return vertices;
	}
	
}
