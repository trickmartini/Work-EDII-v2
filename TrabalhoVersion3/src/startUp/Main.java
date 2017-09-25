package startUp;
import graph.Program;

public class Main {

	public static void main(String[] args) {
		Program p = new Program();
		Program.addNewConnection("a", "b", "3");
		Program.addNewConnection("a", "c", "2");
		Program.addNewConnection("d", "b", "4");
		Program.addNewConnection("c", "e", "10");
		Program.addNewConnection("f", "b", "2");
		Program.addNewConnection("c", "f", "1");
		Program.addNewConnection("f", "d", "1");
		Program.addNewConnection("f", "e", "1");
		Program.addNewConnection("d", "e", "2");
		
	}

}
