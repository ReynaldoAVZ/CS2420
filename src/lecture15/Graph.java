package lecture15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Class for building and visualizing a graph.
 *
 * @author Aaron Wood
 * @version 2023-10-17
 */
public class Graph {

    /**
     * Lookup for vertices by their value.
     */
    private final HashMap<String, Vertex> vertices;

    /**
     * Construct empty graph.
     */
    public Graph() {
        this.vertices = new HashMap<>();
    }

    /**
     * @return string representation of graph
     */
    public String toString() {
        StringBuilder builder = new StringBuilder("Graph\n");
        builder.append("\tvertices: ");
        builder.append(this.vertices.values());
        builder.append("\n\tedges: ");
        ArrayList<String> edgeStrings = new ArrayList<>();
        for (Vertex vertex : this.vertices.values()) {
            edgeStrings.addAll(vertex.buildEdgeStrings());
        }
        builder.append(edgeStrings);
        return builder.toString();
    }

    /**
     * @return DOT representation of graph which can be used at http://www.webgraphviz.com
     */
    public String generateDot() {
        StringBuilder builder = new StringBuilder("digraph G {\n");
        for (Vertex vertex : this.vertices.values()) {
            builder.append(vertex.generatePartialDot());
        }
        builder.append("}");
        return builder.toString();
    }

    /**
     * Add edge from a source vertex to a destination vertex.
     *
     * @implNote adds new vertices as needed
     *
     * @param source - value of the source vertex
     * @param destination - value of the destination vertex
     * @throws NoSuchElementException if edge cannot be added
     */
    public void addEdge(String source, String destination) throws NoSuchElementException {
        Vertex sourceVertex = this.addVertex(source);
        Vertex destinationVertex = this.addVertex(destination);
        sourceVertex.addEdge(destinationVertex);
    }

    /**
     * Add and return vertex for a given value.
     *
     * @implNote returns vertex if it already exists
     *
     * @param value - value of vertex
     * @return vertex associated to the value
     */
    private Vertex addVertex(String value) {
        if (!this.vertices.containsKey(value)) {
            this.vertices.put(value, new Vertex(value));
        }
        return this.vertices.get(value);
    }

}
