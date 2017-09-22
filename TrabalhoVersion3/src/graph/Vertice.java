package graph;
import java.util.ArrayList;

public class Vertice {
	private String name = "Default";
	ArrayList<Adjacente> adjacentes = new ArrayList<Adjacente>();
	
	public Vertice() {
		
	}
	
	public Vertice(String name) {
		this.name = name;
	}
	
	public void addAdjacento(Vertice vertice, double weight) {
		this.adjacentes.add(new Adjacente(this, vertice, weight));
		vertice.addOnewayAdjacento(this, weight);			
		
	}
	
	private void addOnewayAdjacento(Vertice vertice, double weight) {
		this.adjacentes.add(new Adjacente(this, vertice, weight));
	}

	public String printAdjacente() {
		StringBuilder content = new StringBuilder();
		for (Adjacente adjacente : adjacentes) {
			content.append(adjacente.toString()).append(",");
		}
		return content.toString();
	}
	
	public ArrayList<Adjacente> getAdjacentes() {
		return adjacentes;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
