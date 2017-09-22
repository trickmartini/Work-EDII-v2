/**
 * 
 */
package graph;

import java.util.ArrayList;

import gui.MainFrame;

/**
 * @author DanielFortunati
 *
 */
public class Program {
	private static ArrayList<Vertice> vertices = new ArrayList<>();
	
	public Program() {
		MainFrame frame = new MainFrame();
	}
	
	public static void addNewConnection(String nameVertice1, String nameVertice2, String weight) {
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
		
		vertice1.addAdjacento(vertice2, Integer.parseInt(weight));
	}
	
	public static void removeVertice(Vertice vertice) {
		vertices.remove(vertice);
	}
	
	public static Vertice findVerticeByName(String name) {
		for (Vertice vertice : vertices) {
			if(vertice.getName().equals(name)) {
				return vertice;
			}
		}
		return null;
	}
	
	public static ArrayList<Vertice> getVertices() {
		return vertices;
	}
}
