package graph;
import java.util.ArrayList;

public class Vertice {
	private String name = "Default";
	ArrayList<Adjacente> adjacentes = new ArrayList<Adjacente>();

	/**
	 * The Constructor to create a Vertice.
	 * @param name The name of the Vertice.
	 */
	public Vertice(String name) {
		this.name = name;
	}
	
	/**
	 * Adding a neighbour to this Vertice with the edge having a the given weight.
	 * @param vertice The Vertice that is the neighbour.
	 * @param weight The weight of the edge.
	 */
	public void addAdjacento(Vertice vertice, double weight) {
		this.adjacentes.add(new Adjacente(this, vertice, weight));
		vertice.addOnewayAdjacento(this, weight);			
		
	}
	
	/**
	 * Is used to set the neighbours adjacent to this Vertice.
	 * @param vertice The neighbours parent.
	 * @param weight The weight of the edge.
	 */
	private void addOnewayAdjacento(Vertice vertice, double weight) {
		this.adjacentes.add(new Adjacente(this, vertice, weight));
	}

	
	/**
	 * Builds a string containing a representation of all adjacent Vertices of this Vertice.
	 * @return The adjacent string.
	 */
	public String printAdjacente() {
		StringBuilder content = new StringBuilder();
		for (Adjacente adjacente : adjacentes) {
			content.append(adjacente.toString()).append(",");
		}
		return content.toString();
	}
	
	/**
	 * Gets the adjacentes of the Vertice.
	 * @return the adjacentes.
	 */
	public ArrayList<Adjacente> getAdjacentes() {
		return adjacentes;
	}
	
	/**
	 * Gets the name of the Vertice.
	 * @return the name.
	 */
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
