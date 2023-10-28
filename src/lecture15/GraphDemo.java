package lecture15;

/**
 * Demo class to build and visualize a graph.
 *
 * @author Aaron Wood
 * @version 2023-10-17
 */
public class GraphDemo {

    public static void main(String[] args) {
        printGraph(buildGraph());
        printGraph(buildAirportGraph());
    }

    public static void printGraph(Graph graph) {
        System.out.println("\n" + graph);
        System.out.println("\nDOT encoding");
        System.out.println(graph.generateDot());
    }

    public static Graph buildGraph() {
        String[][] edges = new String[][] {
                {"a", "e"},
                {"a", "c"},
                {"a", "b"},
                {"b", "d"},
                {"c", "e"},
                {"c", "d"},
                {"d", "f"},
                {"d", "c"},
                {"e", "f"},
                {"f", "d"},
        };
        Graph graph = new Graph();
        for (String[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        return graph;
    }

    public static Graph buildAirportGraph() {
        String[][] edges = new String[][] {
                {"SLC", "AUS"},
                {"SLC", "OAK"},
                {"AUS", "SLC"},
                {"AUS", "OAK"},
                {"AUS", "ELP"},
                {"AUS", "MSP"},
                {"OAK", "SLC"},
                {"OAK", "AUS"},
                {"OAK", "PDX"},
                {"ELP", "AUS"},
                {"PDX", "OAK"},
                {"MSP", "STL"},
                {"STL", "OAK"},
        };
        Graph graph = new Graph();
        for (String[] edge : edges) {
            graph.addEdge(edge[0], edge[1]);
        }
        return graph;
    }

}
