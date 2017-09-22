package graph;
public class Adjacente {
	private double weight;
	private Vertice v1, v2;
	
	/**
	 * The constructor to make two Vertices adjacent to each other.
	 * @param v1 The first Vertice.
	 * @param v2 The second Vertice.
	 * @param weight The weight of the edge.
	 */
	public Adjacente(Vertice v1, Vertice v2, double weight) {
		this.weight = weight;
		this.v1 = v1;
		this.v2 = v2;
	}
	
	/**
	 * gets the first Vertice;
	 * @return The first Vertice.
	 */
	public Vertice getV1() {
		return v1;
	}
	
	/**
	 * gets the second Vertice;
	 * @return The second Vertice.
	 */
	public Vertice getV2() {
		return v2;
	}

	/**
	 * gets the weight of the edge.
	 * @return The weight.
	 */
	public double getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return  "(" + v1.toString() + "," + v2.toString() + ":" +weight + ")";
	}
}
