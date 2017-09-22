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
	
	public void addAdjacento(Vertice vertice, double tempo) {
		this.adjacentes.add(new Adjacente(this, vertice, tempo));
		vertice.addOnewayAdjacento(this, tempo);			
		
	}
	
	public String getName() {
		return name;
	}
	
	private void addOnewayAdjacento(Vertice vertice, double tempo) {
		this.adjacentes.add(new Adjacente(this, vertice, tempo));
	}

	public String printAdjacente() {
		StringBuilder content = new StringBuilder();
		for (Adjacente adjacente : adjacentes) {
			content.append(adjacente.toString()).append(",");
		}
		return content.toString();
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	
}
