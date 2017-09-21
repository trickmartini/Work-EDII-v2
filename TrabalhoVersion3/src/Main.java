import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Vertice a = new Vertice("A");
		Vertice b = new Vertice("B");
		Vertice c = new Vertice("C");
		Vertice d = new Vertice("D");
		Vertice e = new Vertice("E");
		Vertice f = new Vertice("F");
		Vertice g = new Vertice("G");
		
		
		a.addAdjacento(b, 15);
		a.addAdjacento(c, 20);
		c.addAdjacento(e, 10);
		c.addAdjacento(b, 25);
		b.addAdjacento(e, 15);
		a.addAdjacento(d, 5);
		a.addAdjacento(g, 25);
		a.addAdjacento(b, 15);
		d.addAdjacento(g, 15);
		d.addAdjacento(f, 10);
		
		System.out.println('A' + 1);
		
	}

}
