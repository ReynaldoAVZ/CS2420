package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Contains several methods for solving problems on generic, directed, unweighted, sparse graphs.
 * <p></p>
 * NOTE: Note that each of the methods below takes a list of sources and a list of destinations as input and must
 * construct a graph from this information.  Type is the type of the data stored at each vertex.  Let srci be the
 * element at index i in the sources list. Let dsti be the element at index i in the destinations list.  This indicates
 * that the graph has a directed edge from a vertex with data srci to a vertex with data dsti.  Thus, each pair of
 * elements in sources and destinations indicates a directed edge in the graph.  If the number of elements in the
 * sources and destinations lists do not match, the method should throw an IllegalArgumentException.
 * <p></p>
 * @author Prof. Parker & Reynaldo Villarreal Zambrano
 * @version October 26, 2023
 */
public class GraphUtility {

	/**
	 * This recursive method must use the depth-first search algorithm presented in lecture to determine whether there
	 * is a path from the vertex with srcData to the vertex with dstData in the graph.  Throws an
	 * IllegalArgumentException if there does not exist a vertex in the graph with srcData, and likewise for dstData.
	 * @param sources - List<Type></Type> of sources that represent a vertex where depth-first search algorithm starts
	 * @param destinations - List<Type></Type> of destinations that represent a vertex where depth-first search algorithm ends
	 * @param srcData - beginning vertex
	 * @param dstData - ending vertex
	 * @return isConnected - boolean<Type></Type> value that is true if there is a path from vertex with srcData to
	 * 						 vertex with srcData
	 * @param <Type> - represents the type of data used in the graph
	 * @throws IllegalArgumentException if sources and destinations list do not contain the same number of elements, or
	 * 									if srcData or dstData is not within either sources and/or destinations List.
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		// check and make sure the size of sources is the same as the size of destinations
		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException("Size of sources and destinations does not match.");
		}
		// create a new graph object
		Graph<Type> graph = new Graph<>();
		// put all values of sources into the graph and the corresponding destinations that correspond to the sources
		for (int i = 0; i < sources.size(); i++) {
			// add the ith value into the graph
			graph.addVal(sources.get(i));
			graph.addVal(destinations.get(i));
			// return the node that is found using the current index as the key
			Node<Type> sourceNode = graph.get(sources.get(i));
			Node<Type> destinationNode = graph.get(destinations.get(i));
			// set the neighbor (destination) of the source node
			sourceNode.setNeighbor(destinationNode);
		}
		// get the node at which we're starting at (srcData node)
		Node<Type> startNode = graph.get(srcData);
		if (startNode == null) {
			throw new IllegalArgumentException("srcData is not a vertex in the graph.");
		}
		// perform DepthFirstSearch on the startNode
		graph.DepthFirstSearch(startNode);
		// get the dstNode from the graph using dstData key
		Node<Type> dstNode = graph.get(dstData);
		if (dstNode == null) {
			throw new IllegalArgumentException("dstData is not a vertex in the graph");
		}
		// return if that node was visited or not
		return dstNode.getVisited();
	}

	/**
	 * This method must use the breadth-first search algorithm presented in lecture to find a shortest path from the
	 * vertex with srcData to the vertex with dstData in the graph. Throws an IllegalArgumentException if there does
	 * not exist a vertex in the graph with srcData, and likewise for dstData. Also, throws an IllegalArgumentException
	 * if there does not exist a path between the two vertices.
	 * @param sources - List<Type></Type> of sources that represent a vertex where depth-first search algorithm starts
	 * @param destinations - List<Type></Type> of destinations that represent a vertex where depth-first search algorithm ends
	 * @param srcData - beginning vertex
	 * @param dstData - ending vertex
	 * @return shortestPathList - List<Type></Type> that contains the shortest path from the vertex with srcData to the
	 * 							  vertex with dstData in the graph.
	 * @param <Type> - represents the type of data used in the graph
	 * @throws IllegalArgumentException if sources and destinations list do not contain the same number of elements, or
	 * 									if srcData or dstData is not within either sources and/or destinations List.
	 */
	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		LinkedList<Type> shortestPathList = new LinkedList<>();
		// check and make sure the size of sources is the same as the size of destinations
		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException("Size of sources and destinations does not match.");
		}
		// create a new graph object
		Graph<Type> graph = new Graph<>();
		// put all values of sources into the graph and the corresponding destinations that correspond to the sources
		for (int i = 0; i < sources.size(); i++) {
			// add the ith value into the graph
			graph.addVal(sources.get(i));
			graph.addVal(destinations.get(i));
			// return the node that is found using the current index as the key
			Node<Type> sourceNode = graph.get(sources.get(i));
			Node<Type> destinationNode = graph.get(destinations.get(i));
			// set the neighbor (destination) of the source node
			sourceNode.setNeighbor(destinationNode);
		}
		// get the node at which we're starting at (srcData node)
		Node<Type> startNode = graph.get(srcData);
		if (startNode == null) {
			throw new IllegalArgumentException("srcData is not a vertex in the graph.");
		}
		Node<Type> searchNode = graph.get(dstData);
		if (searchNode == null) {
			throw new IllegalArgumentException("dstData is not a vertex in the graph.");
		}
		// perform BreadthFirstSearch on the startNode
		graph.BreadthFirstSearch(startNode, searchNode, shortestPathList);
		// check if there is a path possible from srcData to dstData, throw error if there isn't a path
		if (shortestPathList.isEmpty()) {
			throw new IllegalArgumentException("There is no path from srcData to dstData in the graph.");
		}
		// return the shortest path list
		return shortestPathList;
	}

	/**
	 * This method must use the topological sort algorithm presented in lecture to generate a sorted ordering of the
	 * vertices in the graph.  Note that a graph may have more than one valid ordering, and any such ordering is
	 * accepted. Throws an IllegalArgumentException if the graph contains a cycle (recall topological sort works only
	 * on acyclic graphs).
	 * @param sources - List<Type></Type> of sources that represent a vertex where the graph starts.
	 * @param destinations - List<Type></Type> of destinations that represent a vertex where the graph ends.
	 * @return sortedList - List<Type></Type> that contains the sorted ordering of the vertices in the graph.
	 * @param <Type> - represents the type of data used in the graph
	 * @throws IllegalArgumentException if sources and destinations list do not contain the same number of elements, or
	 * 									if srcData or dstData is not within either sources and/or destinations List, or
	 * 								    if the graph contains a cycle (only works on acyclic graphs)
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		// check and make sure the size of sources is the same as the size of destinations
		if (sources.size() != destinations.size()) {
			throw new IllegalArgumentException("Size of sources and destinations does not match.");
		}
		List<Type> sortedList = new LinkedList<>();
		// create a new graph object
		Graph<Type> graph = new Graph<>();
		// put all values of sources into the graph and the corresponding destinations that correspond to the sources
		for (int i = 0; i < sources.size(); i++) {
			// add the ith value into the graph
			graph.addVal(sources.get(i));
			graph.addVal(destinations.get(i));
			// return the node that is found using the current index as the key
			Node<Type> sourceNode = graph.get(sources.get(i));
			Node<Type> destinationNode = graph.get(destinations.get(i));
			// set the neighbor (destination) of the source node
			sourceNode.setNeighbor(destinationNode);
			// increment inDegree by 1
			destinationNode.changeInDegree(1);
		}
		// perform topological search on the given lists
		graph.TopologicalSort(sortedList);
		return sortedList;
	}

	/**
	 * Builds "sources" and "destinations" lists according to the edges
	 * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
	 * data type is String.
	 * 
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments 
	 * --accepts one edge per line or edges terminated with ; 
	 * --does not accept attributes in [] (e.g., [label = "a label"])
	 * 
	 * @param filename - name of the DOT file
	 * @param sources - empty ArrayList, when method returns it is a valid
	 *        "sources" list that can be passed to the public methods in this
	 *        class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 *        "destinations" list that can be passed to the public methods in
	 *        this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {

		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		scan.useDelimiter(";|\n");

		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while(scan.hasNext()) {
			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if(line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if(edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);

		}

		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while(scan.hasNext()) {
			String[] substring = line.split(edgeOp);

			for(int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if(vertex1.equals(""))
					continue;

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if(vertex2.equals(""))
					continue;

				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}

			// do until the "}" has been read
			if(substring[substring.length - 1].indexOf("}") >= 0)
				break;

			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		scan.close();
	}
}