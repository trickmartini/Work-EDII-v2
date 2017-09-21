
public class Adjacente {
	private double tempo;
	private Vertice v1, v2;
	
	public Adjacente() {
		
	}
	
	public Adjacente(Vertice v1, Vertice v2, double tempo) {
		this.tempo = tempo;
		this.v1 = v1;
		this.v2 = v2;
	}
	
	@Override
	public String toString() {
		return  "(" + v1.toString() + "," + v2.toString() + ":" +tempo + ")";
	}
}
