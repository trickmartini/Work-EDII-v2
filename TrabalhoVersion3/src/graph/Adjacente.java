package graph;
public class Adjacente {
	private double weight;
	private Vertice v1, v2;
	
	public Adjacente() {
		
	}
	
	public Adjacente(Vertice v1, Vertice v2, double weight) {
		this.weight = weight;
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public Vertice getV1() {
		return v1;
	}
	
	public Vertice getV2() {
		return v2;
	}

	public double getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return  "(" + v1.toString() + "," + v2.toString() + ":" +weight + ")";
	}
}
