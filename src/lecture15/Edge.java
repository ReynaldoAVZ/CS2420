package lecture15;

/**
 * Edge class for use in a graph.
 *
 * @author Aaron Wood
 * @version 2023-10-17
 */
public class Edge {

    private final Vertex destination;

    /**
     * Construct edge to a destination vertex.
     * @param destination - destination vertex of edge
     */
    public Edge(Vertex destination) {
        this.destination = destination;
    }

    /**
     * @return destination vertex
     */
    public Vertex getDestination() {
        return this.destination;
    }

}
