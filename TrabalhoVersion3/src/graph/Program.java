/**
 * 
 */
package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import gui.MainFrame;

/**
 * @author DanielFortunati
 *
 */
public class Program {
	private static ArrayList<Vertice> vertices = new ArrayList<>();
	
	/**
	 * Starts the program by creating the Mainframe and showing its content. 
	 */
	public Program() {
		new MainFrame();
	}
	
	public static void addNewConnection(String nameVertice1, String nameVertice2, String weight) {
		
		double weightNumber = 0;
		try{ // checking if the weight from the textfield is a valid number - if not so, a error messagebox appears
			weight = weight.replaceAll(",", ".");
			weightNumber = Double.parseDouble(weight);
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Weight has to be a valid number!", "Warning", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		/* if the name was not already used by a Vertice in the list, a new Vertice is created and added to the list.*/
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
		
		// making both vertices adjacent to each other with the given weight
		vertice1.addAdjacento(vertice2, weightNumber) ;
	}
	
	/**
	 * Removes a Vertice from the list.
	 * @param vertice The Vertice to remove from the list.
	 */
	public static void removeVertice(Vertice vertice) {
		vertices.remove(vertice);
	}
	
	/**
	 * Gets the Vertice in the list by searching for its name.
	 * @param name The name of the Vertice to find.
	 * @return The Vertice object with the name.
	 */
	public static Vertice findVerticeByName(String name) {
		for (Vertice vertice : vertices) {
			if(vertice.getName().equals(name)) {
				return vertice;
			}
		}
		return null;
	}
	
	public static String BFS(String start) {
    	String result = "BFS Visited: ";
        // Mark all the vertices as not visited(By default set as false)
        boolean visited[] = new boolean[vertices.size()];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        int indexOfVertice = vertices.indexOf(findVerticeByName(start));
        visited[indexOfVertice] = true;
        queue.add(indexOfVertice);
 
        while (queue.size() != 0) // as long as there are still nodes in the queue
        {
        	int index = queue.poll(); // remove the first from the queue
        	Vertice vertice = vertices.get(index);
            result += vertice.getName() + " ";

            // getting all neighbours of the vertice and saving them into the array adj
            ArrayList<Adjacente> neighbours  = vertice.getAdjacentes();
            Vertice[] adj = new Vertice[neighbours.size()];
            int counter = 0;
            for (Adjacente adjacente : neighbours) {
				adj[counter++] = adjacente.getV1().equals(vertice) ? adjacente.getV2() : adjacente.getV1();
			}

            // visit all neighbours of the vertice and mark them as visited. Add them to the queue too
            for (int i = 0; i < adj.length; i++){
                int n = vertices.indexOf(findVerticeByName(adj[i].getName()));
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        
        return result;
    }
	
	
    public static String DFS(String start)
    {
    	StringBuilder builder = new StringBuilder("DFS visited: ");
    	int startIndex = vertices.indexOf(findVerticeByName(start));
    	
        // Mark all the vertices as not visited(set as false by default in java)
        boolean visited[] = new boolean[vertices.size()];
 
        // Call the recursive helper function to print DFS traversal
        DFSUtil(startIndex, visited, builder);
        
        return builder.toString();
    }
	
    private static void DFSUtil(int startIndex, boolean visited[], StringBuilder builder)
    {
    	Vertice vertice = vertices.get(startIndex);
    	
        // Mark the current node as visited
        visited[startIndex] = true;
        builder.append(vertice.getName()).append(" "); // add the current node to the result
 
        // getting all neighbours of the vertice and saving them into the array adj
        ArrayList<Adjacente> neighbours  = vertice.getAdjacentes();
        Vertice[] adj = new Vertice[neighbours.size()];
        int counter = 0;
        for (Adjacente adjacente : neighbours) {
			adj[counter++] = adjacente.getV1().equals(vertice) ? adjacente.getV2() : adjacente.getV1();
		}
        
        // visit all neighbours of the vertice and visit them if not yet visited (recursive!)
        for (int i = 0; i < adj.length; i++){
            int n = vertices.indexOf(findVerticeByName(adj[i].getName()));
            if (!visited[n]){
            	DFSUtil(n, visited, builder);            	
            }
        }
    }
	
    /**
     * Gets the list of Vertices that are currently in the program.
     * @return The list of vertices.
     */
	public static ArrayList<Vertice> getVertices() {
		return vertices;
	}
	
}
