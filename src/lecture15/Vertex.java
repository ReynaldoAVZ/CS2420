package lecture15;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Vertex class for use in a graph where the vertex data is a string.
 *
 * @author Aaron Wood
 * @version 2023-10-17
 */
public class Vertex {
    private final String value;
    private final LinkedList<Edge> edges;

    /**
     * Construct vertex for a given value with no edges.
     * @param value - value of vertex
     */
    public Vertex(String value) {
        this.value = value;
        this.edges = new LinkedList<>();
    }

    /**
     * @return value of vertex
     */
    public String toString() {
        return this.value;
    }

    /**
     * @return partial DOT encoding for vertex and its edges
     */
    public String generatePartialDot() {
        StringBuilder builder = new StringBuilder();
        for (Edge edge : this.edges) {
            builder.append("\t");
            builder.append(this.value);
            builder.append(" -> ");
            builder.append(edge.getDestination());
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * @return list of edge strings of the form "(source, destination)"
     */
    public ArrayList<String> buildEdgeStrings() {
        ArrayList<String> edgeStrings = new ArrayList<>();
        for (Edge edge : this.edges) {
            edgeStrings.add("(" + this.value + ", " + edge.getDestination() + ")");
        }
        return edgeStrings;
    }

    /**
     * Add an edge to a destination vertex.
     * @param destination - destination vertex of edge
     */
    public void addEdge(Vertex destination) {
        this.edges.addFirst(new Edge(destination));
    }

}
