
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class Graph implements GraphInterface<Town, Road> 
{

	Set<Town> town = new HashSet<Town>();
	Set<Road> road = new HashSet<Road>();
	Map<Town, Town> map = new HashMap<Town, Town>();;
	Map<Town, Integer> weight = new HashMap<Town, Integer>();
	
	/**
	 * Returns an edge connecting source vertex to target
	 * Returns null if any of the specified vertices is null
	 *
	 * @param sourceVertex source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 *
	 * @return an edge connecting source vertex to target vertex.
	 */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		for(Road roads: road) {
			if (roads.contains(sourceVertex) && roads.contains(destinationVertex))
				return roads;
		}
		return null;
	}
	
	
	/**
	 * Creates a new edge in this graph
	 *
	 * @param sourceVertex source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 * @param weight weight of the edge
	 * @param description description for edge
	 *
	 * @return The newly created edge if added to the graph, otherwise null.
	 *
	 * @throws IllegalArgumentException if source or target vertices are not
	 * found in the graph.
	 * @throws NullPointerException if any of the specified vertices is null.
	 */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (sourceVertex == null || destinationVertex == null)
			throw new NullPointerException();

		if (!town.contains(sourceVertex) || !town.contains(destinationVertex))
			throw new IllegalArgumentException();

		Road edge = new Road(sourceVertex, destinationVertex, weight, description);
		road.add(edge);

		return edge;
	}
	
	
	/**
	 * Adds the specified vertex to this graph if not already present
	 *
	 * @param v vertex to be added to this graph.
	 *
	 * @return true if this graph did not already contain the specified
	 * vertex.
	 *
	 * @throws NullPointerException if the specified vertex is null.
	 */
	@Override
	public boolean addVertex(Town v) {
		if (v == null)
			throw new NullPointerException();

		if (containsVertex(v))
			return false;

		return town.add(v);
	}
	
	
	/**
	 * Returns true if the graph contains an edge going
	 * from the source vertex to the target vertex.
	 *
	 * @param sourceVertex source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 *
	 * @return true if this graph contains the specified edge.
	 */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		for(Road roads: road) {
			if (roads.contains(sourceVertex) && roads.contains(destinationVertex))
				return true;
		}
		return false;
	}
	
	
	/**
	 * Returns true if this graph contains specific vertex.
	 * Returns false if null
	 *
	 * @param v vertex whose presence in this graph is to be tested.
	 * @return true if this graph contains specific vertex.
	 */
	@Override
	public boolean containsVertex(Town v) {
		boolean containsVertex = false;

		for(Town towns: town) {
			if (towns.equals(v))
				containsVertex = true;
		}
		return containsVertex;
	}
	
	
	/**
	 * Returns a set of the edges contained in this graph
	 * 
	 * @return a set of the edges contained in this graph.
	 */
	@Override
	public Set<Road> edgeSet() {
		return road;
	}
	
	
	/**
	 * Returns a set of all edges touching the specified vertex
	 *
	 * @param vertex the vertex for which a set of touching edges is to be
	 * returned.
	 *
	 * @return a set of all edges touching the specified vertex.
	 *
	 * @throws IllegalArgumentException if vertex is not found in the graph.
	 * @throws NullPointerException if vertex is null.
	 */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> edgesOf = new HashSet<Road>();
		
		for (Road roads: road)
			if (roads.contains(vertex))
				edgesOf.add(roads);
		
		return edgesOf;
	}
	
	
	/**
	 * Removes an edge going from source vertex to target vertex, if such
	 * vertices and such edge exist in this graph. 
	 *
	 * @param sourceVertex source vertex of the edge.
	 * @param destinationVertex target vertex of the edge.
	 * @param weight weight of the edge
	 * @param description description of the edge
	 *
	 * @return The removed edge, or null if no edge removed.
	 */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (sourceVertex == null || destinationVertex == null)
			return null;
		
		if (!town.contains(sourceVertex) || !town.contains(destinationVertex))
			throw new IllegalArgumentException();
		
		for (Road roads: road)
			if (roads.contains(sourceVertex) && roads.contains(destinationVertex) && roads.getWeight() == weight && roads.getName().equals(description)) {
				road.remove(roads);
				return roads;
			}
		return null;
	}
	
	
	/**
	 * Removes the specified vertex from this graph including all its touching edges if present
	 * Return false in null
	 *
	 * @param v vertex to be removed from this graph, if present.
	 *
	 * @return true if the graph contained the specified vertex;
	 * false otherwise.
	 */
	@Override
	public boolean removeVertex(Town v) {
		return town.remove(v);
	}
	
	
	/**
	 * Returns a set of the vertices contained in this graph
	 *
	 * @return a set view of the vertices contained in this graph.
	 */
	@Override
	public Set<Town> vertexSet() {
		return town;
	}
	
	
	/**
	 * Find the shortest path from the sourceVertex to the destinationVertex
	 
	 * @param sourceVertex starting vertex
	 * @param destinationVertex ending vertex
	 * @return An arraylist of Strings that describe the path from sourceVertex
	 */  
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		ArrayList<String> shortest = new ArrayList<String>();

		boolean correct = false;

		for(Road roads: road) {
			if(roads.contains(destinationVertex))
				correct = true;
		}
		if(!correct) {
			return shortest;
		}
		dijkstraShortestPath(sourceVertex);

		Town temp = destinationVertex;

		while(!temp.equals(sourceVertex)) {
			for(Road roads: road) {
				if(roads.contains(temp) && roads.contains(map.get(temp)))
					shortest.add(0, map.get(temp).getName() + " via " + roads.getName() + " to " + temp.getName() + " " + roads.getWeight() + " mi");
			}
			temp = map.get(temp);
		}
		return shortest;
	}
	
	
	/**
	 * Dijkstra's Shortest Path Method
	 * 
	 * @param sourceVertex the vertex to find shortest path from
	 * 
	 */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		HashSet<Town> temp = new HashSet<>();
		
		for(Town towns: town) {
			temp.add(towns);
		}

		for(Town towns: town) {
			weight.put(towns, Integer.MAX_VALUE);
		}
		weight.put(sourceVertex, 0);

		while(!temp.isEmpty()) {
			for(Road roads: road) {
				if(roads.contains(sourceVertex)) {
					if(!roads.getDestination().equals(sourceVertex) && temp.contains(roads.getDestination())) {
						if(weight.get(sourceVertex) + roads.getWeight() < weight.get(roads.getDestination())) {
							map.put(roads.getDestination(), sourceVertex);
							weight.put(roads.getDestination(), roads.getWeight() + weight.get(sourceVertex));
						}
					}
					else if(!roads.getSource().equals(sourceVertex) && temp.contains(roads.getSource()))
						if(weight.get(sourceVertex) + roads.getWeight() < weight.get(roads.getSource())) {
							map.put(roads.getSource(), sourceVertex);
							weight.put(roads.getSource(), roads.getWeight() + weight.get(sourceVertex));
						}
				}
			}
			temp.remove(sourceVertex);

			int min = 100;
			for(Town towns: weight.keySet()) {	
				if(min >  weight.get(towns) && temp.contains(towns)) {
					min = weight.get(towns);
					sourceVertex = towns;
				}
			}
		}
	}
}


