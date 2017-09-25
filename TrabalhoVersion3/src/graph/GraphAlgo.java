/**
 * 
 */
package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author DanielFortunati
 *
 */
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

    public static double startDijkstra(Vertice O, Vertice  T) {
    	ArrayList<Vertice> vertices = Program.getVertices();
    	Double[] shortestDistance = new Double[vertices.size()];
    	for (int i = 0; i < shortestDistance.length; i++) {
    		shortestDistance[i] = Double.POSITIVE_INFINITY;
		}
    	boolean[] visited = new boolean[vertices.size()];
    	
    	shortestDistance[vertices.indexOf(O)] = 0d;

    	double shortest = DIJKSRA(O, T, visited, shortestDistance, vertices);

    	return shortest;
    }
    
    private static double DIJKSRA(Vertice O, Vertice  T, boolean[] visited, Double[] shortestDistance, ArrayList<Vertice> vertices) {
    	int indexOfCurrent = vertices.indexOf(O);
    	
    	ArrayList<Adjacente> adj = O.getAdjacentes();
    	for (Adjacente adjacente : adj) {
    		Vertice neighbour = adjacente.getV1().equals(O) ? adjacente.getV2() : adjacente.getV1();
    		int indexOfNeighbour = vertices.indexOf(neighbour);
    		double weight = adjacente.getWeight();
    		double newDistance = shortestDistance[indexOfCurrent] + weight;
    		if(newDistance < shortestDistance[indexOfNeighbour]) {
    			//System.out.println(O.getName() + "_update " +  neighbour.getName() + " from " + shortestDistance[indexOfNeighbour]  + " to " + newDistance);
    			shortestDistance[indexOfNeighbour] = newDistance;   
    		}
		}
    	
    	visited[indexOfCurrent] = true;
    	
    	
    	List<Double> list = new ArrayList<Double>();
    	Collections.addAll(list, shortestDistance);
    	Double[] sortedDistances = list.stream().filter(v -> v > 0).sorted(Double::compareTo).toArray(Double[]::new);
    	double minDistance = sortedDistances[0];
    	int indexOfNext = list.indexOf(minDistance);
    	while(visited[indexOfNext]) {
    		list.set(indexOfNext, 0d);
    		sortedDistances = list.stream().filter(v -> v>0).sorted(Double::compareTo).toArray(Double[]::new);
    		if(sortedDistances.length == 0) {
    			return shortestDistance[vertices.indexOf(T)]; 
    		}
    		minDistance = sortedDistances[0];
    		indexOfNext = list.indexOf(minDistance);
    	}
    	
    	return DIJKSRA(vertices.get(indexOfNext), T, visited, shortestDistance, vertices);

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
