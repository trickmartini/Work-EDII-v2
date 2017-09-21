import java.util.ArrayList;

public class Vertice {
	private String nome = "Default";
	ArrayList<Adjacente> adjacentes = new ArrayList<Adjacente>();
	
	
	public Vertice() {
		
	}
	
	public Vertice(String nome ) {
		this.nome = nome;
	}
	
	public void addAdjacento(Vertice vertice, double tempo) {
		this.adjacentes.add(new Adjacente(this, vertice, tempo));
		vertice.addOnewayAdjacento(this, tempo);			
		
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
		return nome;
	}
	
	
}
