/**
 * 
 */
package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import gui.GraphPanel;

public class GraphAlgo {

	private GraphAlgo(){
		
	}
	
	public static String BFS(String start) {
		ArrayList<Vertice> vertices = Program.getVertices();
    	StringBuilder builder = new StringBuilder("BFS visited: ");
        // Mark all the vertices as not visited(By default set as false)
        boolean visited[] = new boolean[vertices.size()];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        int indexOfVertice = vertices.indexOf(Program.findVerticeByName(start));
        visited[indexOfVertice] = true;
        queue.add(indexOfVertice);
 
        while (queue.size() != 0) // as long as there are still nodes in the queue
        {
        	int index = queue.poll(); // remove the first from the queue
        	Vertice vertice = vertices.get(index);
        	builder.append(vertice.getName()).append(", ");

            // getting all neighbours of the vertice and saving them into the array adj
            Vertice[] adj = getNeighbours(vertice);

            // visit all neighbours of the vertice and mark them as visited. Add them to the queue too
            for (int i = 0; i < adj.length; i++){
                int n = vertices.indexOf(Program.findVerticeByName(adj[i].getName()));
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        
        return builder.toString().substring(0, builder.length()-2);
    }
	
    public static String DFS(String start)
    {
    	ArrayList<Vertice> vertices = Program.getVertices();
    	StringBuilder builder = new StringBuilder("DFS visited: ");
    	int startIndex = vertices.indexOf(Program.findVerticeByName(start));
    	
        // Mark all the vertices as not visited(set as false by default in java)
        boolean visited[] = new boolean[vertices.size()];
 
        // Call the recursive helper function to print DFS traversal
        DFSUtil(startIndex, visited, builder, vertices);
        
        return builder.toString().substring(0, builder.length()-2);
    }
	
    private static void DFSUtil(int startIndex, boolean visited[], StringBuilder builder, ArrayList<Vertice> vertices)
    {
    	Vertice vertice = vertices.get(startIndex);
    	
        // Mark the current node as visited
        visited[startIndex] = true;
        builder.append(vertice.getName()).append(", "); // add the current node to the result
 
        // getting all neighbours of the vertice and saving them into the array adj
        Vertice[] adj = getNeighbours(vertice);
        
        // visit all neighbours of the vertice and visit them if not yet visited (recursive!)
        for (int i = 0; i < adj.length; i++){
            int n = vertices.indexOf(Program.findVerticeByName(adj[i].getName()));
            if (!visited[n]){
            	DFSUtil(n, visited, builder, vertices);            	
            }
        }
    }

    public static String startDijkstra(Vertice O, Vertice  T) {
    	ArrayList<Vertice> vertices = Program.getVertices();
    	Double[] shortestDistance = new Double[vertices.size()];
    	for (int i = 0; i < shortestDistance.length; i++) {
    		shortestDistance[i] = Double.POSITIVE_INFINITY;
		}
    	boolean[] visited = new boolean[vertices.size()];
    	
    	shortestDistance[vertices.indexOf(O)] = 0d;
    	int[] parent = new int[vertices.size()];
    	parent[0] = -1;
    	StringBuilder content = new StringBuilder("Shortest path from '"+ O.getName() + "' to '" + T.getName() + "' is '");
    	content.append(O.getName() + " - ");
    	double shortest = DIJKSRA(O, T, visited, shortestDistance, vertices, parent, content);
    	
    	printPath(parent, vertices.indexOf(T), vertices, content);
    	
    	String result = content.toString().substring(0 , content.length() - 3);
    	result += "' and takes " + shortest + GraphPanel.UNIT + ".";
    	return result;
    }
    
    private static void printPath(int[] parent, int j, ArrayList<Vertice> vertices, StringBuilder content) {
    	// stopping the recursion when back at the parent
        if (parent[j] == -1)
            return;

        // going into the child vertice
        printPath(parent, parent[j], vertices, content);

        // appending the name of the vertice to the string
        content.append(vertices.get(j).getName()).append(" - ");
    }
 
 
    private static double DIJKSRA(Vertice O, Vertice  T, boolean[] visited, Double[] shortestDistance, ArrayList<Vertice> vertices, int[] parent, StringBuilder content) {
    	int indexOfCurrent = vertices.indexOf(O);
    	
    	// going through all neighbours of the current visited vertice O
    	ArrayList<Adjacente> adj = O.getAdjacentes();
    	for (Adjacente adjacente : adj) { 
    		Vertice neighbour = adjacente.getV1().equals(O) ? adjacente.getV2() : adjacente.getV1();
    		int indexOfNeighbour = vertices.indexOf(neighbour);
			double weight = adjacente.getWeight();
			double newDistance = shortestDistance[indexOfCurrent] + weight;
			if(newDistance < shortestDistance[indexOfNeighbour]) { // updating he vertices distance from start if smaller route found
				shortestDistance[indexOfNeighbour] = newDistance;   
				parent[indexOfNeighbour] = indexOfCurrent;
			}    			
    		
		}
    	
    	visited[indexOfCurrent] = true;
    	
    	// Casting the array "shortestDistance" to a Collection
    	List<Double> listOfShortestDistance = new ArrayList<Double>();
    	Collections.addAll(listOfShortestDistance, shortestDistance);
    
    	// Using a stream to create a sorted array (Ascending) without the 0 
    	Double[] sortedDistances = listOfShortestDistance.stream().filter(v -> v > 0).sorted(Double::compareTo).toArray(Double[]::new);
    	
    	/* Finding the next vertice that has the smallest distance from the start so far
    	 and was not visited yet. If all vertices have been visited already, return the final value */
    	double minDistance = sortedDistances[0];
    	int indexOfNext = listOfShortestDistance.indexOf(minDistance);
    	while(visited[indexOfNext]) { 
    		// Setting the next possible smallest distance to 0 because already visited and sorting the list again
    		listOfShortestDistance.set(indexOfNext, 0d);
    		sortedDistances = listOfShortestDistance.stream().filter(v -> v>0).sorted(Double::compareTo).toArray(Double[]::new);
    		if(sortedDistances.length == 0) {
    			return shortestDistance[vertices.indexOf(T)]; 
    		}
    		minDistance = sortedDistances[0];
    		indexOfNext = listOfShortestDistance.indexOf(minDistance);
    	}
    	
    	// visiting a not yet visited vertice with the current lowest distance from start
    	return DIJKSRA(vertices.get(indexOfNext), T, visited, shortestDistance, vertices, parent, content);
    }
    

    private static Vertice[] getNeighbours(Vertice vertice) {
    	ArrayList<Adjacente> neighbours  = vertice.getAdjacentes();
        Vertice[] adj = new Vertice[neighbours.size()];
        int counter = 0;
        for (Adjacente adjacente : neighbours) {
			adj[counter++] = adjacente.getV1().equals(vertice) ? adjacente.getV2() : adjacente.getV1();
		}
        
        return adj;
    }
}
